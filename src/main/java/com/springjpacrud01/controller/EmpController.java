package com.springjpacrud01.controller;

import com.springjpacrud01.model.Department;
import com.springjpacrud01.model.Employee;
import com.springjpacrud01.service.DepartmentService;
import com.springjpacrud01.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/employee")
/*
Better to name it fully as it is e.g: EmployeeController.
As in big projects there might be EmploymentController.
And it confuses which one of them
 */
public class EmpController {
    //This is outdated
//    @Autowired
//    @Autowired
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    public EmpController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    //not needed
    //@ResponseBody
    @GetMapping("/employees")
    public List<Employee> getEmployeesList(){
        return employeeService.getAllEmployees();
    }

    @RequestMapping("/dep-employees")
    public List<Employee> retrieveEmpFromDep(@RequestParam("id") Long id){
        return employeeService.getAllEmployeesByDepartmentId(id);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET, params = "id")
    public ResponseEntity<?> findById(@RequestParam("id") Long id){
        Optional<Employee> employee = employeeService.findById(id);
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.OK).body(employee.orElseThrow(NullPointerException::new));
        }
        if (id > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(employee.orElseThrow(NoSuchElementException::new));
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee.orElseThrow());
    }

    @PostMapping("/{id}/department/{depId}")
    public Employee assignDepToEmployee(@PathVariable("id") Long id, @PathVariable("depId") Long depId){
        Employee employee = employeeService.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        Department department = departmentService.findById(depId).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        employee.setDepartment(department);
        return employeeService.saveEmployees(employee);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployees(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        employeeService.deleteEmployees(id);
    }


}
