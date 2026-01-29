package boss;
/**
 * 员工父类
 */
abstract class Employee implements Payable{

    private String name;

    public Employee(String name) {
        this.name = name;
    }

    /**
     * 打印员工信息
     */
    public void printEmployeeInfo() {
        System.out.println("Name: " + name);
        System.out.println("Type: " + this.getClass().getSimpleName());
    }

    // 防止name被外部类直接访问
    // 需要getter来获取name，不需要setter修改name
    public String getName() {
        return name;
    }
}