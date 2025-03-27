package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.sql.Date;

/**
 * Bitacora del estado de la carga del archivo txt de NSSA.
 * 
 * @author alfredo.martinezr
 *
 */
public class BitacoraCarga extends Audit {

	private Long id;
	private Date fechaProceso;
	private Date fechaCarga;
	private String nombreArchivo;
	private Long idEstadoCargaInicial;
	private Long idEstadoCargaFinal;
	private Long totalRegistros;
	private Long regInsertados;

	public BitacoraCarga() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public Date getFechaCarga() {
		return fechaCarga;
	}

	public void setFechaCarga(Date fechaCarga) {
		this.fechaCarga = fechaCarga;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public Long getIdEstadoCargaInicial() {
		return idEstadoCargaInicial;
	}

	public void setIdEstadoCargaInicial(Long idEstadoCargaInicial) {
		this.idEstadoCargaInicial = idEstadoCargaInicial;
	}

	public Long getIdEstadoCargaFinal() {
		return idEstadoCargaFinal;
	}

	public void setIdEstadoCargaFinal(Long idEstadoCargaFinal) {
		this.idEstadoCargaFinal = idEstadoCargaFinal;
	}

	public Long getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Long totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public Long getRegInsertados() {
		return regInsertados;
	}

	public void setRegInsertados(Long regInsertados) {
		this.regInsertados = regInsertados;
	}

}
