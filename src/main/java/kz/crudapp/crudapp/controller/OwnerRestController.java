package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Owner;
import kz.crudapp.crudapp.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/owners")
public class OwnerRestController {
    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<Owner> findAllOwners() {
        return ownerService.findAllOwner();
    }

    @GetMapping(value = "/{id}")
    public Owner findOwnerById(@PathVariable("id") Long id) {
        return ownerService.findOwnerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createOwner(@RequestBody Owner owner) {
        return ownerService.createOwner(owner);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOwner(@PathVariable("id") Long id, @RequestBody Owner owner) {
        ownerService.updateOwner(id, owner);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOwner(@PathVariable("id") Long id) {
        ownerService.deleteOwnerById(id);
    }

    @GetMapping(value = "/search/byAddress/{address}")
    public List<Owner> findAllOwnersWithStartingAddress(@PathVariable("address")String address) {
        return ownerService.findOwnersAddressStartWith(address);
    }
}
