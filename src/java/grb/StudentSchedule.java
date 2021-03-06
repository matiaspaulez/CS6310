package grb;

import core.ScheduledCourse;
import core.StudentData;
import gurobi.*;
import sca.Course;
import sca.Semester;
import sca.Student;
import utils.ArgumentParser;
import utils.KTS;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

//import core.Course;
//import core.Semester;
//import core.Student;

public class StudentSchedule
{

    private ArgumentParser ap;
    private StudentData sd;
    private ArrayList<Student> students;
    private ArrayList<Semester> semesters;
    private ArrayList<Course> courses;
    private int totalStudents;
    private GRBEnv env;
    private GRBModel model;
    private GRBVar[][][] arrayVar;
    private GRBVar xVar;

    public StudentSchedule(String[] args) throws GRBException
    {
        this.ap = new ArgumentParser(args);
        this.sd = new StudentData(ap.getFile());
        // get students
        this.students = sd.getStudents();

        this.totalStudents = students.size();

        // main flow
        initialize();
        initilizeGRB();
        addCourseSizeConstraint();
        addCourseConstraint();
        addCourseAvailableConstraint();
        addMaxClassConstraint();
        addDependenciesConstraint();
        // get output
        performCalculationsOutput();
    }

    public StudentSchedule() throws GRBException
    {
        // get students
        this.students = new ArrayList<Student>(Student.findAll());

        this.totalStudents = students.size();

        // main flow
        initializeDomain();
        initilizeGRB();
        addCourseSizeConstraint();
        addCourseConstraint();
        addCourseAvailableConstraint();
        addMaxClassConstraint();
        addDependenciesConstraint();
        // get output
        performCalculationsOutput();
    }

    private void initialize()
    {
        // initialize sememsters
        semesters = new ArrayList<Semester>();
        for (int i = 0; i < KTS.SEMESTERS; i++)
        {
            semesters.add(new Semester(i));
        }

        // initialize courses
        courses = new ArrayList<Course>();
        for (int i = 0; i < KTS.COURSES; i++)
        {
            courses.add(new Course(i));
        }
    }

    private void initializeDomain()
    {
        // initialize sememsters
        semesters = new ArrayList<Semester>(Semester.findAll());

        // initialize courses
        courses = new ArrayList<Course>(Course.findAll());
    }

    private void initilizeGRB() throws GRBException
    {

        env = new GRBEnv();
        model = new GRBModel(env);
        arrayVar = new GRBVar[totalStudents][courses.size()][semesters.size()];
        for (int i = 0; i < totalStudents; i++)
        {
            for (int j = 0; j < courses.size(); j++)
            {
                for (int k = 0; k < semesters.size(); k++)
                {
                    arrayVar[i][j][k] = model.addVar(0, 1, 0, GRB.BINARY,
                            i + "_" + j + "_" + k);
                }
            }
        }
        // model update
        model.update();

        // variable we will optimize
        xVar = model.addVar(0, totalStudents, 1, GRB.INTEGER, "xVar");
        // model update
        model.update();

        // target to the sum of all courses
        GRBLinExpr expr = new GRBLinExpr();
        expr.addTerm(1, xVar);
        // minimize X
        model.setObjective(expr, GRB.MINIMIZE);
        // model update
        model.update();
    }

    private void addCourseSizeConstraint() throws GRBException
    {

        // all students for class are less than xVar the program find the
        // smallest value
        for (int i = 0; i < courses.size(); i++)
        {
            for (int j = 0; j < semesters.size(); j++)
            {
                GRBLinExpr tempExpr = new GRBLinExpr();
                for (int k = 0; k < totalStudents; k++)
                {
                    tempExpr.addTerm(1, arrayVar[k][i][j]);
                }
                model.addConstr(tempExpr, GRB.LESS_EQUAL, xVar,
                        "CourseSizeConstraint_" + i + "_" + j);
            }
        }
    }

    private void addCourseConstraint() throws GRBException
    {

        // students pick whatever class they want
        for (int i = 0; i < totalStudents; i++)
        {
            Student s = students.get(i);
            ArrayList<Integer> stCourses = s.getCourses();
            for (int j = 0; j < stCourses.size(); j++)
            {
                GRBLinExpr tempExpr = new GRBLinExpr();
                for (int k = 0; k < semesters.size(); k++)
                {
                    tempExpr.addTerm(1, arrayVar[i][stCourses.get(j) - 1][k]);
                }
                model.addConstr(tempExpr, GRB.EQUAL, 1,
                        "CourseConstraint_" + i + "_" + stCourses.get(j));
            }
        }
    }

