package br.gabriel.api.spring_mvc_api.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.gabriel.api.spring_mvc_api.model.Cliente;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    List<Cliente> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);
}
