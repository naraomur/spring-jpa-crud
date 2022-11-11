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

   public List<Employee> getAllEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }
    public Employee editEmployees(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public void deleteEmployees(Long id) {
        employeeRepository.deleteById(id);
    }

}
