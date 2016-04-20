import sca.User
import sca.Course
import sca.Student


class BootStrap {

    def init = { servletContext ->

        //users
        def u1 = new User(userName: "st1", password: "123", firstName:"John", lastName:"Doe", role:"Student").save(failOnError: true)
        def u2 = new User(userName: "st2", password: "123", firstName:"Dave", lastName:"Johnson", role:"Student").save(failOnError: true)
        def u3 = new User(userName: "st3", password: "123", firstName:"Susan", lastName:"Sanchez", role:"Student").save(failOnError: true)
        def u4 = new User(userName: "st4", password: "123", firstName:"Lily", lastName:"Martin", role:"Student").save(failOnError: true)
        def u5 = new User(userName: "st5", password: "123", firstName:"Ted", lastName:"Adams", role:"Student").save(failOnError: true)
        def u6 = new User(userName: "st6", password: "123", firstName:"Fred", lastName:"Smith", role:"Student").save(failOnError: true)

        //students
        def st1 = new Student(u1).save(failOnError: true)
        def st2 = new Student(u2).save(failOnError: true)
        def st3 = new Student(u3).save(failOnError: true)
        def st4 = new Student(u4).save(failOnError: true)
        def st5 = new Student(u5).save(failOnError: true)
        def st6 = new Student(u6).save(failOnError: true)


        //courses
        def cl0 = new Course("Advanced Operating Systems", "6210", 0).save(failOnError: true)
        def cl1 = new Course("Computer Networks", "6250", 1).save(failOnError: true)
        def cl2 = new Course("Software Development Process", "6300", 2).save(failOnError: true)
        def cl3 = new Course("Machine Learning", "7641", 3).save(failOnError: true)
        def cl4 = new Course("High Performance Computer Architecture", "6290", 4).save(failOnError: true)
        def cl5 = new Course("Software Architecture and Design", "6310", 5).save(failOnError: true)
        def cl6 = new Course("Intro to Health Informatics", "6440", 6).save(failOnError: true)
        def cl7 = new Course("Computability, Complexity and Algorithms", "6505", 7).save(failOnError: true)
        def cl8 = new Course("Knowledge-Based Artificial Intelligence, Cognitive Systems", "7637", 8).save(failOnError: true)
        def cl9 = new Course("Computer Vision", "4495", 9).save(failOnError: true)
        def cl10 = new Course("Computational Photography", "6475", 10).save(failOnError: true)
        def cl11 = new Course("Introduction to Operating Systems", "8803", 11).save(failOnError: true)
        def cl12 = new Course("Artificial Intelligence for Robotics", "8803", 12).save(failOnError: true)
        def cl13 = new Course("Introduction to Information Security", "6035", 13).save(failOnError: true)
        def cl14 = new Course("High-Performance Computing", "6220", 14).save(failOnError: true)
        def cl15 = new Course("Machine Learning for Trading", "7646", 15).save(failOnError: true)
        def cl16 = new Course("Special Topics: Reinforcement Learning", "8803", 16).save(failOnError: true)
        def cl17 = new Course("Special Topics: Big Data", "8803", 17).save(failOnError: true)



    }


    def destroy = {
    }
}
