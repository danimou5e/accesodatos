package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository;

import org.springframework.data.repository.CrudRepository;

import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITUsuario;



public interface SCITUsuarioRepository extends CrudRepository<SCITUsuario, Long>{
	SCITUsuario findByMatriculaUsuario(String matriculaUsuario);
	
	SCITUsuario findByIdUsuario(Long idUsuario);
	
}
