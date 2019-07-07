package kz.crudapp.crudapp.repository;

import kz.crudapp.crudapp.entity.Worker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends CrudRepository<Worker,Long> {
}
