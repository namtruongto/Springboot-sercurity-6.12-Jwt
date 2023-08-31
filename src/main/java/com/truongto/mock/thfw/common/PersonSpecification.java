package com.truongto.mock.thfw.common;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import com.truongto.mock.entities.Person;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class PersonSpecification implements Specification<Person> {

    @Override
    @Nullable
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toPredicate'");
    }
    
}
