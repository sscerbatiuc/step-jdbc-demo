package com.step;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EmployeeManagerDB manager = new EmployeeManagerDB();

        manager.create(new Employee("Jane", "Smith"));

        List<Employee> emps = manager.read();
        for (Employee emp : emps) {
            System.out.println(emp);
        }

        Employee employee = emps.get(0);
        employee.setName("Modified Name");
        employee.setSurname("Modified Surname");
        manager.update(employee);

//        manager.delete(employee);
    }
}
