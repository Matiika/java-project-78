package hexlet.code;


public abstract class BaseSchema<T> {
    protected boolean required;

    public BaseSchema() {
        this.required = false;
    }

    public abstract boolean isValid(Object value);

    public BaseSchema<T> required() {
        return this;
    }

}
