package com.example.springbootdemo.repository;

import com.example.springbootdemo.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,String> {
    @Query(value = "SELECT * FROM employee WHERE department = ?", nativeQuery = true)
    public List<EmployeeEntity> findByDepartment(String department);
}
