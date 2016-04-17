package utils;

//import core.Course;
import core.ScheduledCourse;
import sca.Course;

public final class KTS
{
    //classes ids available during the year
    public static final int[] aALWAYS = { 2, 3, 4, 6, 8, 9, 12, 13 };
    public static final int[] aSPRING = { 5, 10, 14, 16, 18 };
    public static final int[] aFALL = { 1, 7, 11, 15, 17 };
    
    public static final String sFALL = "FALL";
    public static final String sSPRING = "SPRING";
    public static final String sSUMMER = "SUMMER";
    
    public static final int COURSES = 18;
    public static final int SEMESTERS = 12;
    public static final int MAX_CLASS_SIZE = 2;

    
    //return if a scheduled course is available based on the semester and term
    public static boolean isAvailable(ScheduledCourse sc)
    {
        if (hasValue(aALWAYS, sc))
        {
            return true;
        } else if (hasValue(aFALL, sc)
                && getSemesterName(sc) == sFALL)
        {
            return true;
        } else if (hasValue(aSPRING, sc)
                && getSemesterName(sc) == sSPRING)
        {
            return true;
        }
        return false;
    }

    //return true if key is hold in array
    public static boolean hasValue(final int[] array, ScheduledCourse sc)
    {
        //add plus one to match correctly
        final int key = sc.getCourse().getId() + 1;
        for (final int i : array)
        {
            if (i == key)
            {
                return true;
            }
        }
        return false;
    }
   
    //return the name of the term for the scheduled course
    public static String getSemesterName(ScheduledCourse sc)
    {
        int id = sc.getSemester().getId();        
        if (id % 3 == 0)
        {
            return sFALL;
        } else if (id % 3 == 1)
        {
            return sSPRING;
        } else
        {
            return sSUMMER;
        }
    }
    
    /*
     * COURSES: 
     * 0 ->  1,Advanced Operating Systems,6210,1,0,0,Fall Only 
     * 1 ->  2,Computer Networks,6250,1,1,1,All 
     * 2 ->  3,Software Development Process,6300,1,1,1,All
     * 3 ->  4,Machine Learning,7641,1,1,1,All 
     * 4 ->  5,High Performance Computer Architecture,6290,0,1,0,Spring Only 
     * 5 ->  6,Software Architecture and Design,6310,1,1,1,All 
     * 6 ->  7,Intro to Health Informatics,6440,1,0,0,Fall Only
     * 7 ->  8,"Computability, Complexity and Algorithms",6505,1,1,1,All 
     * 8 ->  9,"Knowledge-Based Artificial Intelligence, Cognitive Systems",7637,1,1,1,All 
     * 9 ->  10,Computer Vision,4495,0,1,0,Spring Only
     * 10 ->  11,Computational Photography,6475,1,0,0,Fall Only 
     * 11 ->  12,Introduction to Operating Systems,8803-002,1,1,1,All 
     * 12 ->  13,Artificial Intelligence for Robotics,8803-001,1,1,1,All 
     * 13 ->  14,Introduction to Information Security,6035,0,1,0,Spring Only 
     * 14 ->  15,High-Performance Computing,6220,1,0,0,Fall Only 
     * 15 ->  16,Machine Learning for Trading,7646,0,1,0,Spring Only 
     * 16 ->  17,Special Topics: Reinforcement Learning,8803,1,0,0,Fall Only 
     * 17 ->  18,Special Topics: Big Data,8803,0,1,0,Spring Only
     */
    /*
     * DEPENDENCIES
     * these are the classes dependencies. Since our class ids initial value is zero
     * we need to adjust propertly
     * 
     *  4, 16 ->  3 - 15,
     * 12,  1 -> 11 -  0,
     *  9, 13 ->  8 - 12,
     *  3,  7 ->  2 -  6,
     */
    
    private static final int cs7641 = 3;
    private static final int cs88032 = 11;
    private static final int cs6505 = 8;
    private static final int cs6300 = 2;
    private static final int cs7646 = 15;
    private static final int cs6210 = 0;
    private static final int cs88031 = 12;
    private static final int cs6440 = 6;
    
    
    //get the dependency of a class if applies
    public static int getDependency(Course c)
    {        
        int ret;
        switch (c.getId())
        {
        case cs7646:
            ret = cs7641;            
            break;
        case cs6210:
            ret = cs88032;            
            break;
        case cs88031:
            ret = cs6505;            
            break;
        case cs6440:
            ret = cs6300;            
            break;
        default:
            ret = -1;
        }        
        return ret;        
    }   
}
