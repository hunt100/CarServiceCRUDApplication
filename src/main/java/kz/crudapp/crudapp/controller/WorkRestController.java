package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Work;
import kz.crudapp.crudapp.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/works")
public class WorkRestController {
    @Autowired
    private WorkService workService;

    @GetMapping
    public List<Work> findAllWorks() {
        return workService.findAllWorks();
    }

    @GetMapping("/{id}")
    public Work findWorkById(@PathVariable("id") Long id) {
        return workService.findWorkById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createWork(@RequestBody Work work) {
        return workService.createWork(work);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateWork(@PathVariable("id") Long id, @RequestBody Work work) {
        workService.updateWork(id, work);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWork(@PathVariable("id") Long id) {
        workService.deleteWork(id);
    }

    @GetMapping(value = "/search/byCarId/{carId}")
    public List<Work> getWorksByCarId(@PathVariable(value = "carId")Long carId) {
        return workService.getWorksByCarId(carId);
    }

    @GetMapping(value = "/search/byWorkerId/{workerId}")
    public List<Work> getWorksByWorkerId(@PathVariable(value = "workerId")Long workerId) {
        return workService.getWorksByWorkerId(workerId);
    }
}
