package com.example.springbootdemo.controller;

import com.example.springbootdemo.model.Employee;
import com.example.springbootdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Qualifier("employeeServiceImpl")
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee save(@RequestBody Employee employee){
           return employeeService.save(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{department}")
    public List<Employee> getEmployeeByDepartment(@PathVariable String department){
      return employeeService.getEmployeeByDepartment(department);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployeeById(@PathVariable String id){
        return employeeService.deleteEmployeeById(id);
    }

}
