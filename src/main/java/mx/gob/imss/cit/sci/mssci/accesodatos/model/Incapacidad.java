package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.sql.Date;

public class Incapacidad extends Audit {

	private Long idIncapacidad;
	private String refFolio;
	private Long idPaciente;
	private Double impPagado;
	private Long diasSubsidiados;
	private Date fechaExpedicion;
	private Long diasAutorizados;
	private Long idRegPatronal;
	private Long idTipoIncapacidad;
	private Long idEstadoIncapacidad;
	private Long idRamo;
	private Long idRango;
	private Date fechaInicio;
	private Long nivelIncapacidad;	
	private Long diasProbRecuperacion;
	private Date fechaTermino;
	private Long diasAcumulados;
	private Long idCie;
	private String refDiagnostico;
	private Long idUnidad;
	private Long idUsuarioMed;
	private Boolean empleadoImss;
	private Long idUnidadTramitadora;
	private Long idUnidadAdscripcion;
	private Double salarioDiario;
	private Double salarioTopado;
	private Long diasDescubiertos;
	private int certificacion;
	private int subsecuente;
	private Date fechaProceso;
	private Long idTipoRegPago;
	private Long idUsuarioAutoriza;
	private int riesgo;
	private int ctrlMaternidad;
	private int marcaPago;
	private int reconocimientoRt;
	private Boolean convenio;
	private Long diasAdicionales;
	private Date fechaPrimerIngreso;
	private Integer periodoOci;
	private String nombreArchivo;
	
	public Incapacidad() {
		//constructor
	}

