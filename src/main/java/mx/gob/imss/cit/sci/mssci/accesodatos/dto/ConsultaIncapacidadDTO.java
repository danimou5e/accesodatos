package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import java.util.List;

import lombok.Data;
import mx.gob.imss.cit.sci.mssci.accesodatos.model.IncapacidadGraficaModel;

@Data
public class ConsultaIncapacidadDTO {

	private Long idPaciente;
	private Long idIncapacidad;
	private Long idUnidad;
	private String nombrePaciente;
	private String nss;
	private String curp;
	private String nombreUnidad;
	private String umf;
	private String nombreTurno;
	private String descRamo;
	private String descOcupacion;
	private Double montoAcumuladoSubsidio;
	private String ultimoDiagnostico;
	private String cveCie;
	private int dpr;
	private String matricula;
	private String cveRegistroPatronal;
	private String situacionActual;
	private String comentario;
	private String ultimaCargaNSSA;
	private String inicioCaso;
	private String finCaso;
	private int limiteDias;
	private Long idOcupacion;
	private Long idCie;
	private Long idTurno;
	private Long idCaso;
	
	private List<IncapacidadGraficaModel> incapacidadGraficaModel;

}
