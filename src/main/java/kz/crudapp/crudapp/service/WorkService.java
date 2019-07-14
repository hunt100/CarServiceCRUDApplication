package kz.crudapp.crudapp.service;

import kz.crudapp.crudapp.entity.Work;
import kz.crudapp.crudapp.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WorkService {
    @Autowired
    private WorkRepository workRepository;

    public List<Work> findAllWorks() {
        return (List<Work>) workRepository.findAll();
    }

    public Work findWorkById(Long id) {
        Optional<Work> foundedWork = workRepository.findById(id);
        if (!foundedWork.isPresent()) {
            throw new IllegalArgumentException("Wrong work id - " + id);
        }
        return foundedWork.get();
    }

    public Long createWork(Work work) {
        workRepository.save(work);
        return work.getId();
    }

    public void updateWork(Long id, Work work) {
        Optional<Work> foundedOptWork = workRepository.findById(id);
        if (foundedOptWork.isPresent()) {
            work.setId(id);
            workRepository.save(work);
        } else {
            throw new IllegalArgumentException("Invalid work id" + work.getId());
        }
    }

    public void deleteWork(Long id) {
        workRepository.deleteById(id);
    }

    public List<Work> getWorksByCarId(Long carId) {
        List<Work> works = workRepository.getAllByCar_Id(carId);
        if (works.isEmpty()) {
            throw new IllegalArgumentException("work is empty by carId - " + carId);
        }
        return works;
    }

    public List<Work> getWorksByWorkerId(Long workerId) {
        List<Work> works = workRepository.getAllByWorker_Id(workerId);
        if (works.isEmpty()) {
            throw new IllegalArgumentException("work is empty by workerId - " + workerId);
        }
        return works;
    }
}
