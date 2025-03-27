package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import lombok.Data;

@Data
public class ConsultaIncapacidadRequest {

	private Boolean isAdscripcion;
	private Long idDelegacion;
	private Long idUnidad;
	private Long idRamo;
	private Integer estadoIncapacidad;
	private Boolean isEmpleadoIMSS;
	private String nss;
	private String curp;
	
	//MASV 11/03/2021 nuevos filtros a la consulta avanzada
	private Long idClasificacion;
	private Long idTurno;
	private String consultorio;
	
	private String nombre;
	private String apaterno;
	private String amaterno;
}
