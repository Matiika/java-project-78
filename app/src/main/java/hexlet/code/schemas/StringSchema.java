package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema {

    private boolean required;
    private Integer minLength;
    private List<String> contains;

    public StringSchema() {
        this.required = false;
        this.contains = new ArrayList<>();
        this.minLength = null;
    }

    public boolean isValid(String str) {
        boolean valid = true;

        if (required) {
            if (str == null || str.isEmpty()) {
                return false;
            }
        }

        if (!contains.isEmpty()) {
            for (var contain : contains) {
                if (!str.contains(contain)) {
                    return false;
                }
            }
        }

        if (minLength != null) {
            if (str.length() < minLength) {
                return false;
            }
        }

        return valid;
    }

    public StringSchema contains(String str) {
        this.contains.add(str);
        return this;
    }

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(Integer newMinLength) {
        this.minLength = newMinLength;
        return this;
    }

}
