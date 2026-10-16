public class Student {
    private String studentName;
    private int studentID;
    private String studentEmail;

    public Student(String name, int id, String email){
        this.studentName = name;
        this.studentID = id;
        this.studentEmail = email;
    }

    public String getName(){
        return studentName;
    }

    public void setName(String newName){
        this.studentName = newName;
    }

    public int getID(){
        return this.studentID;
    }

    public void setID(int newId){
        this.studentID = newId;
    }

    public String getEmail(){
        return this.studentEmail;
    }

    public void setEmail(String newEmail){
        this.studentEmail = newEmail;
    }

    @Override
    public String toString() {
        return "Student ID: " + this.studentID
        + "\nStudent Name: " + this.studentName
        + "\nStudent Email: " + this.studentEmail;
    }
}
