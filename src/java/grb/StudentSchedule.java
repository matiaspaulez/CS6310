package grb;

import java.util.ArrayList;

import core.Course;
import core.ScheduledCourse;
import core.Semester;
import core.Student;
import core.StudentData;
import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;
import utils.ArgumentParser;
import utils.KTS;

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

    private void initilizeGRB() throws GRBException
    {

        env = new GRBEnv();
        model = new GRBModel(env);
        arrayVar = new GRBVar[totalStudents][KTS.COURSES][KTS.SEMESTERS];
        for (int i = 0; i < totalStudents; i++)
        {
            for (int j = 0; j < KTS.COURSES; j++)
            {
                for (int k = 0; k < KTS.SEMESTERS; k++)
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
        for (int i = 0; i < KTS.COURSES; i++)
        {
            for (int j = 0; j < KTS.SEMESTERS; j++)
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
                for (int k = 0; k < KTS.SEMESTERS; k++)
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
                        tempExpr.addTerm(1, arrayVar[k][c.getId()][s.getId()]);
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
                    tempExpr.addTerm(1, arrayVar[i][course.getId()][0]);
                    model.addConstr(tempExpr, GRB.EQUAL, 0,
                            "DependenciesConstraint_" + i + "_" + id);
                    for (int k = 0; k < KTS.SEMESTERS; k++)
                    {
                        GRBLinExpr fooExpr = new GRBLinExpr();
                        GRBLinExpr varExpr = new GRBLinExpr();
                        for (int k1 = 0; k1 < k; k1++)
                        {
                            fooExpr.addTerm(1,
                                    arrayVar[i][KTS.getDependency(course)][k1]);
                            varExpr.addTerm(1,
                                    arrayVar[i][course.getId()][k1 + 1]);
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

}
