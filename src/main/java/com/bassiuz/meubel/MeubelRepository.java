package com.bassiuz.meubel;

import java.util.List;

import com.bassiuz.meubel.domain.Meubel;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MeubelRepository extends CrudRepository<Meubel, Long> {

    @Query("SELECT m FROM Meubel m WHERE m.matchingPersonName=:name ORDER BY m.score DESC")
    public List<Meubel> fetchMeubelsByName(@Param("name") String name);

}