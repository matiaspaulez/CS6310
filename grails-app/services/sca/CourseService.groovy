package sca

import grails.transaction.Transactional

@Transactional
class CourseService {

    def serviceMethod() {
        new Course().save(flush:true)
    }
}
