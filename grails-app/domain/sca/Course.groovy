package sca

class Course {

    int id
    String name
    String number
    String term
    String availability
    String description
    String dependency

    def Course(id){
        this.id = id
    }



    static constraints = {
    }
}
