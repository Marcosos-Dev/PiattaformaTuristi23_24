package com.unicam.cs.PiattaformaTuristi.Repositories;

import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UtenteRepository extends CrudRepository<UtenteAutenticato,Integer> {
    @Query("SELECT u FROM UtenteAutenticato u WHERE u.username = :username")
    UtenteAutenticato ottieniUtenteTramiteUsername(@Param("username") String username);
}
