package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public final class Validator {

    private Validator() {
    }

    public static Set<ValidationError> findErrors(Animal animal) {
        Set<ValidationError> validationErrors = new HashSet<>();

        if (animal.name().isEmpty() || animal.name().isBlank()) {
            validationErrors.add(new ValidationError("Name should contain words", "name"));
        }
        if (animal.age() < 0) {
            validationErrors.add(new ValidationError("Age should be positive number", "age"));
        }
        if (animal.height() <= 0) {
            validationErrors.add(new ValidationError("Height should be positive number", "height"));
        }
        if (animal.weight() <= 0) {
            validationErrors.add(new ValidationError("Weight should be positive number", "weight"));
        }

        return validationErrors;
    }
}
