package edu.eci.services;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import edu.eci.services.contracts.ICarServices;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CarServices implements ICarServices {
    
    @Autowired
    @Qualifier("CarMemoryRepository")
    private ICarRepository carRepository;

    @Override
    public List<Car> list() {
        return carRepository.findAll();
    }

    @Override
    public Car create(Car car) {
        if(null == car.getId())
            throw new RuntimeException("Id invalid");
        else if(carRepository.find(car.getId()) != null)
            throw new RuntimeException("The car exists");
        else
            carRepository.save(car);
        return car;
    }

    @Override
    public Car get(String licence) {
        return carRepository.getCarByLicence(licence);
    }

    @Override
    public Car update(Car car) {
        carRepository.update(car);
        return carRepository.getCarByLicence(car.getLicencePlate());
    }

    @Override
    public Car delete(UUID id) {
        Car del = carRepository.find(id);
        carRepository.delete(del);
        return del;
    }
    
}
