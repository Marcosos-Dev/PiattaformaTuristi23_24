package com.unicam.cs.PiattaformaTuristi.Repositories;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiPreferito;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.UtenteAutenticato;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PoiPreferitoRepository extends CrudRepository<PoiPreferito,String> {
    @Query("SELECT p FROM PoiPreferito p WHERE p.utente.idUtente = :idUtente")
    List<PoiPreferito> GetPreferitiUtente(@Param("idUtente") int idUtente);
}
