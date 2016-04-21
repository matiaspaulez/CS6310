package sca

import grb.StudentSchedule

class Student{

    //int id
    User user
    def courses = []
    
    static hasMany = [courses: Course]

    def Student(user){
        this.user = user
        //this.id = user.getId()
        StudentSchedule.printCourses()
    }

    def ArrayList<Integer> getCourses(){
        return courses;
    }


    static constraints = {
    }
}
