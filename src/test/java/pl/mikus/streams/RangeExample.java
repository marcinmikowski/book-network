package pl.mikus.streams;

import org.junit.jupiter.api.Test;
import pl.mikus.mockdata.MockData;
import pl.mikus.model.Person;

import java.util.List;
import java.util.stream.IntStream;

class RangeExample {

    @Test
    void rangeStreamsTest() {
        IntStream.range(0, 10).forEach(System.out::println);
        System.out.println("-".repeat(20));
        IntStream.rangeClosed(0, 10).forEach(System.out::println);
    }

    @Test
    void rangeIterationList() {
        List<Person> peopleData = MockData.getPeopleData();
        IntStream.range(0, peopleData.size())
                .mapToObj(i -> peopleData.get(i))
                .forEach(System.out::println);
    }

    @Test
    void iterate() {
        IntStream.iterate(0, i -> i + 2)
                .limit(10)
                .forEach(System.out::println);
    }
}
