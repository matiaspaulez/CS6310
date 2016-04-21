package sca

class Student{

    int id
    User user
    def courses = []
    
    static hasMany = [courses: Course]

    def Student(user){
        this.user = user
        this.id = user.getId()
    }

    def ArrayList<Integer> getCourses(){
        //return courses;
        ArrayList<Integer> clone = new ArrayList<Integer>(this.courses.size());
        for(Course item: this.courses) clone.add(item.id);
        return clone;
    }


    static constraints = {
    }
}
