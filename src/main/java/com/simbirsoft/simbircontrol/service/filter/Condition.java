package com.simbirsoft.simbircontrol.service.filter;

public class Condition {

    private Type type;
    private Object value;
    private String field;
    private Comparison comparison;

    public Condition() {
    }

    public Condition(Type type, Object value, String field, Comparison comparison) {
        this.type = type;
        this.value = value;
        this.field = field;
        this.comparison = comparison;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Comparison getComparison() {
        return comparison;
    }

    public void setComparison(Comparison comparison) {
        this.comparison = comparison;
    }

    @Override
    public String toString() {
        return "type = " + type.name() +
                ", value = " + value +
                ", field = " + field +
                ", comparison = " + comparison;
    }
}
