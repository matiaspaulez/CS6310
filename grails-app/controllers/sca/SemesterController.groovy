package sca


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class SemesterController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Semester.list(params), model: [semesterInstanceCount: Semester.count()]
    }

    def show(Semester semesterInstance) {
        respond semesterInstance
    }

    def create() {
        respond new Semester(params)
    }

    @Transactional
    def save(Semester semesterInstance) {
        if (semesterInstance == null) {
            notFound()
            return
        }

        if (semesterInstance.hasErrors()) {
            respond semesterInstance.errors, view: 'create'
            return
        }

        semesterInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'semester.label', default: 'Semester'), semesterInstance.id])
                redirect semesterInstance
            }
            '*' { respond semesterInstance, [status: CREATED] }
        }
    }

    def edit(Semester semesterInstance) {
        respond semesterInstance
    }

    @Transactional
    def update(Semester semesterInstance) {
        if (semesterInstance == null) {
            notFound()
            return
        }

        if (semesterInstance.hasErrors()) {
            respond semesterInstance.errors, view: 'edit'
            return
        }

        semesterInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Semester.label', default: 'Semester'), semesterInstance.id])
                redirect semesterInstance
            }
            '*' { respond semesterInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Semester semesterInstance) {

        if (semesterInstance == null) {
            notFound()
            return
        }

        semesterInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Semester.label', default: 'Semester'), semesterInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'semester.label', default: 'Semester'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
