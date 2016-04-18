package sca

class RegisterController {

    def index() {
        [provId: params.provId]
    }

    def register(User userData){
        println "[LOG:] Entering resgister ${userData}"

        println "[LOG:] Entering reg with user id:   2 ${userData.getId()}"
        println "[LOG:] Entering reg with user name: ${userData.getUserName()}"
        println "[LOG:] Entering reg with first name: ${userData.getFirstName()}"
        println "[LOG:] Entering reg with last name: ${userData.getLastName()}"
        println "[LOG:] Entering reg with role: ${userData.getRole()}"
        println "[LOG:] Entering reg with pass: ${userData.getPassword()}"


        userData.save flush: true
        redirect(action:"index", controller:"Login", params:[successReg: true])
    }
}
