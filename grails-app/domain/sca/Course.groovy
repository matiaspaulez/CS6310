package sca

class Course {

    int id
    String semester
    String name
    String number
    boolean completed

    def Course(id, name, number, semester){
        this.id = id
        this.semester = semester
        this.name = name
        this.number = number
    }

    def Course(id){
        this.id = id
    }



    static constraints = {
    }

    public String toString(){
        return "ID: $id CS-$number"
    }
}
