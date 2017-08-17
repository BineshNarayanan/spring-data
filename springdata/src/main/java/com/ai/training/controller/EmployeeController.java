package com.ai.training.controller;

import com.ai.training.dto.EmployeeDTO;
import com.ai.training.model.Employee;
import com.ai.training.model.EmployeeEntity;
import com.ai.training.model.criteria.EmployeeSearchCriteria;
import com.ai.training.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping(value = "/find-all", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findAll(){
        List<Employee> all = employeeService.findAll();
        return new ResponseEntity<List<Employee>>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-one/employee/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findOne(@PathVariable String employeeId){
        Employee one = employeeService.findOne(employeeId);
        return new ResponseEntity<Employee>(one, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-id/employee/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findById(@PathVariable String employeeId){
        Employee one = employeeService.findById(employeeId);
        return new ResponseEntity<Employee>(one, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-firstname/employee/firstname/{firstname}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findByFirstName(@PathVariable String firstname){
        Employee one = employeeService.findByFirstName(firstname);
        return new ResponseEntity<Employee>(one, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-first-and-last-name/employee/firstname/{firstname}/lastname/{lastname}", method = RequestMethod.GET)
    public ResponseEntity<Employee> findByFirstAndLastName(@PathVariable String firstname,@PathVariable String lastname){
        Employee one = employeeService.findByFirstNameAndLastName(firstname,lastname);
        return new ResponseEntity<Employee>(one, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-all-specification", method = RequestMethod.GET)
    public ResponseEntity<List<Employee>> findAllSpecification(@ModelAttribute EmployeeSearchCriteria criteria){
        List<Employee> all = employeeService.findAllSpecification(criteria);
        return new ResponseEntity<List<Employee>>(all, HttpStatus.OK);
    }

    //{"firstName":"Fabian","lastName":"Renoux","empStatus":1,"status":1,"createBy":"Binesh","updateBy":"Binesh"}
    //{"firstName":"Kevin","lastName":"Couton","empStatus":1,"status":1,"createBy":"Binesh","updateBy":"Binesh","contactDetails":[{"contactType":"Mobile","contactNumber":"1234567890","status":1},{"contactType":"Home","contactNumber":"3456789120","status":1},{"contactType":"Work","contactNumber":"2345678910","status":1}]}
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        Employee add = employeeService.add(employee);
        return new ResponseEntity<Employee>(add, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/employee/{employeeId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId){
        employeeService.delete(employeeId);
        return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/employee/{employeeId}/user/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> addEmployee(@PathVariable String employeeId,@PathVariable String userId){
        employeeService.softDelete(employeeId,userId);
        return new ResponseEntity<String>("Successfully Deleted", HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-id-no-join/employee/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeEntity> findByIdNoJoin(@PathVariable String employeeId){
        EmployeeEntity one = employeeService.findByIdNoJoin(employeeId);
        return new ResponseEntity<EmployeeEntity>(one, HttpStatus.OK);
    }

    @RequestMapping(value = "/find-by-id-no-join-dto/employee/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeDTO> findByIdNoJoinDTO(@PathVariable String employeeId){
        EmployeeDTO one = employeeService.findByIdNoJoinDTO(employeeId);
        return new ResponseEntity<EmployeeDTO>(one, HttpStatus.OK);
    }


    @RequestMapping(value = "/find-by-id-all-join/employee/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<EmployeeEntity> findByIdAllJoin(@PathVariable String employeeId){
        EmployeeEntity one = employeeService.findByIdAllJoin(employeeId);
        return new ResponseEntity<EmployeeEntity>(one, HttpStatus.OK);
    }

}
