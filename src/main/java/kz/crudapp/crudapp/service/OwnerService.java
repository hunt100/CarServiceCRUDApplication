package kz.crudapp.crudapp.service;

import kz.crudapp.crudapp.entity.Owner;
import kz.crudapp.crudapp.repository.OwnerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public OwnerRepository getOwnerRepository() {
        return ownerRepository;
    }

    public Long createOwner(Owner owner) {
        ownerRepository.save(owner);
        return owner.getId();
    }

    public List<Owner> findAllOwner() {
        return (List<Owner>) ownerRepository.findAll();
    }

    public Owner findOwnerById(Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (!owner.isPresent()) {
            throw new IllegalArgumentException("id - " + id);
        }
        return owner.get();
    }

    public void deleteOwnerById(Long id) {
        ownerRepository.deleteById(id);
    }

    public void updateOwner(Long id, Owner owner) {
        Optional<Owner> foundedOwner = ownerRepository.findById(id);
        if (foundedOwner.isPresent()) {
            owner.setId(id);
            ownerRepository.save(owner);
        }
        else {
            throw new IllegalArgumentException("Illegal Owner id - " + owner.getId());
        }
    }

    public List<Owner> findOwnersAddressStartWith(String startOfAddress) {
        return findAllOwner().stream().filter(c -> c.getAddress().startsWith(startOfAddress)).collect(Collectors.toList());
    }
}
