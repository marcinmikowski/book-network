package pl.mikus.streams;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

class MinMax {

    @Test
    void min() {
        List<Integer> numbers = List.of(5, 4, 9, 1, 4, 9, 8, 90);
        Integer min = numbers.stream().min(Integer::compareTo).get();
        System.out.println(min);
    }

    @Test
    void max() {
        List<Integer> numbers = List.of(5, 4, 9, 1, 4, 9, 8, 90);
        Integer max = numbers.stream().max(Comparator.naturalOrder()).get();
        System.out.println(max);
    }
}
