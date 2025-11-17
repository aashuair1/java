
import java.util.*;

// Model Class
class Student {
    int id;
    String name;
    int age;

    Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age;
    }
}

public class java_lab_est {
    static ArrayList<Student> list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Student CRUD Application =====");
            System.out.println("1. Create (Add Student)");
            System.out.println("2. Read (View Students)");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            switch (ch) {
                case 1: addStudent(); break;
                case 2: viewStudents(); break;
                case 3: updateStudent(); break;
                case 4: deleteStudent(); break;
                case 5: System.exit(0);
                default: System.out.println("Invalid choice!");
            }
        }
    }

    // Create
    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // clear buffer
        
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Age: ");
        int age = sc.nextInt();

        list.add(new Student(id, name, age));
        System.out.println("Student Added Successfully!");
    }

    // Read
    static void viewStudents() {
        if (list.isEmpty()) {
            System.out.println("No Records Found!");
        } else {
            System.out.println("\n--- Student Records ---");
            for (Student s : list) {
                System.out.println(s);
            }
        }
    }

    // Update
    static void updateStudent() {
        System.out.print("Enter Student ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Student s : list) {
            if (s.id == id) {
                System.out.print("Enter New Name: ");
                s.name = sc.nextLine();
                System.out.print("Enter New Age: ");
                s.age = sc.nextInt();

                System.out.println("Record Updated Successfully!");
                return;
            }
        }
        System.out.println("Student Not Found!");
    }

    // Delete
    static void deleteStudent() {
        System.out.print("Enter Student ID to Delete: ");
        int id = sc.nextInt();

        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.id == id) {
                it.remove();
                System.out.println("Record Deleted Successfully!");
                return;
            }
        }
        System.out.println("Student Not Found!");
    }
}
