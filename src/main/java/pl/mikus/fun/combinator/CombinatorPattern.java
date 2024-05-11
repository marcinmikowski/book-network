package pl.mikus.fun.combinator;

public class CombinatorPattern {
    public static void main(String[] args) {
        Person marcin = new Person("Marcin", "+0076777777", "marcin@gmail.pl");
        Person alex = new Person("Alex", "0076777777", "alex@gmail.pl");

        PersonRegistrationValidator validator = PersonRegistrationValidator
                .isEmailNotTaken()
                .and(PersonRegistrationValidator.isEmailValid())
                .and(PersonRegistrationValidator.isPhoneValid());

        System.out.println(validator.apply(marcin));
        System.out.println(validator.apply(alex));
    }
}
