package com.example.springbootdemo.service;

import com.example.springbootdemo.error.EmployeeNotFoundException;
import com.example.springbootdemo.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

     List<Employee>employees=new ArrayList<>();
    @Override
    public Employee save(Employee employee) {
        if(employee.getEmployeeId()==null || employee.getEmployeeId().isEmpty()){
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @Override
    public List<Employee> getEmployeeByDepartment(String department) {
        List<Employee> employeeList = employees
                .stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .collect(Collectors.toList());

        if (employeeList.isEmpty()) {
            throw new EmployeeNotFoundException("Employee not found with department " + department);
        }

        return employeeList;
    }

    @Override
    public String deleteEmployeeById(String id) {
        Employee employee=employees.stream().
                filter(e->e.getEmployeeId().equalsIgnoreCase(id)).findFirst().get();
        employees.remove(employee);
        return "Delete employee with id "+id;
    }

}
