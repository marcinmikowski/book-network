package pl.mikus.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Car {

    private final Integer id;
    private final String make;
    private final String model;
    private final String color;
    private final Integer year;
    private final Double price;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Car(
            @JsonProperty("id") Integer id,
            @JsonProperty("make") String make,
            @JsonProperty("model") String model,
            @JsonProperty("color") String color,
            @JsonProperty("year") Integer year,
            @JsonProperty("price") Double price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.color = color;
        this.year = year;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Integer getYear() {
        return year;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}