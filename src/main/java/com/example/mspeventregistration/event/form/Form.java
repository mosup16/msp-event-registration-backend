package com.example.mspeventregistration.event.form;

import lombok.Data;

import javax.persistence.Embeddable;
import java.util.Collections;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

@Data
public class Form {

    private List<Field> fields;
}

@Data
class Field {
    private String name;
    private String value;
    private List<ConstraintParamsPair> constraints = new LinkedList<>();

    public void addConstraints(String name,Map<String,String> params){
        constraints.add(new ConstraintParamsPair(name,params));
    }

    public boolean validateConstraints() {
        return constraints.stream().map(constraintParamsPair -> SupportedConstraints
                .getByName(constraintParamsPair.getName())
                .isSatisfied(this.getValue(),constraintParamsPair.getParams())
        ).allMatch(isSatisfied -> isSatisfied);
    }

}


class Constraint<T> {
    private String name;
    private BiPredicate<T,Map<String,String>> predicate;
    private List<String> parametersNames;

    public List<String> getParametersNames(){
        return this.parametersNames;
    }

    public static <T> Constraint<T> of(BiPredicate<T , Map<String,String>> predicate) {
        return Constraint.of(Collections.emptyList(),predicate);
    }

    public static <T> Constraint<T> of(List<String> parametersNames , BiPredicate<T , Map<String,String>> predicate) {
        Constraint<T> c = new Constraint<>();
        c.predicate = predicate;
        c.parametersNames = parametersNames;
        return c;
    }

    public boolean isSatisfied(T value){
        return this.isSatisfied(value, Collections.emptyMap());
    }
    public boolean isSatisfied(T value, Map<String,String> params){
        return predicate.test(value,params);
    }
}