    private void addCourseAvailableConstraint() throws GRBException
    {

        ScheduledCourse ca;
        // studens will take classes only if available
        for (Semester s : semesters)
        {
            for (Course c : courses)
            {
                ca = new ScheduledCourse(c, s);
                if (!KTS.isAvailable(ca))
                {
                    GRBLinExpr tempExpr = new GRBLinExpr();
                    for (int k = 0; k < totalStudents; k++)
                    {
                        tempExpr.addTerm(1, arrayVar[k][c.getId() - 1][s.getId() - 1]);
                    }
                    model.addConstr(tempExpr, GRB.EQUAL, 0,
                            "CourseAvailableConstraint_" + c.getId() + "_"
                                    + s.getId());
                }
            }
        }
    }

    private void addMaxClassConstraint() throws GRBException
    {

        // add constrain for students for maximum class size
        for (int i = 0; i < totalStudents; i++)
        {
            Student s = students.get(i);
            ArrayList<Integer> classes = s.getCourses();
            for (int k = 0; k < KTS.SEMESTERS; k++)
            {
                GRBLinExpr tempExpr = new GRBLinExpr();
                for (Integer classId : classes)
                {
                    tempExpr.addTerm(1, arrayVar[i][classId - 1][k]);
                }
                model.addConstr(tempExpr, GRB.LESS_EQUAL, KTS.MAX_CLASS_SIZE,
                        "MaxClassConstraint_" + i + "_" + k);
            }
        }
    }

    private void addDependenciesConstraint() throws GRBException
    {

        // calculate the dependencies
        for (Student s : students)
        {
            int i = s.getId();
            for (Integer classId : s.getCourses())
            {
                int id = classId - 1;
                Course course = new Course(-1);
                for (Course c : courses)
                {
                    if (c.getId() == id)
                    {
                        course = c;
                    }
                }

                if ((KTS.getDependency(course) != -1))
                {
                    GRBLinExpr tempExpr = new GRBLinExpr();
                    tempExpr.addTerm(1, arrayVar[i - 1][course.getId() - 1][0]);
                    model.addConstr(tempExpr, GRB.EQUAL, 0,
                            "DependenciesConstraint_" + i + "_" + id);
                    for (int k = 0; k < KTS.SEMESTERS; k++)
                    {
                        GRBLinExpr fooExpr = new GRBLinExpr();
                        GRBLinExpr varExpr = new GRBLinExpr();
                        for (int k1 = 0; k1 < k; k1++)
                        {
                            fooExpr.addTerm(1,
                                    arrayVar[i - 1][KTS.getDependency(course)][k1]);
                            varExpr.addTerm(1,
                                    arrayVar[i - 1][course.getId()][k1 + 1]);
                        }
                        model.addConstr(varExpr, GRB.LESS_EQUAL, fooExpr,
                                "DependenciesConstraint_" + i + "_" + id);
                    }
                }
            }
        }
    }

    private void performCalculationsOutput() throws GRBException
    {
        model.update();
        model.optimize();

        double objectiveValue = model.get(GRB.DoubleAttr.ObjVal);
        System.out.printf("Ojective value = %f\n", objectiveValue);
        System.out.println("X: " + xVar.get(GRB.DoubleAttr.X));

    }

    public Map printCourses(Student student) {

        int id = student.getId();
        ArrayList<Integer> courses = student.getCourses();
        //String results = "";
        Map results = new HashMap();

        for (int i = 0; i < courses.size(); i++) {
            for (int k = 0; k < semesters.size(); k++) {
                try {
                    if (arrayVar[id - 1][i][k].get(GRB.DoubleAttr.X) == 1) {
                        //System.out.printf("Student %d takes Course %d during Semester %d\n", id, i, 1);
                        //results += "course: " + (i + 1) + ", semester: " + Semester.get(k + 1);
                        Semester sem = (Semester)Semester.get(k+1);
                        results.put(sem.getId(), i);
                    }
                } catch (GRBException e) {
                    e.printStackTrace();
                }
            }
        }
        return results;
    }
}
