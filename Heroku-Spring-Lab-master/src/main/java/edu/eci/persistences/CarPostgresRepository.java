package edu.eci.persistences;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.eci.models.Car;
import edu.eci.persistences.repositories.ICarRepository;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Qualifier("CarPostgresRepository")
public class CarPostgresRepository implements ICarRepository{
    
    private String dbUrl = "jdbc:postgresql://ec2-23-21-106-241.compute-1.amazonaws.com:5432/d9eeqhj3l2fiom";

    @Autowired
    private DataSource dataSourceCar;

    
    @Override
    public Car getCarByLicence(String licence) {
        String query = "SELECT * FROM cars WHERE licence = " + licence;

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Car car = new Car();
            car.setId(UUID.fromString(rs.getString("id")));
            car.setBrand(rs.getString("brand"));
            car.setLicencePlate(licence);
            return car;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Car> findAll() {
        String query = "SELECT * FROM cars";
        List<Car> cars = new ArrayList<>();

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                Car car = new Car();
                car.setId(UUID.fromString(rs.getString("id")));
                car.setBrand(rs.getString("brand"));
                car.setLicencePlate("licence");
                cars.add(car);
            }
            return cars;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Car find(UUID id) {
        String query = "SELECT * FROM cars WHERE id = " + id;
        
        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);            
            Car car = new Car();
            car.setId(UUID.fromString(rs.getString("id")));
            car.setBrand(rs.getString("brand"));
            car.setLicencePlate("licence");
            return car;
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public UUID save(Car entity) {
        String query = "INSERT INTO cars (id, licence, brand) VALUES("+entity.getId().toString()+","+entity.getLicencePlate()+","+entity.getBrand()+")";

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return UUID.fromString(rs.getString("id"));
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Car entity) {
        String query = "UPDATE cars SET licence = "+entity.getLicencePlate()+", brand = "+entity.getBrand()+" WHERE id = " + entity.getId().toString();

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Car o) {
        String query = "DELETE FROM users WHERE id = "+ o.getId().toString();

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void remove(Long id) {
        String query = "DELETE FROM users WHERE id = "+ UUID.fromString(id.toString());

        try(Connection connection = dataSourceCar.getConnection()){
            Statement stmt = connection.createStatement();
            stmt.executeQuery(query);            
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Bean
    public DataSource dataSourceCar() throws SQLException {
        if (dbUrl == null || dbUrl.isEmpty()) {
            return new HikariDataSource();
        } else {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbUrl);
            config.setDriverClassName("org.postgresql.Driver");
            config.setUsername("bszwywqnnjeono");
            config.setPassword("2e4d9b9e5106e662cc32fa07f0a0e5320e3e96822df9bfc77ec56526ea49c664");
            config.setMaximumPoolSize(11);
            return new HikariDataSource(config);
        }
    }
    
}
