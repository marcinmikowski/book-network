package pl.mikus.streams;

import org.junit.jupiter.api.Test;
import pl.mikus.mockdata.MockData;
import pl.mikus.model.Car;

import java.util.List;
import java.util.function.Predicate;

class Filtering {

    @Test
    void filter1() {
        List<Car> carData = MockData.getCarData();
        List<Car> yellowCarsLessOrEqThen10K = carData.stream().filter(car -> car.getPrice() <= 10_000)
                .filter(car -> car.getColor().equals("Yellow"))
                .toList();

        yellowCarsLessOrEqThen10K.forEach(System.out::println);
    }

    @Test
    void filter2() {
        List<Car> carData = MockData.getCarData();
        Predicate<Car> carPredicateLessThen10K = car -> car.getPrice() <= 10_000;
        Predicate<Car> carPredicateYellow = car -> car.getColor().equals("Yellow");
        List<Car> yellowCarsLessOrEqThen10K = carData.stream()
                .filter(carPredicateLessThen10K.and(carPredicateYellow))
                .toList();

        yellowCarsLessOrEqThen10K.forEach(System.out::println);
    }

    @Test
    void filter3() {
        List<Integer> integers = List.of(2, 4, 6, 8, 9, 10);

        System.out.println("Using filter");
        integers.stream().filter(n -> n % 2 == 0).forEach(n -> System.out.print(n + " "));
    }

    @Test
    void takeWhile() {
        List<Integer> integers = List.of(2, 4, 6, 8, 9, 10, 12);

        System.out.println("Using filter");
        integers.stream().filter(n -> n % 2 == 0).forEach(n -> System.out.print(n + " "));

        System.out.println("\nUsing take-while");
        integers.stream().takeWhile(n -> n % 2 == 0).forEach(n -> System.out.print(n + " "));
    }

    @Test
    void dropWhile() {
        List<Integer> integers = List.of(2, 4, 6, 8, 9, 10, 12);

        System.out.println("Using filter");
        integers.stream().filter(n -> n % 2 == 0).forEach(n -> System.out.print(n + " "));

        System.out.println("\nUsing drop-while");
        integers.stream().dropWhile(n -> n % 2 == 0).forEach(n -> System.out.print(n + " "));
    }

    @Test
    void findFirst() {
        List<Integer> integers = List.of(2, 4, 6, 8, 9, 10, 12);

        Integer r1 = integers.stream().filter(n -> n == 6).findFirst().orElse(-1);
        System.out.println(r1);
        Integer r2 = integers.stream().filter(n -> n == 60).findFirst().orElse(-1);
        System.out.println(r2);
    }

    @Test
    void findAny() {
        List<Integer> integers = List.of(2, 4, 6, 8, 9, 10, 12);

        Integer r1 = integers.stream().filter(n -> n == 6).findAny().orElse(-1);
        System.out.println(r1);
        Integer r2 = integers.stream().filter(n -> n == 60).findAny().orElse(-1);
        System.out.println(r2);
    }

    @Test
    void allMatch() {
        List<Integer> evenIntegers = List.of(2, 4, 6, 8, 10, 12);
        System.out.println(evenIntegers.stream().allMatch(n -> n % 2 == 0));
        List<Integer> mixedIntegers = List.of(2, 4, 6, 7, 8, 10, 12);
        System.out.println(mixedIntegers.stream().allMatch(n -> n % 2 == 0));
    }

    @Test
    void anyMatch() {
        List<Integer> mixedIntegers = List.of(2, 4, 6, 8, 10, 12);
        System.out.println(mixedIntegers.stream().anyMatch(n -> n % 2 == 0));
        List<Integer> oddIntegers = List.of(3, 5, 7, 9);
        System.out.println(oddIntegers.stream().anyMatch(n -> n % 2 == 0));
    }

    @Test
    void pagination() {
        int pageSize = 2;
        List<Integer> integers = List.of(2, 4, 6, 8, 10, 12, 17);

        System.out.println(integers.stream().skip(0 * pageSize).limit(pageSize).toList());
        System.out.println(integers.stream().skip(1 * pageSize).limit(pageSize).toList());
        System.out.println(integers.stream().skip(2 * pageSize).limit(pageSize).toList());
        System.out.println(integers.stream().skip(3 * pageSize).limit(pageSize).toList());
        System.out.println(integers.stream().skip(4 * pageSize).limit(pageSize).toList());
    }
}
