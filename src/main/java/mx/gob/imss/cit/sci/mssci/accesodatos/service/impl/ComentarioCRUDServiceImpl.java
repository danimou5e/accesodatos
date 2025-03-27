package mx.gob.imss.cit.sci.mssci.accesodatos.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.SCICClasificacionRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.SCITCasoRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.SCITComentarioRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository.SCITUsuarioRepository;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCICClasificacion;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITCaso;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITComentario;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.SCITUsuario;
import mx.gob.imss.cit.sci.mssci.accesodatos.service.ComentarioCRUDService;

@Service
public class ComentarioCRUDServiceImpl implements ComentarioCRUDService{
	
	@Autowired
	private SCICClasificacionRepository clasificacionRepository;
	
	@Autowired
	private SCITComentarioRepository comentarioRepository;
	
	@Autowired
	private SCITCasoRepository casoRepository;
	
	@Autowired
	private SCITUsuarioRepository usuarioRepository;
	
	@Override
	public List<SCITComentario> buscaTodosLosComentariosDelCaso(Long idCaso){
		SCITCaso caso = casoRepository.findByIdCaso(idCaso);
		List<SCITComentario> comentarios = comentarioRepository.findByIdCasoOrderByFecGeneracionComentarioDesc(caso.getIdCaso());		
		return comentarios;
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public SCITComentario buscaComentarioPorId(Long idComentario) {
		SCITComentario comentario = comentarioRepository.findByIdComentario(idComentario);
		return comentario;

	}
	
	@Override
	@Transactional(readOnly = true)
	public SCITCaso buscaCasoPorId(Long idCaso) {
		SCITCaso caso = casoRepository.findByIdCaso(idCaso);
		return caso;
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public SCITUsuario buscaUsuarioPorId(Long IdUsuario) {
		SCITUsuario usuario = usuarioRepository.findByIdUsuario(IdUsuario);
		return usuario;

	}
	
	@Override
	@Transactional(readOnly = true)
	public SCITUsuario buscaUsuario(String matricula) {
		SCITUsuario usuarioEmiteComentario = usuarioRepository.findByMatriculaUsuario(matricula);
		return usuarioEmiteComentario;

	}
	
	
	
	@Override
	@Transactional(readOnly = true)
	public SCICClasificacion buscaClasificacion(Long idClasificacion) {
		SCICClasificacion clasificacionComentario = clasificacionRepository.findByIdClasificacion(idClasificacion);
		return clasificacionComentario;
		
	}
	
	@Override
	public SCITComentario guardaComentario(SCITComentario comentario) {
		SCITComentario comentarioGuardado = comentarioRepository.save(comentario);
		return comentarioGuardado;

	}
	
	

}
