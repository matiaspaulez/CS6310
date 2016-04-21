import sca.User
import sca.Course
import sca.Student


class BootStrap {

    def csvToDb(String fname){
        def courses = Course.all
        new File(fname).splitEachLine(",") {fields ->
            courses.add(
                    name: fields[1],
                    number: fields[2],
                    term: fields[3] + fields[4] + fields[5],
                    availability: fields[6]
            )
        }
        for (course in courses) {
            println "${course.number} - ${course.name} - ${course.term}"
        };
    }


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
        def cl0 = new Course("Advanced Operating Systems", "6210", "Fall Only").save(failOnError: true)
        def cl1 = new Course("Computer Networks", "6250", "All").save(failOnError: true)
        def cl2 = new Course("Software Development Process", "6300", "All").save(failOnError: true)
        def cl3 = new Course("Machine Learning", "7641", "All").save(failOnError: true)
        def cl4 = new Course("High Performance Computer Architecture", "6290", "Spring Only").save(failOnError: true)
        def cl5 = new Course("Software Architecture and Design", "6310", "All").save(failOnError: true)
        def cl6 = new Course("Intro to Health Informatics", "6440", "Fall Only").save(failOnError: true)
        def cl7 = new Course("Computability, Complexity and Algorithms", "6505", "All").save(failOnError: true)
        def cl8 = new Course("Knowledge-Based Artificial Intelligence, Cognitive Systems", "7637", "All").save(failOnError: true)
        def cl9 = new Course("Computer Vision", "4495", "Spring Only").save(failOnError: true)
        def cl10 = new Course("Computational Photography", "6475", "Fall Only").save(failOnError: true)
        def cl11 = new Course("Introduction to Operating Systems", "8803", "All").save(failOnError: true)
        def cl12 = new Course("Artificial Intelligence for Robotics", "8803", "All").save(failOnError: true)
        def cl13 = new Course("Introduction to Information Security", "6035", "Spring Only").save(failOnError: true)
        def cl14 = new Course("High-Performance Computing", "6220", "Fall Only").save(failOnError: true)
        def cl15 = new Course("Machine Learning for Trading", "7646", "Spring Only").save(failOnError: true)
        def cl16 = new Course("Special Topics: Reinforcement Learning", "8803", "Fall Only").save(failOnError: true)
        def cl17 = new Course("Special Topics: Big Data", "8803", "Spring Only").save(failOnError: true)

        def user = null
        user = new User(userName: "user1", password: "123", firstName:"John", lastName:"Doe", role:"Student")
        assert user.save(failOnError:true, flush:true, insert: true)
        user.errors = null

        assert User.count == 7;

        def student = null
        student = new Student(user)
        student.addToCourses(cl0)
        student.addToCourses(cl17)
        assert student.save(failOnError:true, flush:true, insert: true)

    }


    def destroy = {
    }
}
