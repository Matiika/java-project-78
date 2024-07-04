package hexlet.code.schemas;

import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {

    private boolean positive;
    private Integer rangeMin;
    private Integer rangeMax;

    public NumberSchema() {
        this.required = false;
        this.positive = false;
        this.rangeMin = null;
        this.rangeMax = null;
    }

    public NumberSchema required() {
        this.required = true;
        Predicate<Object> reqPredicate = value -> {
            Integer numberValue = (Integer) value;
            return numberValue != null;
        };
        addCheck("required", reqPredicate);
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        Predicate<Object> positPredicate = value -> {
            if (!required && value == null) {
                return true;
            }

            if (value == null) {
                return false;
            }

            Integer numberValue = (Integer) value;
            return numberValue >= 1;
        };
        addCheck("positive", positPredicate);
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;
        Predicate<Object> ranPredicate = value -> {
            Integer numberValue = (Integer) value;
            return numberValue >= rangeMin && numberValue <= rangeMax;
        };
        addCheck("positive", ranPredicate);
        return this;
    }

}

