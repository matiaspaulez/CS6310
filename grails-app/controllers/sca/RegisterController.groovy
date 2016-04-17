package sca

class RegisterController {

    def index() {
        [provId: params.provId]
    }

    def register(Student userData){
        userData.save flush: true
        redirect(action:"index", controller:"Login", params:[successReg: true, student: userData])
    }
}
