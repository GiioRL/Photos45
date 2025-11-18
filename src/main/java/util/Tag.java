package main.java.util;

import java.io.Serializable;

public class Tag implements Serializable{

    private String type;
    private String value;
    private boolean multiValue;

    public Tag(String type, String value) {
        this.type = type;
        this.value = value;
        this.multiValue = true;
    }

    public Tag(String type, String value, boolean multiValue) {
        this.type = type;
        this.value = value;
        this.multiValue = multiValue;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public boolean getMultiValue() {
        return multiValue;
    }

    public boolean equals(Object o) { // only checks tag types
        if (o == null || !(o instanceof Tag)) {
            return false;
        } else {
            Tag other = (Tag)o;
            return type.equals(other.getType());
        }
    }

    public boolean tagEquals(Tag other) {
        if (value == null || other == null)
            return false;
        return value.equals(other.getValue());
    }

    public String toString() {
        return type + ": " + value + ", multiValue = " + multiValue;
    }
}