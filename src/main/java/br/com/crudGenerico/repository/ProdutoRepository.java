package br.com.crudGenerico.repository;

import br.com.crudGenerico.models.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Onde ficam as operações CRUD e consultas
 *
 * @author gianlucampos
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p FROM Produto p WHERE lower(p.nome) like :nome")
    public List<Produto> findByNome(@Param("nome") String nome);
}
