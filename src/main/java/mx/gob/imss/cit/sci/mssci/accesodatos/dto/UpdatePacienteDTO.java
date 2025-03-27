package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import lombok.Data;

@Data
public class UpdatePacienteDTO {

	private Long idPaciente;
	private Long idIncapacidad;
	
	private Long idCie;
	private String cie;
	
	private String consultorio;
	private Long idTurno;
	private Long idOcupacion;
	private String matriculaUsuario;
	private Long idUsuarioModifica;
}
