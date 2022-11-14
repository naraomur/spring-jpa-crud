package com.springjpacrud01.service;

import com.springjpacrud01.model.Employee;
import com.springjpacrud01.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //read
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployees(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public List<Employee> getAllEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }

    public void deleteEmployees(Long id) {
        employeeRepository.deleteById(id);
    }

}
