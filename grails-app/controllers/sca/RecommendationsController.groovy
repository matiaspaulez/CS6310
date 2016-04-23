package sca
import grb.StudentSchedule

class RecommendationsController {

    def index() {

        /*************************
         * Dummy courses
         */
        def courses = Course.getAll()
        println "[LOG:] Course list controller params: $params"
        def student = Student.findById(params.id)
        println "[LOG:] Course list controller student: $student"

        def firstName = student.user.firstName
        def lastName = student.user.lastName
        def studentCourses = student.selectedCourses
        println "[LOG:] Course list controller studentCourses: $studentCourses"

        def error = false
        [courses: courses, error: error, studentName: "$firstName $lastName", sc: studentCourses, id: params.id]

        /*
         * End dummy courses
         */

        //StudentSchedule schedule = new StudentSchedule()
        //def student = Student.findById(params.id)


        println "[LOG] printing gurobi results: $params"
        println params.courseList
        println "[LOG] student is: $student"
        //def recs = schedule.printCourses(student)
        //println "[LOG] printing recommendations: $recs"
    }

    def goback(){
        redirect(action:"index", controller:"Student", params:[update: true, id: params.id])
    }

}
