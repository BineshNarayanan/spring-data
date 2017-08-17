package com.ai.training.persistence;

import com.ai.training.model.EmployeeEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IEmployeeEntityRepository extends CrudRepository<EmployeeEntity,String> {

    @EntityGraph(value = "allJoins", type = EntityGraph.EntityGraphType.LOAD)
    EmployeeEntity findById(String id);
}
