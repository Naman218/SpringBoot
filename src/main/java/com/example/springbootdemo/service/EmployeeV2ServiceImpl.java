package com.example.springbootdemo.service;

import com.example.springbootdemo.entity.EmployeeEntity;
import com.example.springbootdemo.error.EmployeeNotFoundException;
import com.example.springbootdemo.model.Employee;
import com.example.springbootdemo.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeV2ServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        if(employee.getEmployeeId()==null || employee.getEmployeeId().isEmpty()){
            employee.setEmployeeId(UUID.randomUUID().toString());
        }
        EmployeeEntity entity= new EmployeeEntity();
        BeanUtils.copyProperties(employee,entity);
        employeeRepository.save(entity);
        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<Employee> employees= employeeEntityList.stream()
                                                    .map(employeeEntity -> {
                                                        Employee employee=new Employee();
                                                        BeanUtils.copyProperties(employeeEntity,employee);
                                                        return employee;
                                                    }).collect(Collectors.toList());
        return  employees;
    }

    @Override
    public List<Employee> getEmployeeByDepartment(String department) {
        List<EmployeeEntity> employeeEntityList= employeeRepository.findByDepartment(department);
        List <Employee> employees = employeeEntityList.stream()
                                                       .map(employeeEntity -> {
                                                           Employee employee= new Employee();
                                                           BeanUtils.copyProperties(employeeEntity,employee);
                                                           return employee;
                                                       }).collect(Collectors.toList());
        if(employees.isEmpty()){
            throw new EmployeeNotFoundException("Employee not found with department " + department);
        }
        return employees;
    }

    @Override
    public String deleteEmployeeById(String id) {
        return "";
    }
}
