package sca

class StudentController {

    def index(Integer max) {


        println "[LOG:] Student Controller params: ${params}"

        def user = User.findById(params.id)
        println "[LOG:] Student Controller user:  ${user.userName}"

        def st = Student.findById(params.id) ?: new Student(user).save(failOnError: true)
        println "[LOG:] Student Controller student: ${st}"

        def selectedCourses = st.selectedCourses
        def coursesSelectedCount = selectedCourses.size() ?: 0

        def studentCompletedCourses = st.getCompletedCourses()
        def coursesCompletedCount = studentCompletedCourses.size() ?: 0

        println "[LOG:] StudentController coursesCompletedCount: ${coursesCompletedCount}"
        println "[LOG:] StudentController coursesSelectedCount: ${coursesSelectedCount}"

        [student: st, selectedCourses: selectedCourses, csc: coursesSelectedCount, ccc: coursesCompletedCount]
    }

    def viewCourseList(){
        println "[LOG:] viewCourseList params: $params"
        redirect(action: "index", controller: "CourseList", params: [id: params.id])
    }

    def viewRecommendationsList(){
        println "[LOG:] viewRecommendationsList $params"
        def user = Student.findById(params.id)
        def username = user.getUser().userName
        println "[LOG:] username: $username"
        redirect(action: "index", controller: "Recommendations", params:[id: params.id])
    }

}
