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

    // TODO：思考 name 为什么是 private？
    // 防止name被外部类直接访问
    // TODO：是否需要提供 getter / setter？
    // 需要getter来获取name，不需要setter修改name
    public String getName() {
        return name;
    }
}