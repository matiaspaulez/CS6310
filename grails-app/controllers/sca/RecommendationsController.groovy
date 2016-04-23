package sca
import grb.StudentSchedule

class RecommendationsController {

    def index() {
        StudentSchedule schedule = new StudentSchedule()
        def student = Student.findById(params.id)

        println "[LOG] printing gurobi results: $params"
        println "[LOG] student is: $student"
        def recs = schedule.printCourses(student)
        println "[LOG] printing recommendations: $recs"
    }
}
