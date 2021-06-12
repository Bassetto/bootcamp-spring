package br.com.fiap.bootcamp.repository;

import br.com.fiap.bootcamp.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Async
    @Query(value = "SELECT * FROM usuario WHERE email=:email", nativeQuery = true)
    UsuarioEntity findByEmail(@Param("email") String email);
}
