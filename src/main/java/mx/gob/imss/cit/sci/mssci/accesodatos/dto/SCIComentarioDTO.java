package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SCIComentarioDTO {
	private Long idComentario;
	
	@NotNull
	@Valid
    private Long idUsuario;
	
	@NotNull
	@Valid
    private Long idCaso;

	@NotNull
	@Valid
	private Long idClasificacion;

	@NotNull
	@Valid
	private String comentarioHechoAlCaso;
	
	@NotNull
	@Valid
	private String fecRevalorarCaso;

	@NotNull
	@Valid
	private String fecGeneracionComentario;
	

																							 

}
