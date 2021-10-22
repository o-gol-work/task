package ru.olejkai.task_vsr.services.dbAccessServices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.olejkai.task_vsr.entity.DepartmentEntity;
import ru.olejkai.task_vsr.repository.DepartmentRepository;
import java.util.Collection;

@Service
@Transactional
public class DepartmentServices {
    private DepartmentRepository departmentRepository;

    public DepartmentServices(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    private static final Logger LOG = LoggerFactory.getLogger(DepartmentServices.class);


    public Collection<DepartmentEntity> findAllByOrderByTitleAsc(){
        return departmentRepository.findAllByOrderByTitleAsc();
    }


    public DepartmentEntity findById(Long id){
            return departmentRepository.findById(id).get();
    }


    public DepartmentEntity findByIdGetParent(Long id){
        return departmentRepository.findById(id).get().getParent();

    }


    public Collection<DepartmentEntity> findByIdGetChildren(Long id){
        return departmentRepository.findById(id).get().getChildren();

    }


    public Collection<DepartmentEntity> getAllChildren(Long id){
           return departmentRepository.getAllChildren(id);

    }


    public Collection<DepartmentEntity> getAllParent(Long id) {
        return departmentRepository.getAllParent(id);
    }
}
