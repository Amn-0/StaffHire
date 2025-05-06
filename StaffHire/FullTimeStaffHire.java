public class FullTimeStaffHire extends StaffHire {
    private double salary;
    private int weeklyFractionalHours;

    public FullTimeStaffHire(int vacancyNumber, String designation, String jobType,
                              String staffName, String joiningDate, String qualification,
                              String appointedBy, boolean joined, double salary,
                              int weeklyFractionalHours) {
        super(vacancyNumber, designation, jobType, staffName, joiningDate,
              qualification, appointedBy, joined);
        this.salary = salary;
        this.weeklyFractionalHours = weeklyFractionalHours;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (isJoined()) {
            this.salary = salary;
        } else {
            System.out.println("Cannot set salary. No staff has been appointed.");
        }
    }

    public int getWeeklyFractionalHours() {
        return weeklyFractionalHours;
    }

    public void setWeeklyFractionalHours(int weeklyFractionalHours) {
        this.weeklyFractionalHours = weeklyFractionalHours;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Salary: " + salary);
        System.out.println("Weekly Fractional Hours: " + weeklyFractionalHours);
    }
}
