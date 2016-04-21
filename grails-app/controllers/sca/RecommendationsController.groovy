package sca
import grb.StudentSchedule

class RecommendationsController {

    def index() {
        String[] f = ["-i", "C:\\Users\\ibu\\IdeaProjects\\CS6310\\csv\\small\\student_demand_10.csv"]
        StudentSchedule schedule = new StudentSchedule(f)
        def user = User.findById(params.user)

        Student student = Student.findByUser(user) ?: new Student(user)

        println "[LOG] printing gurobi results: $params"
        println "[LOG] student is: $student"
        schedule.printCourses(student)
    }
}
