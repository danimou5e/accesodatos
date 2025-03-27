package mx.gob.imss.cit.sci.mssci.accesodatos.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.gob.imss.cit.sci.mssci.accesodatos.enums.SCIResponseCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponse<T> {
	private SCIResponseCode status;
	private String message;
	private T bodyResponse;

}
