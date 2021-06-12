package br.com.fiap.bootcamp.repository;

import br.com.fiap.bootcamp.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Future;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {

    @Async
    @Query(value = "SELECT * FROM usuario WHERE email=:email", nativeQuery = true)
    Future<UsuarioEntity> findByEmail(@Param("email") String email);
}
