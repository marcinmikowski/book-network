package pl.mikus.fun.callback;

import java.util.Objects;
import java.util.function.Consumer;

public class CallbackExample {

    public static void main(String[] args) {
        hello("Marcin", null, (firstName) -> {
            System.out.println(firstName + " should have lastName");
        });
    }

    static void hello(String firstName, String lastName, Consumer<String> callback) {
        System.out.println(firstName);
        if (Objects.nonNull(lastName) && !lastName.isBlank()) {
            System.out.println(lastName);
        } else {
            callback.accept(firstName);
        }
    }
}
