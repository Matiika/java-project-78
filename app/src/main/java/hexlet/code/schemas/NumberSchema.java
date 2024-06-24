package hexlet.code.schemas;

public class NumberSchema {


    private boolean required;
    private boolean positive;
    private Integer rangeMin;
    private Integer rangeMax;

    public NumberSchema() {
        this.required = false;
        this.positive = false;
        this.rangeMin = null;
        this.rangeMax = null;
    }

    public boolean isValid(Integer number) {
        boolean valid = true;

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

        return valid;
    }


    public NumberSchema positive() {
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
    }

}

