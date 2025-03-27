package mx.gob.imss.cit.sci.mssci.accesodatos.validation;

import javax.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import mx.gob.imss.cit.sci.mssci.accesodatos.dto.SCIComentarioDTO;

@Service
@Validated
public class CustomValidation {
	
	@Validated(OnCreate.class)
	public void createComentarioValidation(@Valid SCIComentarioDTO comentarioDTO) {
		//crear comentario
	}
	
	@Validated(OnUpdate.class)
	public void updateComentarioValidation(@Valid SCIComentarioDTO comentarioDTO) {
		//actualizar comentario
	}
	
	
	
	
}
