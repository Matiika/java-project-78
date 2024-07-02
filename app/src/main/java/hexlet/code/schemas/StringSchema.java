package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

    private Integer minLength;
    private List<String> contains;

    public StringSchema() {
        super();
        this.contains = new ArrayList<>();
        this.minLength = null;
    }

    /*@Override
    public boolean isValid(Object value) {

        String str = (String) value;

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

        return true;
    }*/

    public StringSchema contains(String str) {
        this.contains.add(str);
        Predicate<Object> containsAllPredicate = value -> {
            String strValue = (String) value;
            if (!contains.isEmpty()) {
                for (String contain : contains) {
                    if (!strValue.contains(contain)) {
                        return false;
                    }
                }
            }
            return true;
        };
        addCheck("contains", containsAllPredicate);
        return this;
    }

    public StringSchema required() {
        this.required = true;
        Predicate<Object> reqPredicate = value -> {
            String strValue = (String) value;
            return strValue != null && !strValue.isEmpty();
        };
        addCheck("required", reqPredicate);
        return this;
    }

    public StringSchema minLength(Integer newMinLength) {
        this.minLength = newMinLength;
        Predicate<Object> minLengthPre = value -> {
            String strValue = (String) value;
            if (minLength != null) {
                return strValue.length() >= minLength;
            }
            return true;
        };
        addCheck("minLenth", minLengthPre);
        return this;
    }








   /* public StringSchema contains(String str) {
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
    }*/

}
