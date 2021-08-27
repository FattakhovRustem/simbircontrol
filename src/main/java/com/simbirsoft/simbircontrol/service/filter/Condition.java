package com.simbirsoft.simbircontrol.service.filter;

public class Condition {

    public Type type;
    public Object value;
    public String field;
    public Comparison comparison;

    public Condition() {
    }

    public Condition(Type type, Object value, String field, Comparison comparison) {
        this.type = type;
        this.value = value;
        this.field = field;
        this.comparison = comparison;
    }
}
