package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import lombok.Data;

@Data
public class IncapacidadGraficaModel {

	private String expedicion;
	private String folio;
	private int diasAutorizados;
	private String matriculaMedico;
	private Long idRamo;
	private String descRamo;
	private int diasLimite;
	private int nivelIncapacidad;
	private String fechaInicio;
	private String fechaFin;
}
