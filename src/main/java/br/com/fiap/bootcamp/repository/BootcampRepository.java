package br.com.fiap.bootcamp.repository;

import br.com.fiap.bootcamp.entity.BootcampEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;

@Repository
public interface BootcampRepository extends CrudRepository<BootcampEntity, Long> {

    @Async
    @Query(value = "SELECT * FROM bootcamp WHERE nome=:nome", nativeQuery = true)
    Future<BootcampEntity> findByName(@Param("nome") String nome);
}
