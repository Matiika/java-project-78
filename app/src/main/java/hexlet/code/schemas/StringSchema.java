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

}
