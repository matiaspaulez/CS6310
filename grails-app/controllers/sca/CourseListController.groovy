package sca

class CourseListController {

    def index() {

        def courses = Course.getAll()
        def studentName = params.name
        def error = false
        [name: studentName, courses: courses, error: error]
    }


    def submit(){
        println "COURSE LIST CONTROLLER:"
        println "PARAMS: ${params}"
        for (number in params.checkbox){

        println "Course ${Course.findByNumber(number).name}"

        }

        return
        //redirect(action:"index", controller:"Student", params:[update: true])
    }

    def clear(){

    }
}
