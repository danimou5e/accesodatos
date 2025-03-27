package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.sql.Date;

import lombok.Data;

/**
 * Bitacora de errores de la carga del archivo txt de NSSA.
 * 
 * @author alfredo.martinezr
 *
 */
@Data
public class BitacoraError extends Audit {

	private Long id;
	private Long idBitacora;
	private Long idMovimiento;
	private String descripcion;
	private Long idTipoRegPago;
	private Long idUnidadTramitadora;
	private String numAfiliacion;
	private int numDigVerificador;
	private int numFolioIncapacidad;
	private String curp;
	private String nomAsegurado;
	private String nomApellidoPaterno;
	private String nomApellidoMaterno;
	// fecha de inicio de la incapacidad
	private Date fecInicio;
	private Double impSalarioDiario;
	private Double impSalarioTopado;
	// fecha de termino de la incapacidad
	private Date fecTermino;
	private Date fecExpedicion;
	private int numDiasAutorizados;
	private int numDiasSubsidiados;
	private int numDiasTranslapados;
	private int numDiasDescubiertos;
	private int numDiasAcumulados;
	private Double impSubsidio;
	private String cveUmfExpedicion;
	private String desDiagnosticoMedico;
	private byte tipCertificacion;
	private byte indSubsecuente;
	// Fecha de tramite 
	private Date fecProceso;
	private Long idRegistroPatronal;
	private String numMatriculaMed;
	private String numMatriculaAutoriza;
	private String claveIncapacidad;
	private String claveRamo;
	private String claveRiesgo;
	private Boolean indCtrlMaternidad;
	private int tipoMarcaPago;
	// Identificador de reconocimiento de RT
	private byte reconocimientoRt;
	private Long idSexo;
	private int numDiasRecuperacion;
	private Long idCie10;
	private Long idUmfAdscripcion;
	private char tipConvenio;
	private Boolean capitalCons;
	private int numDiasAdicionales;
	private String observacion;

}
