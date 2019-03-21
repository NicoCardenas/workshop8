package edu.eci.services.contracts;

import edu.eci.models.Car;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface ICarServices {
    List<Car> list();
    Car create(Car car);
    Car get(String licence);    
    Car update(Car car);
    Car delete(UUID id);
}
