package sca


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

//@Transactional(readOnly = true)
class LoginController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {

        log.info "[LOG:] Entering INDEX"
        if(params.error){
            [error: true, errorLabel: "Wrong User Name or Passsword"]
        }
        else if(params.successReg){
            Student s = params.userData
            [register: true]
        }
    }


    def login(User userData){
        def user = User.findByUserName(userData.userName)

        if(user == null){
            println('USER NULL')
            redirect(action: "index", params:[error: true, errorLabel: "Wrong User Name or Password"])
            return
        }

        println "[LOG:] Entering login with user id: ${user.getId()}"
        println "[LOG:] Entering login with user name: ${user.getUserName()}"
        println "[LOG:] Entering login with first name: ${user.getFirstName()}"
        println "[LOG:] Entering login with last name: ${user.getLastName()}"
        println "[LOG:] Entering login with role: ${user.getRole()}"
        println "[LOG:] Entering login with pass: ${user.getPassword()}"

        if (user.role == 'Student'){
            redirect(action: "index", controller: "Student", params:[id: user.id])
        } else if(user.role == 'Professor'){
            redirect(action: "index", controller: "Professor", params:[studentId: user.id])
        } else if(user.role == 'TA'){
            redirect(action: "index", controller: "TA", params:[studentId: user.id])
        }

    }

    def register(User userData){
        redirect(action: "index", controller: "Register")
    }

}