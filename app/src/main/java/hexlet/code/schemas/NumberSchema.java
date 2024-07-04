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

   /* @Override
    public boolean isValid(Object value) {

        Integer number = (Integer) value;

        if (required) {
            if (number == null) {
                return false;
            }
        } else if (number == null) {
            return true;
        }

        if (positive) {
            if (number < 1) {
                return false;
            }
        }

        if (rangeMin != null) {
            if (number < rangeMin || number > rangeMax) {
                return false;
            }
        }

        return true;
    }*/

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



    /*public NumberSchema positive() {
        this.positive = true;
        return this;
    }


    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;
        return this;
    }*/

}

