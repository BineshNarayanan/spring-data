package com.ai.training.persistence;

import com.ai.training.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeRepository extends CrudRepository<Employee,String>,JpaRepository<Employee,String>,JpaSpecificationExecutor<Employee> {

    Employee findById(String id);

    Employee findByFirstName(String firstName);

    @Query(value = "SELECT A.* FROM EMPLOYEE A LEFT OUTER JOIN EMP_CONTACT_DETAILS B ON A.ID = B.EMP_ID WHERE A.FIRST_NAME = :firstName AND A.LAST_NAME = :lastName",nativeQuery = true)
    Employee findByFirstNameAndLastName(@Param("firstName") String firstName,@Param("lastName") String lastName);

    @Modifying
    @Query(value = "UPDATE EMPLOYEE SET STATUS = 0,UPDATE_BY = :updateBy WHERE ID = :employeeId",nativeQuery = true)
    void softDelete(@Param("employeeId") String employeeId,@Param("updateBy") String updateBy);
}
