import java.util.Scanner;

public class UserInterface {
    private Scanner scan;
    private StudentManager studentManager;

    public UserInterface(Scanner scanner){
        this.scan = scanner;
        studentManager = new StudentManager("students.txt");
    }

    public void start(){
        System.out.println("Welcome to Student Management System!");
        while(true){
            System.out.println("Commands List:\n" + "1.Add - Adds Students\n" + "2.Update - Updates Student Information\n"
                +"3.Delete - Deletes Student Information\n" + "4.View - Shows Student Information\n" + "5.View All - Shows List of All Students\n"
                +"6.Exit - Exits Program"
            );

            System.out.println("Input your command: ");
            String command = scan.nextLine().toLowerCase();

            if(command.equals("exit")){
                System.out.println("Exiting Program...");
                break;
            }

            if(command.equals("add")){
                System.out.println("Enter Student name: ");
                String name = scan.nextLine();
                System.out.println("Enter Student ID: ");
                int studentId = Integer.valueOf(scan.nextLine());
                System.out.println("Enter Student Email: ");
                String email = scan.nextLine();
                if(studentManager.addStudent(new Student(name, studentId, email))){
                    System.out.println("Student added successfully.");
                }else{
                    System.out.println("A student with ID: " + studentId + " already exists.");
                }
            }

            if(command.equals("update")){
                System.out.println("Enter name of Student to update information: ");
                String name = scan.nextLine();
                if(studentManager.studentPresent(name)){
                    System.out.println("Information to Update(Student ID, name or email)? ");
                    String parameter = scan.nextLine().toLowerCase();
                    System.out.println("New information to Update: ");
                    String updateIformation = scan.nextLine();
                    studentManager.updateStudentInfo(name, parameter, updateIformation);
                    System.out.println("Information has been succesfully updated.");
                }else{
                    System.out.println("Student with name " + name + " not present.");
                }
            }

            if(command.equals("delete")){
                System.out.println("Enter name of Student to be removed: ");
                String name = scan.nextLine();
                if(studentManager.studentPresent(name)){
                    studentManager.removeStudent(name);
                    System.out.println("Succesfully removed student from list.");
                }else{
                    System.out.println("Student with name " + name + " not present.");
                }
            }

            if(command.equals("view")){
                System.out.println("Enter name of Student to display information: ");
                String name = scan.nextLine();
                if(studentManager.studentPresent(name)){
                    studentManager.printStudentDetails(name);
                }else{
                    System.out.println("Student with name " + name + " not present.");
                }
            }

            if(command.equals("view all")){
                studentManager.printAllStudentDetails();
            }
        }
    }

}
