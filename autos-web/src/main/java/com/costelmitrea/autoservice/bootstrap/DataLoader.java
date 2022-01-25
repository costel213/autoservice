package com.costelmitrea.autoservice.bootstrap;


import com.costelmitrea.autoservice.model.*;
import com.costelmitrea.autoservice.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
public class DataLoader implements CommandLineRunner {

    private final SimpleGrantedAuthorityService simpleGrantedauthorityService;
    private final CarTypeService carTypeService;
    private final SpecialtyService specialtyService;
    private final MechanicService mechanicService;
    private final UserService userService;
    private final ExperienceService experienceService;
    private final CarService carService;
    private final ClientService clientService;
    private final VisitService visitService;

    public DataLoader(SimpleGrantedAuthorityService simpleGrantedauthorityService, CarTypeService carTypeService,
                      SpecialtyService specialtyService, MechanicService mechanicService, UserService userService,
                      ExperienceService experienceService, CarService carService, ClientService clientService, VisitService visitService) {
        this.simpleGrantedauthorityService = simpleGrantedauthorityService;
        this.carTypeService = carTypeService;
        this.specialtyService = specialtyService;
        this.mechanicService = mechanicService;
        this.userService = userService;
        this.experienceService = experienceService;
        this.carService = carService;
        this.clientService = clientService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(simpleGrantedauthorityService.findAll().size() == 0 && carTypeService.findAll().size() == 0 &&
                specialtyService.findAll().size() == 0 && mechanicService.findAll().size() == 0 && userService.findAll().size() == 0)
            loadData();
    }

    private void loadData() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority();
        authority.setRole("ROLE_USER");
        SimpleGrantedAuthority savedAuthority = simpleGrantedauthorityService.save(authority);

        SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority();
        authority2.setRole("ROLE_ADMIN");
        SimpleGrantedAuthority savedAuthority2 = simpleGrantedauthorityService.save(authority2);

        User user = new User();
        user.setFirstName("Default");
        user.setLastName("Admin");
        user.setPassword("$2a$12$cOdrh.2MnYmlyHCnqHU9eOXz33dE.3tLxUN.cofatfATQX9jxOs2W");
        user.setUserName("defaultAdmin");
        user.getRoles().add(savedAuthority2);
        userService.save(user);

        CarType carType = new CarType();
        carType.setName("family car");
        CarType savedCarType = carTypeService.save(carType);

        CarType carType1 = new CarType();
        carType1.setName("off-road car");
        CarType savedCarType1 = carTypeService.save(carType1);

        CarType carType2 = new CarType();
        carType2.setName("pick-up truck");
        CarType savedCarType2 = carTypeService.save(carType2);

        CarType carType3 = new CarType();
        carType3.setName("sport car");
        CarType savedCarType3 = carTypeService.save(carType3);

        CarType carType4 = new CarType();
        carType4.setName("luxury car");
        CarType savedCarType4 = carTypeService.save(carType4);

        Specialty specialty = new Specialty();
        specialty.setName("Engine Repair");
        Specialty savedSpecialty = specialtyService.save(specialty);

        Specialty specialty1 = new Specialty();
        specialty1.setName("Engine Performance");
        Specialty savedSpecialty1 = specialtyService.save(specialty1);

        Specialty specialty2 = new Specialty();
        specialty2.setName("Automatic Transmission/Transaxle");
        Specialty savedSpecialty2 = specialtyService.save(specialty2);

        Specialty specialty3 = new Specialty();
        specialty3.setName("Brakes");
        Specialty savedSpecialty3 = specialtyService.save(specialty3);

        Specialty specialty4 = new Specialty();
        specialty4.setName("Electrical/Electronic Systems");
        Specialty savedSpecialty4 = specialtyService.save(specialty4);

        Specialty specialty5 = new Specialty();
        specialty5.setName("Heating and Air Conditioning");
        Specialty savedSpecialty5 = specialtyService.save(specialty5);

        Specialty specialty6 = new Specialty();
        specialty6.setName("Manual Drive Train and Axles");
        Specialty savedSpecialty6 = specialtyService.save(specialty6);

        Specialty specialty7 = new Specialty();
        specialty7.setName("Suspension and Steering");
        Specialty savedSpecialty7 = specialtyService.save(specialty7);

        Car car = new Car();
        car.setModel("Dacia Duster");
        car.setDateOfProduction(LocalDate.of(2021, Month.AUGUST, 23));
        car.setCarType(savedCarType);

        Car car2 = new Car();
        car2.setModel("Audi A5");
        car2.setDateOfProduction(LocalDate.of(2020, Month.FEBRUARY, 02));
        car2.setCarType(savedCarType);

        Car car3 = new Car();
        car3.setModel("Ferrari Roma");
        car3.setDateOfProduction(LocalDate.of(2022, Month.JANUARY, 11));
        car3.setCarType(savedCarType3);

