package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "scit_caso_activos")//CLON
public class CasoActivos {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "cve_id_caso")
	private Long idCaso;
	
	@Column( name = "cve_id_paciente")
	private Long idPaciente;
	
	@Column( name = "cve_id_estado_incapacidad")
	private Long idEstadoIncapacidad;
	
	@Column(name="fec_ini_caso")
	private Date fechaInicioCaso;
	
	@Column(name="num_dias_prob_rec")
	private int dpr;
	
	@Column(name="num_dias_acumulados")
	private int numDiasAcumulados;
	
	@Column(name="imp_monto_acumulado")
	private Double impMontoAcumulado;
	
	@Column(name="fec_ult_incap_caso")
	private Date fechaUltimaIncapacidadCaso;
	
	@Column(name="num_dias_caso")
	private Long numDiasCaso;
	
	@Column(name="cve_matricula_alta")
	private String matriculaAlta;
	
	
	public CasoActivos() {
		//constructor
	}
	
	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Long getIdEstadoIncapacidad() {
		return idEstadoIncapacidad;
	}

	public void setIdEstadoIncapacidad(Long idEstadoIncapacidad) {
		this.idEstadoIncapacidad = idEstadoIncapacidad;
	}

	public Date getFechaInicioCaso() {
		return fechaInicioCaso;
	}

	public void setFechaInicioCaso(Date fechaInicioCaso) {
		this.fechaInicioCaso = fechaInicioCaso;
	}

	public int getDpr() {
		return dpr;
	}

	public void setDpr(int dpr) {
		this.dpr = dpr;
	}

	public int getNumDiasAcumulados() {
		return numDiasAcumulados;
	}

	public void setNumDiasAcumulados(int numDiasAcumulados) {
		this.numDiasAcumulados = numDiasAcumulados;
	}

	public Double getImpMontoAcumulado() {
		return impMontoAcumulado;
	}

	public void setImpMontoAcumulado(Double impMontoAcumulado) {
		this.impMontoAcumulado = impMontoAcumulado;
	}

	public Date getFechaUltimaIncapacidadCaso() {
		return fechaUltimaIncapacidadCaso;
	}

	public void setFechaUltimaIncapacidadCaso(Date fechaUltimaIncapacidadCaso) {
		this.fechaUltimaIncapacidadCaso = fechaUltimaIncapacidadCaso;
	}

	public Long getNumDiasCaso() {
		return numDiasCaso;
	}

	public void setNumDiasCaso(Long numDiasCaso) {
		this.numDiasCaso = numDiasCaso;
	}

	public String getMatriculaAlta() {
		return matriculaAlta;
	}

	public void setMatriculaAlta(String matriculaAlta) {
		this.matriculaAlta = matriculaAlta;
	}
	
	
	
}
