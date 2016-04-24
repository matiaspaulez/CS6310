package sca


class Student{

    int id
    User user

    static hasMany = [selectedCourses: Course, completedCourses: Course]

    def Student(user){
        this.user = user
        this.id = user.id

    }

    public ArrayList<Integer> getCourses(){

        ArrayList<Integer> courses = new ArrayList<Integer>()
        selectedCourses.each{
            courses.add(it.id)
        }
        return courses;
    }

    static constraints = {
    }



    public String toString(){
        return new String("id $id, " +
                "user: $user, " +
                "firstName: $user.firstName, " +
                "lastName: $user.lastName, " +
                "userName: $user.userName, " +
                "role: $user.role, " +
                "password: ****, " +
                "SelectedCourses: " +
                selectedCourses
        )

    }


}
