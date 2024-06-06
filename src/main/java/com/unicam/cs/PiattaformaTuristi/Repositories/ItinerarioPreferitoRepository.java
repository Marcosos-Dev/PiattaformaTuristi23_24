package com.unicam.cs.PiattaformaTuristi.Repositories;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.ItinerarioPreferito;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiPreferito;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItinerarioPreferitoRepository extends CrudRepository<ItinerarioPreferito,String> {
    @Query("SELECT i FROM ItinerarioPreferito i WHERE i.utente.idUtente = :idUtente")
    List<ItinerarioPreferito> GetPreferitiUtente(@Param("idUtente") int idUtente);
}
