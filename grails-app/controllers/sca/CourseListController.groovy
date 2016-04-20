package sca

class CourseListController {

    def index() {

        def courses = Course.getAll()
        def studentName = params.name
        [name: studentName, courses: courses]
    }
}
