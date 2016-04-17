package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import sca.Student;

public class StudentData
{
    private ArrayList<ArrayList<Integer>> students = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Student> parsedStudents;
    private FileInputStream fis;
    private InputStreamReader is;
    private BufferedReader br;

    public StudentData(File file)
    {

        try
        {
            fis = new FileInputStream(file);
            is = new InputStreamReader(fis);
            br = new BufferedReader(is);

            // int lineNumber = 1;
            String currentLine;
            // throw away the first line with field descriptions
            String output = br.readLine();
            // lineNumber++;
            System.out.println("trowing away: " + output + "\n");

            int studentId = 0;
            ArrayList<Integer> studentCourses = new ArrayList<Integer>();
            int currentStudent = 1;

            // read line
            while ((currentLine = br.readLine()) != null)
            {
                if (currentLine.length() == 0)
                {
                    // lineNumber++;
                    continue;
                }

                // System.out.println(
                // "\n[" + lineNumber + "]: Current line: " + currentLine);
                // get the an array out of the current line
                String[] data = currentLine.split(",");
                studentId = Integer.valueOf(data[0]);
                int course = Integer.valueOf(data[1]);

                if (studentId != currentStudent)
                {
                    // System.out.println(
                    // " New student, set new student and reset array");
                    currentStudent = studentId;
                    // System.out.println(" Adding Student: " + currentStudent
                    // + " course: " + course);
                    students.add(studentCourses);
                    studentCourses = new ArrayList<Integer>();
                }

                studentCourses.add(course);
                // lineNumber++;
            }

            // add the last set
            students.add(studentCourses);

            parsedStudents = new ArrayList<Student>();
            for (int i = 0; i < students.size(); i++)
            {
                Student s = new Student(i);
                s.setCourses(students.get(i));
                parsedStudents.add(s);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Student> getStudents()
    {
        return parsedStudents;
    }

}
