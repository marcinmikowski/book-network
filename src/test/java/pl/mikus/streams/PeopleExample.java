package pl.mikus.streams;

import org.junit.jupiter.api.Test;
import pl.mikus.mockdata.MockData;
import pl.mikus.model.Person;

import java.util.ArrayList;
import java.util.List;

class PeopleExample {

    // Find people with age less or equal 18
    // Change implementation to get first 10 people

    @Test
    void imperativeApproach() {
        List<Person> people = MockData.getPeopleData();
        List<Person> peopleFound = new ArrayList<>();

        var counter = 0;
        for (var person : people) {
            if (person.getAge() <= 18) {
                peopleFound.add(person);
                if (++counter == 5) break;
            }
        }

        peopleFound.forEach(System.out::println);
    }

    @Test
    void declarativeApproach() {
        List<Person> people = MockData.getPeopleData();
        List<Person> peopleFound = people.stream()
                .filter(p -> p.getAge() <= 18)
                .limit(5)
                .toList();
        peopleFound.forEach(System.out::println);
    }
}
