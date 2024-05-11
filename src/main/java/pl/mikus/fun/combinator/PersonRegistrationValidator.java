package pl.mikus.fun.combinator;

import java.util.function.Function;

import static pl.mikus.fun.combinator.PersonRegistrationValidator.ValidationResult;
import static pl.mikus.fun.combinator.PersonRegistrationValidator.ValidationResult.*;

public interface PersonRegistrationValidator extends Function<Person, ValidationResult> {

    enum ValidationResult {
        SUCCESS,
        EMAIL_IS_NOT_VALID,
        EMAIL_TAKEN_ERROR,
        PHONE_IS_NOT_VALID,
    }

    static PersonRegistrationValidator isEmailValid() {
        return person -> person.email().contains("@") ? SUCCESS : EMAIL_IS_NOT_VALID;
    }

    static PersonRegistrationValidator isPhoneValid() {
        return person -> person.phone().contains("+") ? SUCCESS : PHONE_IS_NOT_VALID;
    }

    static PersonRegistrationValidator isEmailNotTaken() {
        return person -> SUCCESS;
    }

    default PersonRegistrationValidator and(PersonRegistrationValidator v) {
        return person -> {
            ValidationResult result = this.apply(person);
            return result == SUCCESS ? v.apply(person) : result;
        };
    }
}
