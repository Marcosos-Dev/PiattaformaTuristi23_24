package com.unicam.cs.PiattaformaTuristi.Repositories;

import com.unicam.cs.PiattaformaTuristi.Model.Comune;
import com.unicam.cs.PiattaformaTuristi.Model.Entities.PoiGenerico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComuneRepository extends CrudRepository<Comune,String> {
}
