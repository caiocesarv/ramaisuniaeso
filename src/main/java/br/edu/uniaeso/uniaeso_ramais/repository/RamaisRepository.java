package br.edu.uniaeso.uniaeso_ramais.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.edu.uniaeso.uniaeso_ramais.model.Ramais;

public interface RamaisRepository extends CrudRepository<Ramais, Long> {
    List<Ramais> findByDescricaoContainingIgnoreCase(String descricao);
}
