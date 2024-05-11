package pl.mikus.streams;

import org.junit.jupiter.api.Test;
import pl.mikus.mockdata.MockData;
import pl.mikus.model.Car;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingData {

    @Test
    void simpleGrouping() {
        List<Car> carData = MockData.getCarData();
        Map<String, List<Car>> groupedCars = carData.stream().collect(Collectors.groupingBy(Car::getMake));
        groupedCars.forEach((s, cars) -> {
            System.out.println("Make: " + s);
            cars.forEach(System.out::println);
            System.out.println("-".repeat(40));
        });
    }

    @Test
    void groupingAndCounting() {
        List<String> list = List.of("John",
                "John",
                "Mariam",
                "Alex",
                "Mohammado",
                "Mohammado",
                "Vincent",
                "Alex",
                "Alex"
        );

        Map<String, Long> collected = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        collected.forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });

    }
}
