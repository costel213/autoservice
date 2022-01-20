package com.costelmitrea.autoservice.bootstrap;


import com.costelmitrea.autoservice.model.*;
import com.costelmitrea.autoservice.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SimpleGrantedAuthorityService simpleGrantedauthorityService;
    private final CarTypeService carTypeService;
    private final SpecialtyService specialtyService;
    private final MechanicService mechanicService;
    private final UserService userService;
    private final ExperienceService experienceService;

    public DataLoader(SimpleGrantedAuthorityService simpleGrantedauthorityService, CarTypeService carTypeService, SpecialtyService specialtyService, MechanicService mechanicService, UserService userService, ExperienceService experienceService) {
        this.simpleGrantedauthorityService = simpleGrantedauthorityService;
        this.carTypeService = carTypeService;
        this.specialtyService = specialtyService;
        this.mechanicService = mechanicService;
        this.userService = userService;
        this.experienceService = experienceService;
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
        user.setLastName("User");
        user.setPassword("$2a$12$1iu7KkdKQeM8N7EQ5UHyauwOoBCHVbomDkcIMlYkWfuzpbLyWRna.");
        user.setUserName("user");
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
    }
}
