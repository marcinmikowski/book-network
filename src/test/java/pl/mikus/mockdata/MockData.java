package pl.mikus.mockdata;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import pl.mikus.model.Car;
import pl.mikus.model.Person;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MockData {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    public  static List<Car> getCarData() {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/test/resources/data/cars.json"));
        return objectMapper.readValue(jsonData, new TypeReference<List<Car>>() {});
    }

    @SneakyThrows
    public  static List<Person> getPeopleData() {
        byte[] jsonData = Files.readAllBytes(Paths.get("src/test/resources/data/people.json"));
        return objectMapper.readValue(jsonData, new TypeReference<List<Person>>() {});
    }

    public static void main(String[] args) {
        System.out.println(getCarData());
        System.out.println(getPeopleData());
    }
}
