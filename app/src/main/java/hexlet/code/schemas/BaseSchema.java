package hexlet.code.schemas;


public abstract class BaseSchema<T> {
    protected boolean required;

    public BaseSchema() {
        this.required = false;
    }

    public abstract boolean isValid(Object value);

}
