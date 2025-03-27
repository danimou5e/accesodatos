package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITComentario;


@Repository
public interface SCITComentarioRepository extends CrudRepository<SCITComentario, Long> {
	
	List<SCITComentario> findByIdCasoOrderByFecGeneracionComentarioDesc(Long idCaso);
	
	SCITComentario findByIdComentario(Long idComentario);
	

}
