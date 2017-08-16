package com.ai.training.service.impl;

import com.ai.training.model.Employee;
import com.ai.training.model.EmployeeContactDetails;
import com.ai.training.model.criteria.EmployeeSearchCriteria;
import com.ai.training.persistence.IEmployeeRepository;
import com.ai.training.persistence.specification.EmployeeSearchSpecification;
import com.ai.training.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    public Employee findOne(String id) {
        return employeeRepository.findOne(id);
    }

    public List<Employee> findAll() {
        List<Employee> all = employeeRepository.findAll();
        return all;
    }

    public List<Employee> findAllSpecification(EmployeeSearchCriteria criteria) {
        List<Employee> all = employeeRepository.findAll(EmployeeSearchSpecification.findBySearchCriteria(criteria));
        return all;
    }

    public Employee findById(String id) {
        return employeeRepository.findById(id);
    }

    public Employee findByFirstName(String firstName) {
        Employee byFirstName = employeeRepository.findByFirstName(firstName);
        return byFirstName;
    }

    public Employee findByFirstNameAndLastName(String firstName, String lastName) {
        Employee byFirstNameAndLastName = employeeRepository.findByFirstNameAndLastName(firstName, lastName);
        return byFirstNameAndLastName;
    }

    public Employee add(Employee employee) {
        if(null != employee.getContactDetails()){
            Set<EmployeeContactDetails> contactDetails = employee.getContactDetails();
            for(EmployeeContactDetails e : contactDetails){
                e.setEmployee(employee);
            }
        }
        Employee save = employeeRepository.save(employee);
        return save;
    }

    public void delete(String employeeId) {
        employeeRepository.delete(employeeId);
    }

    @Transactional
    public void softDelete(String employeeId,String userId) {
        employeeRepository.softDelete(employeeId,userId);
    }

}
