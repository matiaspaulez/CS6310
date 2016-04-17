package sca

class Semester {

    int id
    String name
    String startDate
    String endDate

    def Semester(id){
        this.id = id
    }

    static constraints = {
    }
}
