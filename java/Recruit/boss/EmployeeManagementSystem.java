package boss;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试入口类
 */
public class EmployeeManagementSystem {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new SalesEmployee("Alice", 5000, 200000));
        employees.add(new InternEmployee("Bob", 80, 120));
        employees.add(new SalesEmployee("Cindy", 6000, 150000));

        for (Employee employee : employees) {
            employee.printEmployeeInfo();
            System.out.println("Salary: " + employee.calculateSalary());
            System.out.println("----------------------------");
        }
    }
}
