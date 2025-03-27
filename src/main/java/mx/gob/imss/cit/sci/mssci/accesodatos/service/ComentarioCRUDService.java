package mx.gob.imss.cit.sci.mssci.accesodatos.service;

import java.util.List;

import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCICClasificacion;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITCaso;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITComentario;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITUsuario;

public interface ComentarioCRUDService {
	
	List<SCITComentario> buscaTodosLosComentariosDelCaso(Long idCaso);
	
	SCITComentario buscaComentarioPorId(Long idComentario);
	
	SCITCaso buscaCasoPorId(Long idCaso);
	
	SCITUsuario buscaUsuarioPorId(Long IdUsuario);
	
	SCITUsuario buscaUsuario(String matricula);
	
	SCICClasificacion buscaClasificacion(Long idClasificacion);
	
	SCITComentario guardaComentario(SCITComentario comentario);
	
	
	
	

}
