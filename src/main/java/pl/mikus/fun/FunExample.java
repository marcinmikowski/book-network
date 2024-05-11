package pl.mikus.fun;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunExample {
    public static void main(String[] args) {
        System.out.println(incrementByOne(876));
        System.out.println(incrementByOneFunction.apply(876));

        List<Integer> listOfIntegers = List.of(1, 2, 3, 4).stream().map(FunExample::incrementByOne)
                .toList();

        System.out.println(listOfIntegers);

        System.out.println(incrementByOneFunction.andThen(doubleNumberFunction).apply(7));

        Function<Integer, Double> combinedFunction = incrementByOneFunction.andThen(doubleNumberFunction);

        System.out.println(combinedFunction.apply(10));

        System.out.println(personMapper.apply("Marcin", 18));

        sendEmailConsumer.accept("ala@ola.com");

        List<String> emails = List.of("aaaa@a.pl", "bbbb@b.pl", "cccc@c.pl");

        emails.forEach(sendEmailConsumer);

        sendEmail.accept("from@from.pl", "to@to.pl");

        System.out.println(isValidEmail("ee@ewe.pl"));

        System.out.println(isValidEmailFunction.and(containsDot).test("dssa@dsdw.pl"));

        List<String> someValidEmails = List.of("sadasd", "ann@dsad.pl", "1212@dsd", "uwoqriwq.pl");
        List<String> validEmails = someValidEmails.stream().filter(isValidEmailFunction.and(containsDot)).toList();
        System.out.println(validEmails);

        System.out.println(url.get());

        EmailValidator emailValidator = new EmailValidator();

        someValidEmails.forEach(e -> {
            boolean emailIsValid = emailValidator.test(e);
            System.out.printf("Email %s is valid: %S%n", e, emailIsValid);
        });

        String ola = "Ala %s ma kota %s".replaceAll("%s", "ola");
        System.out.println(ola);
    }

    static class EmailValidator implements Predicate<String> {

        @Override
        public boolean test(String s) {
            return s.contains("@");
        }
    }

    static Supplier<String> url = () -> "http://www.wp/pl";

    static Predicate<String> containsDot = email -> email.contains(".");

    static Predicate<String> isValidEmailFunction = email -> email.contains("@");

    static boolean isValidEmail(String email) {
        return email.contains("@");
    }

    static BiConsumer<String, String> sendEmail = (from, to) -> System.out.println("Sending mail from " + from + " to " + to);

    static Consumer<String> sendEmailConsumer = email -> System.out.println("Sending email to " + email);

    record Person(String name, int age) {}

    /*
    static BiFunction<String, Integer, Person> personMapper = (name, age) -> new Person(name, age);
    */

    static BiFunction<String, Integer, Person> personMapper = Person::new;

    static Function<Integer, Double> doubleNumberFunction = n -> {
        System.out.println("Doubles");
        return n * 2.0;
    };

    static Function<Integer, Integer> incrementByOneFunction = (n) -> {
        System.out.println("Increments");
        return n + 1;
    };

    static int incrementByOne(int n) {
        return n + 1;
    }

}
