package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.sql.Date;

/**
 * Contiene los comentarios hechos a los casos de incapacidad.
 * 
 * @author alfredo.martinezr
 *
 */
public class Comentario extends Audit {

	private Long id;
	private String texto;
	private Date fechaComentario;
	private Date fechaRevaloracion;
	private Long idClasificacion;
	private Long idIncapacidad;
	private Long idUsuario;
	private Long idComentarioPadre;

	public Comentario() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getFechaComentario() {
		return fechaComentario;
	}

	public void setFechaComentario(Date fechaComentario) {
		this.fechaComentario = fechaComentario;
	}

	public Date getFechaRevaloracion() {
		return fechaRevaloracion;
	}

	public void setFechaRevaloracion(Date fechaRevaloracion) {
		this.fechaRevaloracion = fechaRevaloracion;
	}

	public Long getIdClasificacion() {
		return idClasificacion;
	}

	public void setIdClasificacion(Long idClasificacion) {
		this.idClasificacion = idClasificacion;
	}

	public Long getIdIncapacidad() {
		return idIncapacidad;
	}

	public void setIdIncapacidad(Long idIncapacidad) {
		this.idIncapacidad = idIncapacidad;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdComentarioPadre() {
		return idComentarioPadre;
	}

	public void setIdComentarioPadre(Long idComentarioPadre) {
		this.idComentarioPadre = idComentarioPadre;
	}

}
