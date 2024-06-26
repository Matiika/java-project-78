package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public final class StringSchema extends BaseSchema<String> {

    private Integer minLength;
    private List<String> contains;

    public StringSchema() {
        super();
        this.contains = new ArrayList<>();
        this.minLength = null;
    }

    @Override
    public boolean isValid(Object value) {

        String str = (String) value;

        if (required) {
            if (str == null || str.isEmpty()) {
                return false;
            }
        } else if (str == null) {
            return true;
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

        return true;
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
