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

        def error = false
        [courses: courses, error: error, studentName: "$firstName $lastName", id: params.id, ssc: studentSelectedCourses, scc: studentCompletedCourses]
    }


    def submit(){

        def st = Student.findById(params.id)
        println "from here:: $st"
        //for (pid in params.checkbox){
        //  def c = Course.findById(pid)
        updateCourses(st, params.checkbox)
        //st.addToSelectedCourses(Course.findById(pid))
        //}

        println "from here:: $st"

        //st.save(flush: true)

        redirect(action:"index", controller:"Student", params:[update: true, id: params.id])
    }

    def clear(){
        index()
    }


    def updateCourses(Student student, def listOfSelectedCoursesId){

        student.getSelectedCourses().removeAll(it.semester == "Fall Only" )
        listOfSelectedCoursesId.each {
            student.addToSelectedCourses(Course.findById(it))
        }

        student.save(flush:true)

    }

}
