package sca

class CourseListController {

    def index() {

        def courses = Course.getAll()
        println "[LOG:] Course list controller params: $params"
        def student = Student.findById(params.id)
        println "[LOG:] Course list controller student: $student"

        def firstName = student.user.firstName
        def lastName = student.user.lastName

        def studentSelectedCourses = student.selectedCourses
        def studentCompletedCourses = student.completedCourses
        println "[LOG:] Course list controller studentSelectedCourses: $studentSelectedCourses"
        println "[LOG:] Course list controller studentCompletedCourses: $studentCompletedCourses"
        def error = params.error ?: false

        [courses: courses, error: error, studentName: "$firstName $lastName", id: params.id, ssc: studentSelectedCourses, scc: studentCompletedCourses]
    }


    def submit(){

        def checkbox = params.checkbox ?: []

        if(checkbox.size() > 2) {
            redirect(action: "index", controller: "CourseList", params: [id: params.id, error: true])
            return
        }

        def st = Student.findById(params.id)
        println "[LOG:] Course list controller submit Student: $st"
        updateCourses(st, params.checkbox)

        println "[LOG:] Course list controller submit Student after update: $st"

        redirect(action:"index", controller:"Student", params:[update: true, id: params.id])
    }

    def clear(){
        index()
    }


    def updateCourses(Student student, def listOfSelectedCoursesId){

        student.getSelectedCourses().clear()
        def size = student.getSelectedCourses().size()
        println "[LOG:] Course list controller updateCourses: $size"

        listOfSelectedCoursesId.each {
            println "[LOG:] Course list controller Selected: $it"
            student.addToSelectedCourses(Course.findById(it))
        }
        println "[LOG:] Course list controller Student AFTER Selected: $student"
        student.save(flush:true)

    }

}
