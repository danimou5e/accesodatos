package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "scit_caso_agrupado")
public class CasoAgrupado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "cve_id_caso_agrupado")
	private Long idCasoAgrupado;
	
	@Column( name = "cve_id_caso_anterior")
	private Long idCasoAnterior;
	
	@Column( name = "cve_id_caso_nuevo")
	private Long idCasoNuevo;
	
	@Column(name="cve_matricula_alta")
	private String matriculaAlta;
	
	
	
	public CasoAgrupado() {
		//constructor
	}



	public Long getIdCasoAgrupado() {
		return idCasoAgrupado;
	}



	public void setIdCasoAgrupado(Long idCasoAgrupado) {
		this.idCasoAgrupado = idCasoAgrupado;
	}



	public Long getIdCasoAnterior() {
		return idCasoAnterior;
	}



	public void setIdCasoAnterior(Long idCasoAnterior) {
		this.idCasoAnterior = idCasoAnterior;
	}



	public Long getIdCasoNuevo() {
		return idCasoNuevo;
	}



	public void setIdCasoNuevo(Long idCasoNuevo) {
		this.idCasoNuevo = idCasoNuevo;
	}



	public String getMatriculaAlta() {
		return matriculaAlta;
	}



	public void setMatriculaAlta(String matriculaAlta) {
		this.matriculaAlta = matriculaAlta;
	}
	
	
	
	
}
