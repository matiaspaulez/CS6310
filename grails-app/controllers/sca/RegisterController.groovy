package sca

class RegisterController {

    def index() {
        [provId: params.provId]
    }

    def register(User userData){
        userData.save flush: true
        redirect(action:"index", controller:"Login", params:[successReg: true])
    }
}
