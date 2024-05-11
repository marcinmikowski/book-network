package pl.mikus.streams;

import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Dictinct {

    @Test
    void distinct() {
        List<Integer> integers = List.of(1, 1, 1, 6, 6, 3, 3, 3, 3, 9, 1, 1, 0, 7, 6, 10);
        Collection<Integer> integerCollection = integers.stream().distinct().toList();
        System.out.println(integerCollection);
    }

    @Test
    void distinctBySet() {
        List<Integer> integers = List.of(1, 1, 1, 6, 6, 3, 3, 3, 3, 9, 1, 1, 0, 7, 6, 10);
        Set<Integer> integerSet = integers.stream().collect(Collectors.toUnmodifiableSet());
        System.out.println(integerSet);
    }

    @Test
    void distinctSimpler() {
        List<Integer> integers = List.of(1, 1, 1, 6, 6, 3, 3, 3, 3, 9, 1, 1, 0, 7, 6, 10);
        Set<Integer> integerSet = new HashSet<>(integers);
        System.out.println(integerSet);
    }
}
