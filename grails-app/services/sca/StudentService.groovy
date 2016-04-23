package sca

import grails.transaction.Transactional

@Transactional
class StudentService {

    def serviceMethod() {
        new Student().save(flush:true)
    }
}