	public Long getIdIncapacidad() {
		return idIncapacidad;
	}
	public void setIdIncapacidad(Long idIncapacidad) {
		this.idIncapacidad = idIncapacidad;
	}
	public String getRefFolio() {
		return refFolio;
	}
	public void setRefFolio(String refFolio) {
		this.refFolio = refFolio;
	}
	public Long getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}
	public Double getImpPagado() {
		return impPagado;
	}
	public void setImpPagado(Double impPagado) {
		this.impPagado = impPagado;
	}
	public Long getDiasSubsidiados() {
		return diasSubsidiados;
	}
	public void setDiasSubsidiados(Long diasSubsidiados) {
		this.diasSubsidiados = diasSubsidiados;
	}
	public Date getFechaExpedicion() {
		return fechaExpedicion;
	}
	public void setFechaExpedicion(Date fechaExpedicion) {
		this.fechaExpedicion = fechaExpedicion;
	}
	public Long getDiasAutorizados() {
		return diasAutorizados;
	}
	public void setDiasAutorizados(Long diasAutorizados) {
		this.diasAutorizados = diasAutorizados;
	}
	public Long getIdRegPatronal() {
		return idRegPatronal;
	}
	public void setIdRegPatronal(Long idRegPatronal) {
		this.idRegPatronal = idRegPatronal;
	}
	public Long getIdTipoIncapacidad() {
		return idTipoIncapacidad;
	}
	public void setIdTipoIncapacidad(Long idTipoIncapacidad) {
		this.idTipoIncapacidad = idTipoIncapacidad;
	}
	public Long getIdEstadoIncapacidad() {
		return idEstadoIncapacidad;
	}
	public void setIdEstadoIncapacidad(Long idEstadoIncapacidad) {
		this.idEstadoIncapacidad = idEstadoIncapacidad;
	}
	public Long getIdRamo() {
		return idRamo;
	}
	public void setIdRamo(Long idRamo) {
		this.idRamo = idRamo;
	}
	public Long getIdRango() {
		return idRango;
	}
	public void setIdRango(Long idRango) {
		this.idRango = idRango;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Long getNivelIncapacidad() {
		return nivelIncapacidad;
	}
	public void setNivelIncapacidad(Long nivelIncapacidad) {
		this.nivelIncapacidad = nivelIncapacidad;
	}
	public Long getDiasProbRecuperacion() {
		return diasProbRecuperacion;
	}
	public void setDiasProbRecuperacion(Long diasProbRecuperacion) {
		this.diasProbRecuperacion = diasProbRecuperacion;
	}
	public Date getFechaTermino() {
		return fechaTermino;
	}
	public void setFechaTermino(Date fechaTermino) {
		this.fechaTermino = fechaTermino;
	}
	public Long getDiasAcumulados() {
		return diasAcumulados;
	}
	public void setDiasAcumulados(Long diasAcumulados) {
		this.diasAcumulados = diasAcumulados;
	}
	public Long getIdCie() {
		return idCie;
	}
	public void setIdCie(Long idCie) {
		this.idCie = idCie;
	}
	public String getRefDiagnostico() {
		return refDiagnostico;
	}
	public void setRefDiagnostico(String refDiagnostico) {
		this.refDiagnostico = refDiagnostico;
	}
	public Long getIdUnidad() {
		return idUnidad;
	}
	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}
	public Long getIdUsuarioMed() {
		return idUsuarioMed;
	}
	public void setIdUsuarioMed(Long idUsuarioMed) {
		this.idUsuarioMed = idUsuarioMed;
	}
	public Boolean getEmpleadoImss() {
		return empleadoImss;
	}
	public void setEmpleadoImss(Boolean empleadoImss) {
		this.empleadoImss = empleadoImss;
	}
	public Long getIdUnidadTramitadora() {
		return idUnidadTramitadora;
	}
	public void setIdUnidadTramitadora(Long idUnidadTramitadora) {
		this.idUnidadTramitadora = idUnidadTramitadora;
	}
	public Long getIdUnidadAdscripcion() {
		return idUnidadAdscripcion;
	}
	public void setIdUnidadAdscripcion(Long idUnidadAdscripcion) {
		this.idUnidadAdscripcion = idUnidadAdscripcion;
	}
	public Double getSalarioDiario() {
		return salarioDiario;
	}
	public void setSalarioDiario(Double salarioDiario) {
		this.salarioDiario = salarioDiario;
	}
	public Double getSalarioTopado() {
		return salarioTopado;
	}
	public void setSalarioTopado(Double salarioTopado) {
		this.salarioTopado = salarioTopado;
	}
	public Long getDiasDescubiertos() {
		return diasDescubiertos;
	}
	public void setDiasDescubiertos(Long diasDescubiertos) {
		this.diasDescubiertos = diasDescubiertos;
	}
	public int getCertificacion() {
		return certificacion;
	}
	public void setCertificacion(int certificacion) {
		this.certificacion = certificacion;
	}
	public int getSubsecuente() {
		return subsecuente;
	}
	public void setSubsecuente(int subsecuente) {
		this.subsecuente = subsecuente;
	}
	public Date getFechaProceso() {
		return fechaProceso;
	}
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	public Long getIdTipoRegPago() {
		return idTipoRegPago;
	}
	public void setIdTipoRegPago(Long idTipoRegPago) {
		this.idTipoRegPago = idTipoRegPago;
	}
	public Long getIdUsuarioAutoriza() {
		return idUsuarioAutoriza;
	}
	public void setIdUsuarioAutoriza(Long idUsuarioAutoriza) {
		this.idUsuarioAutoriza = idUsuarioAutoriza;
	}
	public int getRiesgo() {
		return riesgo;
	}
	public void setRiesgo(int riesgo) {
		this.riesgo = riesgo;
	}
	public int getCtrlMaternidad() {
		return ctrlMaternidad;
	}
	public void setCtrlMaternidad(int ctrlMaternidad) {
		this.ctrlMaternidad = ctrlMaternidad;
	}
	public int getMarcaPago() {
		return marcaPago;
	}
	public void setMarcaPago(int marcaPago) {
		this.marcaPago = marcaPago;
	}
	public int getReconocimientoRt() {
		return reconocimientoRt;
	}
	public void setReconocimientoRt(int reconocimientoRt) {
		this.reconocimientoRt = reconocimientoRt;
	}
	public Boolean getConvenio() {
		return convenio;
	}
	public void setConvenio(Boolean convenio) {
		this.convenio = convenio;
	}
	public Long getDiasAdicionales() {
		return diasAdicionales;
	}
	public void setDiasAdicionales(Long diasAdicionales) {
		this.diasAdicionales = diasAdicionales;
	}
	public Date getFechaPrimerIngreso() {
		return fechaPrimerIngreso;
	}
	public void setFechaPrimerIngreso(Date fechaPrimerIngreso) {
		this.fechaPrimerIngreso = fechaPrimerIngreso;
	}
	public Integer getPeriodoOci() {
		return periodoOci;
	}
	public void setPeriodoOci(Integer periodoOci) {
		this.periodoOci = periodoOci;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

}
