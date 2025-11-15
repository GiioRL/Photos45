package main.java.util;

public class Tag {

    private String type;
    private String value;

    public Tag(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}