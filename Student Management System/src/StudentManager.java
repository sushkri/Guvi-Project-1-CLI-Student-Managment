import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> studentsList;


    public StudentManager(){
        this.studentsList = new ArrayList<>();
    }

    public void addStudent(Student student){
        if(studentsList.contains(student)){
            System.out.println("Studen already added");
        }else{
            this.studentsList.add(student);
        }
    }

    public Student removeStudent(String studentName){
        for(Student student: studentsList){
            if(student.getName().equals(studentName)){
                Student removedStudent = student;
                studentsList.remove(student);
                return removedStudent;
            }
        }

        return null;
    }

    public void updateStudentInfo(String name, String updateParameter, String updateInfo){
        for(Student student : studentsList){
            if(student.getName().equals(name)){
                if(updateParameter.equals("id")){
                    student.setID(Integer.valueOf(updateInfo));
                }else if(updateParameter.equals("name")){
                    student.setName(updateInfo);
                }else if(updateParameter.equals("email")){
                    student.setEmail(updateInfo);
                }
            }
        }
    }

    public boolean studentPresent(String studentName){
        boolean isPresent = false;
        for(Student st : studentsList){
            if(st.getName().equals(studentName)){
                isPresent = true;
            }
        }

        return isPresent;
    }

    public void printStudentDetails(String studentName){
        System.out.println("Student List: ");
        System.out.println("--------------------------------------------");
        for(Student student: studentsList){
            if(student.getName().equals(studentName)){
                System.out.println(student);
            }
        }
        System.out.println("--------------------------------------------");
    }

    public void printAllStudentDetails(){
        System.out.println("--------------------------------------------");
        for(Student student: studentsList){
            System.out.println(student);
            System.out.println("--------------------------------------------");
        }
    }
}
