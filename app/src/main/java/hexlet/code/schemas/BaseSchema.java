package hexlet.code.schemas;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {

    protected Map<String, Predicate<Object>> checks = new LinkedHashMap<>();
    protected boolean required = false;

    protected final void addCheck(String checkName, Predicate<Object> predicate) {
        checks.put(checkName, predicate);
    }

    public BaseSchema() {
        this.required = false;
    }

    public final boolean isValid(Object value) {

        for (Predicate<Object> check : checks.values()) {
            if (!check.test(value)) {
                return false;
            }
        }
        return true;
    }

}
