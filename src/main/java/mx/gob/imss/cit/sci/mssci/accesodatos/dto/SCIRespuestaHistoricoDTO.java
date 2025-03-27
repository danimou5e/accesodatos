package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@Getter
@Setter
@Data
public class SCIRespuestaHistoricoDTO {
	@NotNull
	@Valid
	private String nss;
	@NotNull
	@Valid
	private String apPaterno;
	@NotNull
	@Valid
	private String apMaterno;
	@NotNull
	@Valid
	private String nombre;
	@NotNull
	@Valid
	private String unidadAdscripcion;
	@NotNull
	@Valid
	private String descripcionUnidadAdscripcion;
	@NotNull
	@Valid
	private Long idCaso;
	@NotNull
	@Valid
	private String estadoCaso;
	@NotNull
	@Valid
	private Long idIncapacidad;
	@NotNull
	@Valid
	private String refFolio;
	@NotNull
	@Valid
	private String estadoIncapacidad;
	@NotNull
	@Valid
	private String fecInicio;
	@NotNull
	@Valid
	private String fecTermino;
	@NotNull
	@Valid
	private int diasSubsidiados;
	@NotNull
	@Valid
	private int diasAutorizados;
	@NotNull
	@Valid
	private int diasAcumulados;
	@NotNull
	@Valid
	private String tipoIncapacidad;
	@NotNull
	@Valid
	private String descTipoIncapacidad;
	@NotNull
	@Valid
	private String ramo;
	@NotNull
	@Valid
	private String descRamo;
	@NotNull
	@Valid
	private String matriculaMedico;
	@NotNull
	@Valid
	private String unidadExpedicion;
	@NotNull
	@Valid
	private String descUnidadExpedicion;
	@NotNull
	@Valid
	private String refDiagnostico;
	
	public SCIRespuestaHistoricoDTO() {
		//constructor
	}

}
