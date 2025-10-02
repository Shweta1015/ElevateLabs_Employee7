import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static final EmployeeDAO dao = new EmployeeDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true){
            System.out.println("\n--- Employee DB App ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Find Employee by ID");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1 -> add();
                case 2 -> viewAll();
                case 3 -> findById();
                case 4 -> update();
                case 5 -> delete();
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    private static void add() {
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Department: ");
        String dept = sc.nextLine();
        System.out.print("Salary: ");
        double salary = Double.parseDouble(sc.nextLine());
        Employee e = new Employee(name, email, dept, salary);
        boolean ok = dao.addEmployee(e);
        System.out.println(ok ? "Added successfully." : "Add failed.");
    }

    private static void viewAll() {
        List<Employee> list = dao.getAllEmployees();
        if (list.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            list.forEach(System.out::println);
        }
    }

    private static void findById() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Employee e = dao.getEmployeeById(id);
        System.out.println(e == null ? "Not found." : e);
    }

    private static void update() {
        System.out.print("Enter ID to update: ");
        int id = Integer.parseInt(sc.nextLine());
        Employee existing = dao.getEmployeeById(id);
        if (existing == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.print("New Name (" + existing.getName() + "): ");
        String name = sc.nextLine(); if (name.isBlank()) name = existing.getName();

        System.out.print("New Email (" + existing.getEmail() + "): ");
        String email = sc.nextLine(); if (email.isBlank()) email = existing.getEmail();

        System.out.print("New Department (" + existing.getDepartment() + "): ");
        String dept = sc.nextLine(); if (dept.isBlank()) dept = existing.getDepartment();

        System.out.print("New Salary (" + existing.getSalary() + "): ");
        String salInput = sc.nextLine();
        double salary = salInput.isBlank() ? existing.getSalary() : Double.parseDouble(salInput);

        Employee updated = new Employee(id, name, email, dept, salary);
        boolean ok = dao.updateEmployee(updated);
        System.out.println(ok ? "Updated successfully." : "Update failed.");
    }

    private static void delete() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean ok = dao.deleteEmployee(id);
        System.out.println(ok ? "Deleted." : "Delete failed or not found.");
    }
}


