package boss;
/**
 * 实习生
 * 工资 = hourlyRate * workingHours
 */
class InternEmployee extends Employee {

    private double hourlyRate;
    private int workingHours;

    public InternEmployee(String name, double hourlyRate, int workingHours) {
        super(name);
        this.hourlyRate = hourlyRate;
        this.workingHours = workingHours;
    }

    @Override
    public double calculateSalary() {
        double re = 0;
        if(judge()){
            re = hourlyRate * (double)workingHours;
        }
        return re;
    }

    // workingHours 是否需要校验（如不能超过 160）？
    public boolean judge() {
        return workingHours<160;
    }

    public void printEmployeeInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Type: " + this.getClass().getSimpleName());
        System.out.println("Hourly Rate: " + hourlyRate);
        System.out.println("Working Hours: " + workingHours);
    }
}

