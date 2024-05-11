package pl.mikus.streams;

import org.junit.jupiter.api.Test;
import pl.mikus.mockdata.MockData;
import pl.mikus.model.Car;
import pl.mikus.model.Person;

import java.util.Comparator;
import java.util.List;

class SortingExample {

    @Test
    void sortingStreamOfElements() {
        List<Person> people = MockData.getPeopleData();
        people.stream()
                .map(Person::getFirstName)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    void reverseSortingOfElements() {
        List<Person> people = MockData.getPeopleData();
        people.stream()
                .map(Person::getFirstName)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    @Test
    void topTenMostExpensiveBlueCars() {
        List<Car> carData = MockData.getCarData();
        carData.stream()
                .filter(c -> c.getColor().equalsIgnoreCase("Blue"))
                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                .limit(10)
                .forEach(System.out::println);
    }
}
