package edu.eci.persistences.repositories;

import edu.eci.models.Car;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface ICarRepository extends DAO<Car, UUID> {
    Car getCarByLicence(String licence);
}
