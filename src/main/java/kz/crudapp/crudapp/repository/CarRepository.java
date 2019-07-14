package kz.crudapp.crudapp.repository;

import kz.crudapp.crudapp.entity.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository <Car, Long> {
    List<Car> getCarsByYearOfIssue (String year);
    List<Car> getCarsByModel (String model);
}
