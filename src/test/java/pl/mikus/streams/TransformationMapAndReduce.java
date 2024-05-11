package pl.mikus.streams;

import org.junit.jupiter.api.Test;
import pl.mikus.mockdata.MockData;
import pl.mikus.model.Car;
import pl.mikus.model.Person;
import pl.mikus.model.PersonDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class TransformationMapAndReduce {

    private static final List<List<String>> arrayListOfNames = List.of(
            List.of("Mariam", "Alex", "Ismail"),
            List.of("John", "Alesha", "Andre"),
            List.of("Susy", "Ali")
    );

    @Test
    void withoutFlatMap() {
        System.out.println(arrayListOfNames);
        System.out.println();

        List<String> result = new ArrayList<>();
        arrayListOfNames.forEach(result::addAll);

//        for (var listOfNames : arrayListOfNames) {
//            result.addAll(listOfNames);
//        }

        System.out.println(result);
    }

    @Test
    void withFlatMap() {
        System.out.println(arrayListOfNames);
        System.out.println();

        List<String> strings = arrayListOfNames.stream().flatMap(Collection::stream).toList();
        System.out.println(strings);
    }

    @Test
    void flatMapWithOptionals() {
        List<Optional<?>> optionals = List.of(
                Optional.of("Ala"),
                Optional.of("ma"),
                Optional.empty(),
                Optional.of("kota"),
                Optional.empty(),
                Optional.ofNullable(null)
        );

        System.out.println(optionals);

        List<?> result = optionals.stream().flatMap(Optional::stream).toList();
        System.out.println(result);
    }

    @Test
    void transformPeopleToDTOs() {
        List<Person> people = MockData.getPeopleData();
        List<PersonDTO> personDTOS1 = people.stream().map(p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge())).toList();
        personDTOS1.forEach(System.out::println);

        Function<Person, PersonDTO> transform = p -> new PersonDTO(p.getId(), p.getFirstName(), p.getAge());

        List<PersonDTO> personDTOS2 = people.stream().map(transform).toList();
        personDTOS2.forEach(System.out::println);

        List<PersonDTO> personDTOS3 = people.stream().map(PersonDTO::map).toList();
        personDTOS3.forEach(System.out::println);
    }

    @Test
    void findAverageCarPrice() {
        List<Car> carData = MockData.getCarData();
        System.out.println(
                carData.stream().mapToDouble(Car::getPrice).average().orElse(0)
        );
    }

    @Test
    void reduce() {
        int[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
        int sum1 = Arrays.stream(integers).reduce(0, (a, n) -> a + n);
        int sum2 = Arrays.stream(integers).reduce(0, Integer::sum);
        System.out.println(sum1);
        System.out.println(sum2);
    }
}
