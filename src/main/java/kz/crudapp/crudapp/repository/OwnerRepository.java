package kz.crudapp.crudapp.repository;

import kz.crudapp.crudapp.entity.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
