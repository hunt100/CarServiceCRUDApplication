package kz.crudapp.crudapp.repository;

import kz.crudapp.crudapp.entity.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MyUserRepository extends CrudRepository<MyUser, Long> {
    MyUser findByUsername (String username);
}
