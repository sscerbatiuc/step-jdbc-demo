package com.step;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * // TODO: use try-with-resources in all the methods below
 */
public class EmployeeManagerDB {


    /**
     * Returns a database connection.
     * @return {@link Connection}
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/app";
        String username = "postgres";
        String password = "aozhnl";

        return DriverManager.getConnection(url, username, password);
    }

    /**
     * Reads all employees.
     * @return {@link List}
     */
    public List<Employee> read() {
        try {
            List<Employee> employees = new ArrayList<>();
            String sql = "select id, name, surname from manager.employee where id > ? order by id asc";

            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 0);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                employees.add(new Employee(id, name, surname));
            }
            preparedStatement.close();
            connection.close();
            return employees;
        } catch (SQLException ex) {
            System.out.printf("Error. Could not read employees. Reason: %s ", ex.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Creates an employee.
     * @param emp {@link Employee}
     */
    public void create(Employee emp) {
        try {
            String sqlTemplate = "INSERT INTO manager.employee(name, surname) values(?, ?)";

            Connection connection = getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(sqlTemplate);
            preparedStatement.setString(1, emp.getName());
            preparedStatement.setString(2, emp.getSurname());

            int rows = preparedStatement.executeUpdate();
            String message = rows == 0 ? "EROARE. Numar de randuri neasteptat." : "Succes. Am adaugat " + rows;
            System.out.println(message);

        } catch (SQLException ex) {
            System.out.printf("Error. Could not create employee. Reason: %s ", ex.getMessage());
        }

    }


    /**
     * Updates an employee
     * @param updatedEmployee {@link Employee}
     */
    public void update(Employee updatedEmployee) {
        try {
            String sql = "UPDATE manager.employee SET name=? , surname = ? where id=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedEmployee.getName());
            preparedStatement.setString(2, updatedEmployee.getSurname());
            preparedStatement.setInt(3, updatedEmployee.getId());

            int rows = preparedStatement.executeUpdate();
            String message = rows == 1 ? "Succes. Am adaugat " + rows : "EROARE. Numar de randuri neasteptat.";
            System.out.println(message);

        } catch (SQLException ex) {
            System.out.printf("Error. Could not update employee. Reason: %s ", ex.getMessage());
        }


    }


    /**
     * Deletes an employee.
     * @param modifiedEmployee {@link Employee}
     */
    public void delete(Employee modifiedEmployee) {
        try {
            String sql = "DELETE FROM manager.employee where id=?";
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, modifiedEmployee.getId());

            int rows = preparedStatement.executeUpdate();
            String message = rows == 1 ? "Succes. Am sters:  " + rows : "EROARE. Numar de randuri neasteptat.";
            System.out.println(message);

        } catch (SQLException ex) {
            System.out.printf("Error. Could not delete employee. Reason: %s ", ex.getMessage());
        }


    }


}
