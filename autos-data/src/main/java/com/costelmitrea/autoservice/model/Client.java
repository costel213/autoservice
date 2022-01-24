package com.costelmitrea.autoservice.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client extends Person{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Car> cars;
    @Builder
    public Client(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Car> cars) {
        super(firstName, lastName, address, city, telephone);

        if(cars == null || cars.size() > 0) {
            this.cars = cars;
        }
    }

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
//        for(Car car : getCarsInternal()) {
//            if(car.getId() != null) {
//                sortedCars.add(car);
//            }
//        }
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
        return this.getFirstName() + " " + this.getLastName();
    }
}
