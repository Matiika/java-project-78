package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    private boolean positive;
    private Integer rangeMin;
    private Integer rangeMax;

    public NumberSchema() {
        this.required = false;
        this.positive = false;
        this.rangeMin = null;
        this.rangeMax = null;
    }

    @Override
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
    }

    /*public boolean isValid(Integer number) {
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
    }*/


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

    /*@Override
    public String toString() {
        return "NumberSchema{" +
                "requierd=" + super.required +
                ", positive=" + positive +
                ", rangeMin=" + rangeMin +
                ", rangeMax=" + rangeMax +
                '}';
    }*/
}

