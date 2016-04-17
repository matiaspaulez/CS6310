package core;

import sca.Course;
import sca.Semester;


public class ScheduledCourse



{
    private Course course;
    private Semester semester;

    public ScheduledCourse(Course course, Semester semester)
    {
        this.course = course;
        this.semester = semester;
    }

    public Course getCourse()
    {
        return this.course;
    }

    public Semester getSemester()
    {
        return this.semester;
    }

}
