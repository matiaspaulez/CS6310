package sca

class CourseListController {

    def index() {

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
    }


    def submit(){

        def st = Student.findById(params.id)
        println "from here:: $st"
        for (pid in params.checkbox){
            println "Course ${Course.findById(pid)}"
            st.addToSelectedCourses(Course.findById(pid))
        }

        println "from here:: $st"

        st.save(flush: true)

        redirect(action:"index", controller:"Student", params:[update: true, id: params.id])
    }

    def clear(){
        index()
    }

}
