package sca

class Course {

    int id
    String semester
    String name
    String number

    def Course(name, number, semester){
        this.semester = semester
        this.name = name
        this.number = number
    }

    def Course(id){
        this.id = id
    }



    static constraints = {
    }
}
