package com.ai.training.service;

import com.ai.training.model.Employee;
import com.ai.training.model.criteria.EmployeeSearchCriteria;

import java.util.List;

public interface IEmployeeService {

    Employee findOne(String id);

    List<Employee> findAll();

    List<Employee> findAllSpecification(EmployeeSearchCriteria criteria);

    Employee findById(String id);

    Employee findByFirstName(String firstName);

    Employee findByFirstNameAndLastName(String firstName,String lastName);

    Employee add(Employee employee);

    void delete(String employeeId);

    void softDelete(String employeeId,String userId);
}