        Car car4 = new Car();
        car4.setModel("Ford F150");
        car4.setDateOfProduction(LocalDate.of(2021, Month.APRIL, 30));
        car4.setCarType(savedCarType2);

        Client client = new Client();
        client.setFirstName("Peter");
        client.setLastName("Morrison");
        client.setAddress("76 School's St.");
        client.setCity("Falticeni");
        client.setTelephone("5768493021");
        car.setOwner(client);
        client.addCar(car);
        Car savedCar = carService.save(car);
        car2.setOwner(client);
        client.addCar(car2);
        Car savedCar2 = carService.save(car2);
        clientService.save(client);

        Client client2 = new Client();
        client2.setFirstName("Marcus");
        client2.setLastName("Murray");
        client2.setAddress("04 Court St.");
        client2.setCity("Suceava");
        client2.setTelephone("8675450393");
        car3.setOwner(client2);
        client2.addCar(car3);
        Car savedCar3 = carService.save(car3);
        car4.setOwner(client2);
        client2.addCar(car4);
        Car savedCar4 = carService.save(car4);
        clientService.save(client2);

        Mechanic mechanic = new Mechanic();
        mechanic.setAddress("23 Main St.");
        mechanic.setCity("Falticeni");
        mechanic.setFirstName("James");
        mechanic.setLastName("Carter");
        mechanic.setTelephone("489356956");
        mechanic.getSpecialtiesInternal().add(savedSpecialty);
        mechanic.getSpecialtiesInternal().add(savedSpecialty4);
        mechanicService.save(mechanic);

        Mechanic mechanic2 = new Mechanic();
        mechanic2.setAddress("65 Random St.");
        mechanic2.setCity("Falticeni");
        mechanic2.setFirstName("Helen");
        mechanic2.setLastName("Leary");
        mechanic2.setTelephone("079843525");
        mechanic2.getSpecialtiesInternal().add(savedSpecialty2);
        mechanic2.getSpecialtiesInternal().add(savedSpecialty6);
        mechanicService.save(mechanic2);

        Mechanic mechanic3 = new Mechanic();
        mechanic3.setAddress("15 Republic St.");
        mechanic3.setCity("Falticeni");
        mechanic3.setFirstName("Jane");
        mechanic3.setLastName("Davies");
        mechanic3.setTelephone("0798687583");
        mechanic3.getSpecialtiesInternal().add(savedSpecialty5);
        mechanic3.getSpecialtiesInternal().add(savedSpecialty7);
        mechanicService.save(mechanic3);

        Mechanic mechanic4 = new Mechanic();
        mechanic4.setAddress("03 Bakery's St.");
        mechanic4.setCity("Suceava");
        mechanic4.setFirstName("Daniel");
        mechanic4.setLastName("Marlon");
        mechanic4.setTelephone("0709879087");
        mechanic4.getSpecialtiesInternal().add(savedSpecialty4);
        mechanic2.getSpecialtiesInternal().add(savedSpecialty7);
        mechanicService.save(mechanic4);

        Visit visit = new Visit();
        visit.setDate(LocalDate.now());
        visit.setDescription("Change gear oil");
        visit.setMechanic(mechanic2);
        mechanic2.addVisit(visit);
        visit.setCar(car);
        car.addVisit(visit);
        visitService.save(visit);

        Visit visit2 = new Visit();
        visit2.setDate(LocalDate.of(2021, Month.AUGUST, 16));
        visit2.setDescription("Repair air conditioning system");
        visit2.setMechanic(mechanic);
        mechanic.addVisit(visit2);
        visit2.setCar(car3);
        car3.addVisit(visit2);
        visitService.save(visit2);

        Visit visit3 = new Visit();
        visit3.setDate(LocalDate.of(2022, Month.JANUARY, 13));
        visit3.setDescription("verify manual drive train");
        visit3.setMechanic(mechanic3);
        mechanic3.addVisit(visit3);
        visit3.setCar(car2);
        car2.addVisit(visit3);
        visitService.save(visit3);

        Visit visit4 = new Visit();
        visit4.setDate(LocalDate.of(2021, Month.NOVEMBER, 30));
        visit4.setDescription("Change tyres");
        visit4.setMechanic(mechanic4);
        mechanic4.addVisit(visit4);
        visit4.setCar(car4);
        car4.addVisit(visit4);
        visitService.save(visit4);

        Experience experience = new Experience();
        experience.setTimeInterval("02/2014-12/2018");
        experience.setPosition("Engine repair mechanic");
        experience.setMechanic(mechanic);
        mechanic.addExperience(experience);
        experienceService.save(experience);

        Experience experience1 = new Experience();
        experience1.setTimeInterval("01/2019-12/2021");
        experience1.setPosition("Engine performance mechanic");
        experience1.setMechanic(mechanic);
        mechanic.addExperience(experience1);
        experienceService.save(experience1);
    }
}
