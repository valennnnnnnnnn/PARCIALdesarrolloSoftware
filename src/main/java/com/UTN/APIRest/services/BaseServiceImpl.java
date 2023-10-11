package com.UTN.APIRest.services;

import com.UTN.APIRest.entities.BaseEntidad;
import com.UTN.APIRest.entities.Persona;
import com.UTN.APIRest.repositories.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl <E extends BaseEntidad,ID extends Serializable> implements BaseService<E,ID>{
    protected BaseRepository<E,ID> baseRepository;

    public BaseServiceImpl(BaseRepository<E,ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    @Transactional
    public List<E> findAll() throws Exception {
        try {
            List<E> entities = baseRepository.findAll();
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
@Override
@Transactional
    public Page<E> findAll(Pageable pageable ) throws Exception {

        try {
            Page<E> entities = baseRepository.findAll ((org.springframework.data.domain.Pageable) pageable);
            return entities;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public E findById(ID id) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            return entityOptional.get();
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E save(E entity) throws Exception {
        try{
            entity = baseRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public E update(ID id, E entity) throws Exception {
        try{
            Optional<E> entityOptional = baseRepository.findById(id);
            E entityUpdate = entityOptional.get();
            entityUpdate = baseRepository.save(entity);
            return entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(ID id) throws Exception {
        try{
            if(baseRepository.existsById(id)){
                baseRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public abstract Page<Persona> search(String filtro, org.springframework.data.domain.Pageable pageable) throws Exception;
}
