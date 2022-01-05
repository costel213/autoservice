package com.costelmitrea.autoservice.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;

import java.util.*;

@Setter
@Getter
@NoArgsConstructor
public class Client extends Person{

    private Set<Car> cars;

    public Set<Car> getCarsInternal() {
        if(this.cars == null) {
            this.cars = new HashSet<>();
        }

        return this.cars;
    }

    public void setCarsInternal(Set<Car> cars) {
        this.cars = cars;
    }

    public List<Car> getCars() {
        List<Car> sortedCars = new ArrayList<>(getCarsInternal());
        PropertyComparator.sort(sortedCars,
                new MutableSortDefinition("model", true, true));
        return Collections.unmodifiableList(sortedCars);
    }

    public void addCar(Car car) {
        if(car.isNew()) {
            getCarsInternal().add(car);
        }

        car.setOwner(this);
    }

    public Car getCar(String model) {
        return getCar(model, false);
    }

    public Car getCar(String model, boolean ignoreNew) {
        model = model.toLowerCase();
        for(Car car : getCarsInternal()) {
            if(!ignoreNew || !car.isNew()) {
                String compModel = car.getModel();
                compModel = compModel.toLowerCase();
                if(compModel.equals(model)) {
                    return car;
                }
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id", this.getId()).append("new", this.isNew())
                .append("firstName", this.getFirstName()).append("lastName", this.getLastName())
                .append("address", this.getAddress()).append("city", this.getCity())
                .append("telephone", this.getTelephone()).toString();
    }
}
