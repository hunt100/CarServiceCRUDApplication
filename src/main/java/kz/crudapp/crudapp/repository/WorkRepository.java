package kz.crudapp.crudapp.repository;

import kz.crudapp.crudapp.entity.Work;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends CrudRepository<Work, Long> {
    List<Work> getAllByCar_Id(Long carId);
    List<Work> getAllByWorker_Id(Long workerId);
}
