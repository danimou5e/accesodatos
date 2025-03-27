package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import java.util.Date;

public class ExportIncapacidadesDTO {
	private Long idCaso;
	private Long idPaciente;
	private String nombrePaciente;
	private String primerApellido;
	private String segundoApellido;
	private String nss;
	private Long idUnidad;
	private String nombreUnidad;
	private Long idUnidadExp;
	private String nombreUnidadExp;
	private Long idDelegacion;
	private String nombreDelegacion;
	private int diasAcumuladosIncapacidad;
	private Date inicioCaso;
	private Date finCaso;
	private Integer numIncapacidades;
	private String ultimaCargaNSSA;
	private Long idIncapacidad;
	private String curp;
	private String umf;
	private String nombreTurno;
	private String descRamo;
	private String descOcupacion;
	private Double montoAcumuladoSubsidio;
	private String ultimoDiagnostico;
	private String cveCie;
	private int dpr;
	private String matriculaMedico;
	private String cveRegistroPatronal;
	private Long idOcupacion;
	private Long idCie;
	private Long idTurno;
	private String comentario;
	private String claveClasificacion;
	private String descClasificacion;
	private Date fechaEmision;
	private Date fechaRevaloracion;

	public ExportIncapacidadesDTO() {
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

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public Long getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public Long getIdUnidadExp() {
		return idUnidadExp;
	}

	public void setIdUnidadExp(Long idUnidadExp) {
		this.idUnidadExp = idUnidadExp;
	}

	public String getNombreUnidadExp() {
		return nombreUnidadExp;
	}

	public void setNombreUnidadExp(String nombreUnidadExp) {
		this.nombreUnidadExp = nombreUnidadExp;
	}

	public Long getIdDelegacion() {
		return idDelegacion;
	}

	public void setIdDelegacion(Long idDelegacion) {
		this.idDelegacion = idDelegacion;
	}

	public String getNombreDelegacion() {
		return nombreDelegacion;
	}

	public void setNombreDelegacion(String nombreDelegacion) {
		this.nombreDelegacion = nombreDelegacion;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public int getDiasAcumuladosIncapacidad() {
		return diasAcumuladosIncapacidad;
	}

	public void setDiasAcumuladosIncapacidad(int diasAcumuladosIncapacidad) {
		this.diasAcumuladosIncapacidad = diasAcumuladosIncapacidad;
	}

	public Date getInicioCaso() {
		return inicioCaso;
	}

	public void setInicioCaso(Date inicioCaso) {
		this.inicioCaso = inicioCaso;
	}

	public Date getFinCaso() {
		return finCaso;
	}

	public void setFinCaso(Date finCaso) {
		this.finCaso = finCaso;
	}

	public Integer getNumIncapacidades() {
		return numIncapacidades;
	}

	public void setNumIncapacidades(Integer numIncapacidades) {
		this.numIncapacidades = numIncapacidades;
	}

	public String getUltimaCargaNSSA() {
		return ultimaCargaNSSA;
	}

	public void setUltimaCargaNSSA(String ultimaCargaNSSA) {
		this.ultimaCargaNSSA = ultimaCargaNSSA;
	}

	public Long getIdIncapacidad() {
		return idIncapacidad;
	}

	public void setIdIncapacidad(Long idIncapacidad) {
		this.idIncapacidad = idIncapacidad;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getUmf() {
		return umf;
	}

	public void setUmf(String umf) {
		this.umf = umf;
	}

	public String getNombreTurno() {
		return nombreTurno;
	}

	public void setNombreTurno(String nombreTurno) {
		this.nombreTurno = nombreTurno;
	}

	public String getDescRamo() {
		return descRamo;
	}

	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}

	public String getDescOcupacion() {
		return descOcupacion;
	}

	public void setDescOcupacion(String descOcupacion) {
		this.descOcupacion = descOcupacion;
	}

	public Double getMontoAcumuladoSubsidio() {
		return montoAcumuladoSubsidio;
	}

	public void setMontoAcumuladoSubsidio(Double montoAcumuladoSubsidio) {
		this.montoAcumuladoSubsidio = montoAcumuladoSubsidio;
	}

	public String getUltimoDiagnostico() {
		return ultimoDiagnostico;
	}

	public void setUltimoDiagnostico(String ultimoDiagnostico) {
		this.ultimoDiagnostico = ultimoDiagnostico;
	}

	public String getCveCie() {
		return cveCie;
	}

	public void setCveCie(String cveCie) {
		this.cveCie = cveCie;
	}

	public int getDpr() {
		return dpr;
	}

	public void setDpr(int dpr) {
		this.dpr = dpr;
	}

	public String getMatriculaMedico() {
		return matriculaMedico;
	}

	public void setMatriculaMedico(String matriculaMedico) {
		this.matriculaMedico = matriculaMedico;
	}

	public String getCveRegistroPatronal() {
		return cveRegistroPatronal;
	}

	public void setCveRegistroPatronal(String cveRegistroPatronal) {
		this.cveRegistroPatronal = cveRegistroPatronal;
	}

	public Long getIdOcupacion() {
		return idOcupacion;
	}

	public void setIdOcupacion(Long idOcupacion) {
		this.idOcupacion = idOcupacion;
	}

	public Long getIdCie() {
		return idCie;
	}

	public void setIdCie(Long idCie) {
		this.idCie = idCie;
	}

	public Long getIdTurno() {
		return idTurno;
	}

	public void setIdTurno(Long idTurno) {
		this.idTurno = idTurno;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getClaveClasificacion() {
		return claveClasificacion;
	}

	public void setClaveClasificacion(String claveClasificacion) {
		this.claveClasificacion = claveClasificacion;
	}

	public String getDescClasificacion() {
		return descClasificacion;
	}

	public void setDescClasificacion(String descClasificacion) {
		this.descClasificacion = descClasificacion;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaRevaloracion() {
		return fechaRevaloracion;
	}

	public void setFechaRevaloracion(Date fechaRevaloracion) {
		this.fechaRevaloracion = fechaRevaloracion;
	}

}