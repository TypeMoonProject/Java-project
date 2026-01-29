package boss;
class SalesEmployee extends Employee {

    private double baseSalary;
    private double salesAmount;

    public SalesEmployee(String name, double baseSalary, double salesAmount) {
        super(name);
        this.baseSalary = baseSalary;
        this.salesAmount = salesAmount;
    }

    // 补全销售员工的工资计算逻辑
    // 提示：提成比例可自定义，如 5%
    private double commissionRate = 0.05;
    @Override
    public double calculateSalary() {
        //
        double re = 0;
        re = baseSalary + salesAmount * commissionRate;
        return re;
    }

    // 
    public void printEmployeeInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Type: " + this.getClass().getSimpleName());
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Sales Amount: " + salesAmount);
        System.out.println("Commission Rate: " + commissionRate);
    }
    // 
    // 不应该，防止数据被修改
}