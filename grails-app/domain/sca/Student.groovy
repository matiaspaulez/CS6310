package sca

class Student {

    int id
    String userName
    String password
    String firstName
    String LastName
    def courses = []

    def Student(id){
        this.id = id
    }

    def ArrayList<Integer> getCourses(){
        return courses;
    }

    static constraints = {
    }
}
