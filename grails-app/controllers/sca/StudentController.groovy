package sca

class StudentController {

    def index(Integer max) {


        println "params: ${params}"

        def user = User.findById(params.studentId)
        def student = Student.findByUser(user) ?: new Student(user)
        def courses = student.getCourses()
        def coursesCount = courses ?: 0

        println "[LOG:] Student Controller user:  ${user.getUserName()}"
        println "[LOG:] Student Controller student name:  ${student.getUser().getFirstName()}, last name: ${student.getUser().getLastName()}  "

        [student: student, courses: courses, user: user, coursesCount: coursesCount]
    }

    def viewDocList(){
        redirect(action: "index", controller: "DocumentsList", params: [specId: params.specId, allDocs: true])
    }

    def viewProvidersList(){
        redirect(action: "index", controller: "ProvidersList", params: [specId: params.specId, isSpec: true])
    }

}
