package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "scit_comentario")
public class CometarioJPA {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "cve_id_comentario")
	private Long idComentario;
	
	@Column( name = "cve_id_caso")
	private Long idCaso;
	
	@Column(name="cve_matricula_actualiza")
	private String matriculaActualiza;
	
	@Column(name="stp_actualiza_registro")
	private Date actualizaRegistro;
	
	
	
	public CometarioJPA() {
		//constructor
	}

	public Long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(Long idComentario) {
		this.idComentario = idComentario;
	}

	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	public String getMatriculaActualiza() {
		return matriculaActualiza;
	}

	public void setMatriculaActualiza(String matriculaActualiza) {
		this.matriculaActualiza = matriculaActualiza;
	}

	public Date getActualizaRegistro() {
		return actualizaRegistro;
	}

	public void setActualizaRegistro(Date actualizaRegistro) {
		this.actualizaRegistro = actualizaRegistro;
	}
	
	

	
}
