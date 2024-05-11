package pl.mikus.streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

class JoiningStrings {

    @Test
    void joiningStrings() {
        List<String> strings = List.of("anna", "john", "marcos", "helena", "jasmin");
        StringBuilder output = new StringBuilder();
        for (var s : strings) {
            output.append(s.substring(0,1).toUpperCase());
            output.append(s.substring(1));
            output.append(", ");
        }

        System.out.println(output.substring(0, output.length() - 2));
    }

    @Test
    void joiningStringsWithStream() {
        List<String> strings = List.of("anna", "john", "marcos", "helena", "jasmin");
        String collected = strings.stream().map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining(", "));
        System.out.println(collected);
    }
}
