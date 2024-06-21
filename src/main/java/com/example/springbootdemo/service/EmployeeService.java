package com.example.springbootdemo.service;

import com.example.springbootdemo.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee);

    List<Employee> getAllEmployees();


    List<Employee> getEmployeeByDepartment(String department);

    String deleteEmployeeById(String id);
}
