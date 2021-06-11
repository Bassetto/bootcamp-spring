package br.com.fiap.bootcamp.repository;

import br.com.fiap.bootcamp.entity.EspacoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;

@Repository
public interface EspacoRepository extends CrudRepository<EspacoEntity, Long> {

    @Async
    @Query(value = "SELECT * FROM espaco WHERE cep=:cep", nativeQuery = true)
    Future<EspacoEntity> findByCep(@Param("cep") int cep);
}
