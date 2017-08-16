package com.ai.training.persistence.specification;

import com.ai.training.model.Employee;
import com.ai.training.model.EmployeeContactDetails;
import com.ai.training.model.criteria.EmployeeSearchCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSearchSpecification {
    public static Specification<Employee> findBySearchCriteria(final EmployeeSearchCriteria criteria){

        return new Specification<Employee>() {
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                query.distinct(true);
                Join<Employee, EmployeeContactDetails> leftOuterJoin = root.join("contactDetails",JoinType.LEFT);
                if(!StringUtils.isEmpty(criteria.getFirstName())){
                    predicates.add(builder.and(builder.like(builder.lower(root.<String> get("firstName")), "%" + criteria.getFirstName().trim().toLowerCase() + "%")));
                }

                if(!StringUtils.isEmpty(criteria.getLastName())){
                    predicates.add(builder.and(builder.like(builder.lower(root.<String> get("lastName")), "%" + criteria.getLastName().trim().toLowerCase() + "%")));
                }

                if(!StringUtils.isEmpty(criteria.getEmpStatus())){
                    predicates.add(builder.and(builder.equal(root.<String> get("empStatus"), criteria.getEmpStatus())));
                }

                if(!StringUtils.isEmpty(criteria.getContactType())){
                    predicates.add(builder.and(builder.equal(leftOuterJoin.<String> get("contactType"), criteria.getContactType())));
                }

                if(!StringUtils.isEmpty(criteria.getContactNumber())){
                    predicates.add(builder.and(builder.equal(leftOuterJoin.<String> get("contactNumber"), criteria.getContactNumber())));
                }
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
