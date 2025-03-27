package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SCIPaginadoConsultaHistoricoDTO {

	private List<SCIRespuestaHistoricoDTO> historicoIncapacidades;
	private Integer totalIncapacidades;

	public SCIPaginadoConsultaHistoricoDTO() {
		//constructor
	}

}
