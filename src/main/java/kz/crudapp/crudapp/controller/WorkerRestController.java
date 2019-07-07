package kz.crudapp.crudapp.controller;

import kz.crudapp.crudapp.entity.Worker;
import kz.crudapp.crudapp.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/workers")
public class WorkerRestController {
    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<Worker> findAllWorkers() {
        return workerService.findAllWorker();
    }

    @GetMapping(value = "/{id}")
    public Worker findWorkerById(@PathVariable("id") Long id) {
        return workerService.findWorkerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createWorker(@RequestBody Worker worker) {
        return workerService.createWorker(worker);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateWorker(@PathVariable("id") Long id, @RequestBody Worker worker) {
        workerService.updateWorker(id, worker);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWorker(@PathVariable("id") Long id) {
        workerService.deleteWorkerById(id);
    }
}
