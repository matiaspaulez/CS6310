import sca.User
import sca.Course
import sca.Student
import sca.Semester
import utils.KTS


class BootStrap {

    def init = { servletContext ->

        //users
        def u1 = new User(userName: "st1", password: "123", firstName: "John", lastName: "Doe", role: "Student").save(failOnError: true)
        def u2 = new User(userName: "st2", password: "123", firstName: "Dave", lastName: "Johnson", role: "Student").save(failOnError: true)
        def u3 = new User(userName: "st3", password: "123", firstName: "Susan", lastName: "Sanchez", role: "Student").save(failOnError: true)
        def u4 = new User(userName: "st4", password: "123", firstName: "Lily", lastName: "Martin", role: "Student").save(failOnError: true)
        def u5 = new User(userName: "st5", password: "123", firstName: "Ted", lastName: "Adams", role: "Student").save(failOnError: true)
        def u6 = new User(userName: "st6", password: "123", firstName: "Fred", lastName: "Smith", role: "Student").save(failOnError: true)

        //students

        def st1 = new Student(u1).save(failOnError: true)
        def st2 = new Student(u2).save(failOnError: true)
        def st3 = new Student(u3).save(failOnError: true)
        def st4 = new Student(u4).save(failOnError: true)
        def st5 = new Student(u5).save(failOnError: true)
        def st6 = new Student(u6).save(failOnError: true)

        //courses
        def ac = [
                new Course(1, "Advanced Operating Systems", "6210", "Fall Only").save(failOnError: true),
                new Course(2, "Computer Networks", "6250", "All").save(failOnError: true),
                new Course(3, "Software Development Process", "6300", "All").save(failOnError: true),
                new Course(4, "Machine Learning", "7641", "All").save(failOnError: true),
                new Course(5, "High Performance Computer Architecture", "6290", "Spring Only").save(failOnError: true),
                new Course(6, "Software Architecture and Design", "6310", "All").save(failOnError: true),
                new Course(7, "Intro to Health Informatics", "6440", "Fall Only").save(failOnError: true),
                new Course(8, "Computability, Complexity and Algorithms", "6505", "All").save(failOnError: true),
                new Course(9, "Knowledge-Based Artificial Intelligence, Cognitive Systems", "7637", "All").save(failOnError: true),
                new Course(10, "Computer Vision", "4495", "Spring Only").save(failOnError: true),
                new Course(11, "Computational Photography", "6475", "Fall Only").save(failOnError: true),
                new Course(12, "Introduction to Operating Systems", "8803", "All").save(failOnError: true),
                new Course(13, "Artificial Intelligence for Robotics", "8803", "All").save(failOnError: true),
                new Course(14, "Introduction to Information Security", "6035", "Spring Only").save(failOnError: true),
                new Course(15, "High-Performance Computing", "6220", "Fall Only").save(failOnError: true),
                new Course(16, "Machine Learning for Trading", "7646", "Spring Only").save(failOnError: true),
                new Course(17, "Special Topics: Reinforcement Learning", "8803", "Fall Only").save(failOnError: true),
                new Course(18, "Special Topics: Big Data", "8803", "Spring Only").save(failOnError: true)]


        for (Student s in Student.getAll()) {

            s.addToSelectedCourses(ac[getRand(ac.size())])
            s.addToSelectedCourses(ac[getRand(ac.size())])
            s.addToSelectedCourses(ac[getRand(ac.size())])
            s.addToSelectedCourses(ac[getRand(ac.size())])
            s.save(flush: true)
            println "[LOG FROM BOOTSRAP:] Student: ${s}"

        }

        //Create user, student and assign courses to it
        def user = new User(userName: "user1", password: "123", firstName:"Steve", lastName:"Wozniak", role:"Student").save(failOnError: true)
        def student = new Student(user)
        student.addToSelectedCourses(Course.get(1))
        student.addToSelectedCourses(Course.get(10))
        student.save(failOnError:true, flush:true, insert: true)
        println "[LOG] ${Student.get(7)}"

        //Create semesters
        for (int i = 0; i < KTS.SEMESTERS; i++) {
            def sem = null
            def sname = ""
            def start = ""
            def end = ""
            if (i % 3 == 0){
                sname = "FALL"
                start = "August"
                end = "December"
            }
            else if (i % 3 == 1) {
                sname = "SPRING"
                start = "January"
                end = "April"
            }
            else {
                sname = "SUMMER"
                start = "May"
                end = "July"
            }
            sem = new Semester(name:sname, startDate: start, endDate: end)
            assert sem.save(failOnError:true, flush:true, insert: true)
        }

    }

    def getRand(range){

        return Math.abs(new Random().nextInt() % (range - 1)) + 1
    }


    def destroy = {
    }

}