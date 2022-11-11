package com.springjpacrud01.controller;

import com.springjpacrud01.model.Department;
import com.springjpacrud01.service.DepartmentService;
import org.hibernate.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/department")
public class DepController {
    @Autowired
    private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping(value = "/departments")
    public List<Department> getDepList(){
        List<Department> departments = departmentService.getAll();
        return departments;
    }
    @PutMapping
    public Department addDep(@RequestBody Department department){
        return departmentService.save(department);
    }

    @ResponseBody
    @RequestMapping("/dep")
    public ResponseEntity<Department> getById(@RequestParam("id") Long id){
        Department department  = departmentService.findById(id).orElseThrow(()->new NoSuchElementException("Department with ID: " + id + " not found!"));
        return ResponseEntity.ok().body(department);
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect data type of entry")
    public ResponseEntity<String> handleTypeMismatchException(TypeMismatchException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).header("Invalid Entry", "Entry should be a whole number! e.g: 1").body(ex.getMessage());
    }

    @PostMapping
    public Department saveDep(@RequestBody Department department){
        return departmentService.save(department);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        departmentService.deleteById(id);
    }

}
