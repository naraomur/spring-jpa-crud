package com.springjpacrud01.controller;

import com.springjpacrud01.model.Department;
import com.springjpacrud01.model.Employee;
import com.springjpacrud01.service.DepartmentService;
import com.springjpacrud01.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
public class EmpController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;
    @ResponseBody
    @RequestMapping(value = "/employees")
    public List<Employee> getEmployeesList(){
        return (List<Employee>) employeeService.getAllEmployees();
    }
    @GetMapping("/{id}")
    public Optional<Employee> findById(@PathVariable("id") Long id){
        return employeeService.findById(id);
    }
    @PostMapping("/dep-to-employee")
    @ResponseBody
    public Employee assignDepToEmployee(@RequestParam("id") Long id, @RequestParam("id") Long depId){
        Employee employee = employeeService.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("No such element in the given resource");
        });
        Department department = departmentService.findById(depId).orElseThrow(() -> {
            throw new NoSuchElementException("No such element in the given resource");
        });
        employee.setDepartment(department);
        return employeeService.editEmployees(employee);
    }

    @RequestMapping("/dep-employees")
    public List<Employee> retrieveEmpFromDep(@RequestParam("id") Long id){
        return employeeService.getAllEmployeesByDepartmentId(id);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.editEmployees(employee);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        employeeService.deleteEmployees(id);
    }


}
