package boss;
class SalesEmployee extends Employee {

    private double baseSalary;
    private double salesAmount;

    public SalesEmployee(String name, double baseSalary, double salesAmount) {
        super(name);
        this.baseSalary = baseSalary;
        this.salesAmount = salesAmount;
    }

    // TODO：补全销售员工的工资计算逻辑
    // 提示：提成比例可自定义，如 5%
    @Override
    public double calculateSalary() {
        // TODO
        double re = 0;
        re = baseSalary + salesAmount * 0.05;
        return re;
    }

    // TODO：是否需要重写 printEmployeeInfo？
    // 不需要，该方法在父类中已经实现，且适用于该类
    // TODO：baseSalary 和 salesAmount 是否应该直接暴露？
    // 不应该，防止数据被修改
}