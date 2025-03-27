package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Data;


@Getter
@Setter
@Data
public class SCIParametrosHistoricoIncapacidadesDTO {
	private Long idUnidadAdscripcion;
	private String nss;
	private String nombre;
	private String aPaterno;
	private String aMaterno;
	private Boolean exportar;
	
	
}
