package com.springjpacrud01.controller;

import com.springjpacrud01.model.Department;
import com.springjpacrud01.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(value = "/department")
public class  DepController {
    private final DepartmentService departmentService;

    public DepController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @GetMapping(value = "/departments")
    public List<Department> getDepList(){
        List<Department> departments = departmentService.getAll();
        System.out.println("\033[0;33m" + "-------------" + departments.toString() + "---------" + "\033[0m");
        return departments;
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET, params = "id")
    public ResponseEntity<Department> getById(@RequestParam("id") Long id){
        //System.out.println("\033[0;33m" + "-----" + departmentService.findById(id).get().getEmployeeHashSet().size() + "-----" + "\033[0m");
        Optional<Department> department = departmentService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(department.orElseThrow(NoSuchElementException::new));
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
