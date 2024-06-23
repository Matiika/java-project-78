package hexlet.code;

import hexlet.code.schemas.StringSchema;

public class Validator {
    boolean status;

    public StringSchema string() {
        return new StringSchema();
    }
}
