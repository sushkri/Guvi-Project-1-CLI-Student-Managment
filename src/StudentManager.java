import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentManager {
    private ArrayList<Student> studentsList;
    private String fileName;


    public StudentManager(String fileName){
        this.studentsList = new ArrayList<>();
        this.fileName = fileName;
        loadFromFile(); // reading saved students as soon as program starts.
    }

    //File Handling using BufferedWriter and Filewriter
    public void saveToFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for(Student student : studentsList){
                writer.write(student.getID() + "," + student.getName() + "," + student.getEmail());
                writer.newLine();
            }
        }catch(IOException e){
            System.out.println("Error while saving students to file: " + e.getMessage());
        }
    }

    //Reads File line by line
    private void loadFromFile(){
        File file = new File(fileName);
        if(!file.exists()){
            return;   // first run: nothing saved yet, start with an empty list
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null){
                if(line.trim().isEmpty()){
                    continue;                      // skip blank lines
                }
                String[] parts = line.split(",", 3);   // id, name, email
                if(parts.length < 3){
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                try{
                    int id = Integer.parseInt(parts[0].trim());
                    studentsList.add(new Student(parts[1].trim(), id, parts[2].trim()));
                }catch(NumberFormatException e){
                    System.out.println("Skipping line with invalid ID: " + line);
                }
            }
        }catch(IOException e){
            System.out.println("Error while reading students from file: " + e.getMessage());
        }
    }

    public boolean addStudent(Student student){
        for(Student existing : studentsList){
            if(existing.getID() == student.getID()){
                return false;
            }
        }

        this.studentsList.add(student);
        saveToFile();
        return true;
    }

    public Student removeStudent(String studentName){
        for(Student student: studentsList){
            if(student.getName().equals(studentName)){
                Student removedStudent = student;
                studentsList.remove(student);
                saveToFile();
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
        saveToFile();
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
