package sca

class StudentController {

    def index(Integer max) {


        println "params: ${params}"

        def user = User.findById(params.studentId)
        def student = Student.findByUser(user) ?: new Student(user)
        def courses = student.getCourses()
        //def coursesCount = courses ?: 0
        def coursesCount = Course.getAll().size()
        println "[LOG:] Student Controller user:  ${user.getUserName()}"
        println "[LOG:] Student Controller student name:  ${student.getUser().getFirstName()}, last name: ${student.getUser().getLastName()}  "

        [student: student, courses: courses, user: user, coursesCount: coursesCount]
    }

    def viewCourseList(){
        println "[LOG:] viewCourseList $params"
        def student = User.findById(params.user)
        def name = student.firstName
        def last = student.lastName

        println "[LOG:] viewCourseList: $name $last"

        redirect(action: "index", controller: "CourseList", params: [user: "$name $last"])
    }

    def viewRecommendationsList(){
        println "[LOG:] viewRecommendationsList $params"
        def user = User.findById(params.user)
        def username = user.userName
        println "[LOG:] username: $username"
        redirect(action: "index", controller: "Recommendations", params:[user: user.id])
    }


}
