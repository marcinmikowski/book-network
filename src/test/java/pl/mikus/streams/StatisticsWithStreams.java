package pl.mikus.streams;

import org.junit.jupiter.api.Test;
import pl.mikus.mockdata.MockData;
import pl.mikus.model.Car;

import java.math.BigDecimal;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;

public class StatisticsWithStreams {

    @Test
    void countCars() {
        List<Car> carData = MockData.getCarData();
        long fordCounted = carData.stream().filter(c -> c.getMake().equalsIgnoreCase("Ford")).count();
        System.out.println(fordCounted);
    }

    @Test
    void minPrice() {
        List<Car> carData = MockData.getCarData();
        OptionalDouble min = carData.stream().mapToDouble(Car::getPrice).min();
        System.out.println(min.orElse(0));
    }

    @Test
    void maxPrice() {
        List<Car> carData = MockData.getCarData();
        OptionalDouble max = carData.stream().mapToDouble(Car::getPrice).max();
        System.out.println(max.orElse(0));
    }

    @Test
    void averagePrice() {
        List<Car> carData = MockData.getCarData();
        OptionalDouble avg = carData.stream().mapToDouble(Car::getPrice).average();
        System.out.println(avg.orElse(0));
    }

    @Test
    void sumPrice() {
        List<Car> carData = MockData.getCarData();
        double sum = carData.stream().mapToDouble(Car::getPrice).sum();
        System.out.println(BigDecimal.valueOf(sum));
    }


    @Test
    void statistics() {
        List<Car> carData = MockData.getCarData();
        DoubleSummaryStatistics doubleSummaryStatistics = carData.stream().mapToDouble(Car::getPrice)
                .summaryStatistics();

        System.out.println(doubleSummaryStatistics);
    }
}
