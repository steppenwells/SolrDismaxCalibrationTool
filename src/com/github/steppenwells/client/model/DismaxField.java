package com.github.steppenwells.client.model;


public class DismaxField {

    private String fieldName;
    private double weight;
    private boolean inUse;

    public DismaxField(String fieldName, double weight, boolean inUse) {
        this.fieldName = fieldName;
        this.weight = weight;
        this.inUse = inUse;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
