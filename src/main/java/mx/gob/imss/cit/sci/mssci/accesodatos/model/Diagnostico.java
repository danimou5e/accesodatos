package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Diagnosticos en texto de la incapacidad.
 * 
 * @author alfredo.martinezr
 *
 */
public class Diagnostico {

	private Long id;
	private String medica;
	private Long idUsuario;
	private Long idIncapacidad;

	public Diagnostico() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMedica() {
		return medica;
	}

	public void setMedica(String medica) {
		this.medica = medica;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdIncapacidad() {
		return idIncapacidad;
	}

	public void setIdIncapacidad(Long idIncapacidad) {
		this.idIncapacidad = idIncapacidad;
	}

}
