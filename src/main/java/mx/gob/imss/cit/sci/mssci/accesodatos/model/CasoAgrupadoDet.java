package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "scit_caso_agrupado_det")
public class CasoAgrupadoDet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "cve_id_caso_agrupado_det")
	private Long idCasoAgrupadoDet;
	
	@Column( name = "cve_id_incapacidad")
	private Long idIncapacidad;
	
	@Column(name="cve_matricula_alta")
	private String matriculaAlta;
	
	@ManyToOne//(fetch=FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "cve_id_caso_agrupado", nullable = false)
    private CasoAgrupado casoAgrupado;
	
	
	public CasoAgrupadoDet() {
		//constructor
	}

	public Long getIdCasoAgrupadoDet() {
		return idCasoAgrupadoDet;
	}

	public void setIdCasoAgrupadoDet(Long idCasoAgrupadoDet) {
		this.idCasoAgrupadoDet = idCasoAgrupadoDet;
	}

	public CasoAgrupado getCasoAgrupado() {
		return casoAgrupado;
	}

	public void setCasoAgrupado(CasoAgrupado casoAgrupado) {
		this.casoAgrupado = casoAgrupado;
	}

	public Long getIdIncapacidad() {
		return idIncapacidad;
	}

	public void setIdIncapacidad(Long idIncapacidad) {
		this.idIncapacidad = idIncapacidad;
	}

	public String getMatriculaAlta() {
		return matriculaAlta;
	}

	public void setMatriculaAlta(String matriculaAlta) {
		this.matriculaAlta = matriculaAlta;
	}
	
	

}
