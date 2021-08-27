package com.simbirsoft.simbircontrol.service.filter;

import com.simbirsoft.simbircontrol.entity.Task;
import com.simbirsoft.simbircontrol.enums.State;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

public class TaskFilter implements Specification<Task> {
    List<Condition> conditions;

    public TaskFilter(List<Condition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = conditions.stream().map((condition) -> buildPredicate(condition, root, criteriaQuery, criteriaBuilder)).collect(Collectors.toList());

        if (predicates.size() > 1) {
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        } else {
            return predicates.get(0);
        }
    }


    public Predicate buildPredicate(Condition condition, Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        switch(condition.comparison) {
            case eq:
                return buildEqualsPredicate(condition, root, criteriaQuery, criteriaBuilder);
            default:
                return buildEqualsPredicate(condition, root, criteriaQuery, criteriaBuilder);
        }
    }


    private Predicate buildEqualsPredicate(Condition condition, Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (condition.type == Type.state) {
            condition.value = State.valueOf((String) condition.value);
        }
        return criteriaBuilder.equal(root.get(condition.field), condition.value);
    }


}
