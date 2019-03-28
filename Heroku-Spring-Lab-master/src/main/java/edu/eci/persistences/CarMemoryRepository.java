package edu.eci.persistences;

import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static java.util.stream.Collectors.toList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("CarMemoryRepository")
public class CarMemoryRepository implements ICarRepository{

    public static List<Car> carsContainer;
    
    public static List<Car> getContainer(){
        if(CarMemoryRepository.carsContainer == null)
            CarMemoryRepository.carsContainer = new ArrayList<>();
        return CarMemoryRepository.carsContainer;
    }  

    @Override
    public Car getCarByLicence(String licence) {
        return CarMemoryRepository.getContainer()
                .stream()
                .filter(u -> licence.equals(u.getLicencePlate()))
                .findFirst()
                .get();
    }

    @Override
    public List<Car> findAll() {
        return CarMemoryRepository.getContainer();
    }

    @Override
    public Car find(UUID id) {
        Optional<Car> answer = CarMemoryRepository.getContainer()
                .stream()
                .filter(u -> id.equals(u.getId()))
                .findFirst();
        return answer.isPresent() ? answer.get() : null;
    }

    @Override
    public UUID save(Car entity) {
        CarMemoryRepository.getContainer().add(entity);
        return entity.getId();
    }

    @Override
    public void update(Car entity) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .map(u -> u.getId().equals(entity.getId()) ? entity : u)
                .collect(toList());
    }

    @Override
    public void delete(Car o) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .filter(u -> !u.getId().equals(o.getId()))
                .collect(toList());
    }

    @Override
    public void remove(Long id) {
        CarMemoryRepository.carsContainer = CarMemoryRepository.getContainer()
                .stream()
                .filter(u -> !u.getId().equals(id))
                .collect(toList());
    }
    
}
