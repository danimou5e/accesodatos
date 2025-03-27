package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ConsultaTodosCasosDTO {
	
	private Long idPaciente;
	private String nombrePaciente;
	private String nss;
	private String nombreUnidad;
	private String ultimoDiagnostico;
	private String cveCie;
	private String cveRegistroPatronal;
	private Date inicioCaso;
	private Date finCaso;
	private Long idCaso;
	private String desUnidadExpedicion;
	private int diasAcumulados;
	private int dpr;
	private Double montoAcumulado;
	private int estadoIncapacidad;

}
