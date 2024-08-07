package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FirstTest {

    @Test
    public void stringTest() {

        var v = new Validator();

        var schema = v.string();

        assertTrue(schema.isValid("")); // true
        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("")); // false
        assertTrue(schema.isValid("what does the fox say")); // true
        assertTrue(schema.isValid("hexlet")); // true

        assertTrue(schema.contains("wh").isValid("what does the fox say")); // true
        assertTrue(schema.contains("what").isValid("what does the fox say")); // true
        assertFalse(schema.contains("whatthe").isValid("what does the fox say")); // false

        assertFalse(schema.isValid("what does the fox say")); // false

        var schema1 = v.string();
        assertTrue(schema1.minLength(10).minLength(4).isValid("Hexlet")); // true

    }

    @Test
    public void numberTest() {

        var v = new Validator();

        var schema = v.number();

        assertTrue(schema.isValid(5)); // true

// Пока не вызван метод required(), null считается валидным
        assertTrue(schema.isValid(null)); // true
        assertTrue(schema.positive().isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(10)); // true

// Потому что ранее мы вызвали метод positive()
        assertFalse(schema.isValid(-10)); // false
//  Ноль — не положительное число
        assertFalse(schema.isValid(0)); // false

        schema.range(5, 10);

        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false

    }

    @Test
    public void mapTest() {

        var v = new Validator();

        var schema = v.map();

        assertTrue(schema.isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertTrue(schema.isValid(new HashMap<>())); // true
        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        assertTrue(schema.isValid(data)); // true

        schema.sizeof(2);

        assertFalse(schema.isValid(data));  // false
        data.put("key2", "value2");
        assertTrue(schema.isValid(data)); // true

    }

    @Test
    public void deepMapTest() {
        var v = new Validator();

        var schema = v.map();

// shape позволяет описывать валидацию для значений каждого ключа объекта Map
// Создаем набор схем для проверки каждого ключа проверяемого объекта
// Для значения каждого ключа - своя схема
        Map<String, BaseSchema<String>> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "firstName" и "lastName"
// Имя должно быть строкой, обязательно для заполнения
        schemas.put("firstName", v.string().required());
// Фамилия обязательна для заполнения и должна содержать не менее 2 символов
        schemas.put("lastName", v.string().required().minLength(2));

// Настраиваем схему `MapSchema`
// Передаем созданный набор схем в метод shape()
        schema.shape(schemas);

// Проверяем объекты
        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1)); // true

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2)); // false

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3)); // false
    }


    /*@Test
    public void predicateStringTest() {

        var v = new Validator();

        var schema = v.string();

        assertTrue(schema.isValidPredicate("")); // true
        assertTrue(schema.isValidPredicate(null)); // true

        schema.requiredPredicate();

        assertFalse(schema.isValidPredicate(null)); // false
        assertFalse(schema.isValidPredicate("")); // false
        assertTrue(schema.isValidPredicate("what does the fox say")); // true
        assertTrue(schema.isValidPredicate("hexlet")); // true

        assertTrue(schema.containsPredicate("wh").isValidPredicate("what does the fox say")); // true
        assertTrue(schema.containsPredicate("what").isValidPredicate("what does the fox say")); // true
        assertFalse(schema.containsPredicate("whatthe").isValidPredicate("what does the fox say")); // false

        assertFalse(schema.isValidPredicate("what does the fox say")); // false

        var schema1 = v.string();
        assertTrue(schema1.minLengthPredicate(10).minLengthPredicate(4).isValidPredicate("Hexlet")); // true

    }*/

    /*@Test
    public void numberPredTest() {

        var v = new Validator();

        var schema = v.number();

        assertTrue(schema.isValidPredicate(5)); // true

// Пока не вызван метод required(), null считается валидным
        assertTrue(schema.isValidPredicate(null)); // true
        assertTrue(schema.positivePredicate().isValidPredicate(null)); // true

        schema.requiredPredicate();

        assertFalse(schema.isValidPredicate(null)); // false
        assertTrue(schema.isValidPredicate(10)); // true

// Потому что ранее мы вызвали метод positive()
        assertFalse(schema.isValidPredicate(-10)); // false
//  Ноль — не положительное число
        assertFalse(schema.isValidPredicate(0)); // false

        schema.rangePredicate(5, 10);

        assertTrue(schema.isValidPredicate(5)); // true
        assertTrue(schema.isValidPredicate(10)); // true
        assertFalse(schema.isValidPredicate(4)); // false
        assertFalse(schema.isValidPredicate(11)); // false

    }*/

}
