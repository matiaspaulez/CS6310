import sca.Professor
import sca.TA
import sca.Student

class BootStrap {

    def init = { servletContext ->

        //students
        def st1 = new Student(userName: "st1", password: "123", firstName:"John", lastName:"Doe")
        def st2 = new Student(userName: "st2", password: "123", firstName:"Dave", lastName:"Johnson")
        def st3 = new Student(userName: "st3", password: "123", firstName:"Susan", lastName:"Sanchez")
        def st4 = new Student(userName: "st4", password: "123", firstName:"Lily", lastName:"Martin")
        def st5 = new Student(userName: "st5", password: "123", firstName:"Ted", lastName:"Adams")
        def st6 = new Student(userName: "st6", password: "123", firstName:"Fred", lastName:"Smith")

        //TA
        def ta1 = new TA(userName: "ta1", password: "123", firstName:"Juan", lastName:"Doss")
        def ta2 = new TA(userName: "ta2", password: "123", firstName:"Pedro", lastName:"Cardot")
        def ta3 = new TA(userName: "ta3", password: "123", firstName:"Samantha", lastName:"Franklin")

        //Professor
        def p1 = new Professor(userName: "p1", password: "123", firstName:"Spencer", lastName:"Ross")
        def p2 = new Professor(userName: "p2", password: "123", firstName:"Sevas", lastName:"Tian")

    }
    def destroy = {
    }
}
