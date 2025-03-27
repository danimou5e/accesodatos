package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.gob.imss.cit.sci.mssci.accesodatos.model.CometarioJPA;

public interface ComentarioRepository extends JpaRepository<CometarioJPA, Long> {
	List<CometarioJPA> findByIdCaso(Long idCaso);
}
