package com.lhind.internship.jdbc.repository;

import com.lhind.internship.jdbc.mapper.EmployeeMapper;
import com.lhind.internship.jdbc.model.Employee;
import com.lhind.internship.jdbc.model.enums.EmployeeQuery;
import com.lhind.internship.jdbc.util.JdbcConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements Repository<Employee, Integer> {

    private static final String SELECT_ALL = "SELECT * FROM employees;";
    private static final String SELECT_BY_ID = "SELECT * FROM employees WHERE employeeNumber = ?;";
    private static final String EXISTS_QUERY = "SELECT COUNT(*) FROM employees WHERE emoloyeeNumber = 7";
    private static final String INSERT_QUERY = "INSERT INTO employees(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE employees SET lastName = ?, firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ?";
    private static final String DELETE_QUERY = "DELETE FROM employees WHERE employeeNumber = ?";

    private EmployeeMapper employeeMapper = EmployeeMapper.getInstance();

    @Override
    public List<Employee> findAll() {
        final List<Employee> response = new ArrayList<>();
        try (final Connection connection = JdbcConnection.connect();
             final PreparedStatement statement = connection.prepareStatement(EmployeeQuery.SELECT_ALL.getQuery())) {
            final ResultSet result = statement.executeQuery();
            while (result.next()) {
                response.add(employeeMapper.toEntity(result));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return response;
    }

    @Override
    public Optional<Employee> findById(final Integer id) {
        try (final Connection connection = JdbcConnection.connect();
             final PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            final ResultSet result = statement.executeQuery();

            if (result.next()) {
                final Employee employee = employeeMapper.toEntity(result);
                return Optional.of(employee);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public boolean exists(final Integer integer) {
        try (final Connection connection = JdbcConnection.connect();
             final PreparedStatement statement = connection.prepareStatement(EXISTS_QUERY)) {
            statement.setInt(1, integer);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error in exists: " + e.getMessage());

        }
        return false;
    }

    @Override
    public Employee save(final Employee employee) {
        if (exists(employee.getEmployeeNumber())) {
            // Update existing employee
            final String updateQuery = "UPDATE employees SET lastName = ?, firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ?";
            try (Connection connection = JdbcConnection.connect();
                 PreparedStatement statement = connection.prepareStatement(updateQuery)) {
                statement.setString(1, employee.getLastName());
                statement.setString(2, employee.getFirstName());
                statement.setString(3, employee.getExtension());
                statement.setString(4, employee.getEmail());
                statement.setString(5, employee.getOfficeCode());
                if (employee.getReportsTo() != null) {
                    statement.setInt(6, employee.getReportsTo());
                } else {
                    statement.setNull(6, java.sql.Types.INTEGER);
                }
                statement.setString(7, employee.getJobTitle());
                statement.setInt(8, employee.getEmployeeNumber());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return employee;
                }
            } catch (SQLException e) {
                System.err.println("Error updating employee: " + e.getMessage());
            }
        } else {
            final String insertQuery = "INSERT INTO employees(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo, jobTitle) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (Connection connection = JdbcConnection.connect();
                 PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setInt(1, employee.getEmployeeNumber());
                statement.setString(2, employee.getLastName());
                statement.setString(3, employee.getFirstName());
                statement.setString(4, employee.getExtension());
                statement.setString(5, employee.getEmail());
                statement.setString(6, employee.getOfficeCode());
                if (employee.getReportsTo() != null) {
                    statement.setInt(7, employee.getReportsTo());
                } else {
                    statement.setNull(7, java.sql.Types.INTEGER);
                }
                statement.setString(8, employee.getJobTitle());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    return employee;
                }
            } catch (SQLException e) {
                System.err.println("Error inserting employee: " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void delete(final Integer integer) {
        final String deleteQuery = "DELETE FROM employees WHERE employeeNumber = ?";
        try (Connection connection = JdbcConnection.connect();
             PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, integer);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.err.println("No employee found with id: " + integer);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
        }
    }
}
