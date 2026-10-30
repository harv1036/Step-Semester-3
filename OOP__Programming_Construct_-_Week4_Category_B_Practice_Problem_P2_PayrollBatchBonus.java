class PayrollBatchBonus {

    public static void main(String[] args) {
        String[] empIds = {"E-101", "E-102", "E-103", "E-104"};
        double[] startingSalaries = {40000, 55000, 62000, 48000};

        PayrollEmployee[] employees = new PayrollEmployee[empIds.length];
        for (int i = 0; i < empIds.length; i++) {
            employees[i] = new PayrollEmployee(empIds[i], startingSalaries[i]);
        }

        for (PayrollEmployee employee : employees) {
            employee.raiseSalary(5000);
            System.out.println(employee.empId + " | Final Salary: Rs " + employee.salary);
        }
    }
}

class PayrollEmployee {
    String empId;
    double salary;

    PayrollEmployee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    void raiseSalary(double salary) {
        this.salary = this.salary + salary;
    }
}
