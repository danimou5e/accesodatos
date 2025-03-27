package mx.gob.imss.cit.sci.mssci.accesodatos.utils;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class ValidationUtils {

		public static Map<String, String> convertValidationError(ConstraintViolationException errors){
			Map<String, String> response = null;
			if(errors != null) {
				response = new HashMap<String, String>();
				for(@SuppressWarnings("rawtypes") ConstraintViolation violation : errors.getConstraintViolations()) {
					response.put(violation.getPropertyPath().toString(), violation.getMessage());

				}
			}
			return response;
		}
}
