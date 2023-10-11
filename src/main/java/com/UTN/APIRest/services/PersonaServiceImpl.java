package com.UTN.APIRest.services;

import com.UTN.APIRest.entities.Persona;
import com.UTN.APIRest.repositories.BaseRepository;
import com.UTN.APIRest.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PersonaServiceImpl extends BaseServiceImpl<Persona,Long> implements PersonaService{

    @Autowired
    private PersonaRepository personaRepository;

    public PersonaServiceImpl(BaseRepository<Persona, Long> baseRepository, PersonaRepository personaRepository) {
        super(baseRepository);
        this.personaRepository = personaRepository;
    }

    public   List<Persona>search (String filtro ) throws Exception {

        try {
            List<Persona> personas = personaRepository.findByNombreContainingOrApellidoContaining(filtro,filtro);
            return personas;

        } catch (Exception e) {

            throw new Exception (e.getMessage());
        }

    }

    @Override
    public Page<Persona> search(String filtro, java.awt.print.Pageable pageable) throws Exception {
        return null;
    }

    @Override
    public Page<Persona> search(String filtro, Pageable pageable) throws Exception {

        try {
            Page <Persona> personas = (Page<Persona>) (Page<Persona>) (Page<Persona>) personaRepository.findByNombreContainingOrApellidoContaining(filtro,filtro,pageable);
            return personas;

        } catch (Exception e) {

            throw new Exception (e.getMessage());
        }

    }

}
