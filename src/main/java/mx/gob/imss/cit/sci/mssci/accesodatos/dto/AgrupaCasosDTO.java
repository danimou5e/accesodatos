package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import java.util.List;

import lombok.Data;

@Data
public class AgrupaCasosDTO {
	private List<ConsultaTodosCasosDTO> casosSeleccionados;
	private Long nuevoEstadoIncapacidad;
	private String matriculaUsuario;
}
