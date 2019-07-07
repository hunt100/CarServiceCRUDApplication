package kz.crudapp.crudapp.service;

import kz.crudapp.crudapp.entity.Worker;
import kz.crudapp.crudapp.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkerService {
    @Autowired
    private WorkerRepository workerRepository;

    public Long createWorker(Worker worker) {
        workerRepository.save(worker);
        return worker.getId();
    }

    public List<Worker> findAllWorker() {
        return (List<Worker>) workerRepository.findAll();
    }

    public Worker findWorkerById(Long id) {
        Optional<Worker> worker = workerRepository.findById(id);
        if (!worker.isPresent()) {
            throw new IllegalArgumentException("id - " + id);
        }
        return worker.get();
    }

    public void updateWorker(Long id,Worker worker) {
        Optional<Worker> foundedWorker = workerRepository.findById(id);
        if (foundedWorker.isPresent()) {
            worker.setId(id);
            workerRepository.save(worker);
        }
        else {
            throw new IllegalArgumentException("Illegal Worker id - " + worker.getId());
        }
    }

    public void deleteWorkerById(Long id) {
        workerRepository.deleteById(id);
    }
}
