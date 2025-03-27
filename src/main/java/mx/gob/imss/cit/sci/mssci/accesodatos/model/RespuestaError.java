package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import mx.gob.imss.cit.sci.mssci.accesodatos.enums.EnumHttpStatus;

public class RespuestaError   {

    @Schema(description = "Código de error HTTP.", example = "500")
    private String code;
    
    @Schema(description = "Descripción del error.", example = "Internal server error")
    private String description;
    
	@Schema(description = "Mensaje de negocio.", example = "Problemas con el servicio. Intente más tarde por favor")
	private String businessMessage;
    
    @Schema(description = "Mensaje real de la excepción.", example = "Exception in thread main java.lang.ArrayIndexOutOfBoundsException")
	private String reasonPhrase;
    
    @Schema(description = "Reservado para uso posterior.", required = false)
    private String uri;
    
	@Schema(description = "Reservado para uso posterior.", required = false)
	private String contactEmail;	
    
    @Schema(description = "Hora y fecha en la que se origina el error.", example = "2020-04-16T14:24:45.000")
	private String timeStamp;
        

    public RespuestaError(EnumHttpStatus status, String businessMessage, String reasonPhrase) {
		this.code = status.getCode().toString();
		this.description = status.getDescription();
		this.businessMessage = businessMessage;
		this.reasonPhrase = reasonPhrase;
		this.uri = "http://sci.imss.gob.mx/help";

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		this.timeStamp = dateFormat.format(new Date());
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getBusinessMessage() {
		return businessMessage;
	}


	public void setBusinessMessage(String businessMessage) {
		this.businessMessage = businessMessage;
	}


	public String getReasonPhrase() {
		return reasonPhrase;
	}


	public void setReasonPhrase(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}


	public String getUri() {
		return uri;
	}


	public void setUri(String uri) {
		this.uri = uri;
	}


	public String getContactEmail() {
		return contactEmail;
	}


	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}


	public String getTimeStamp() {
		return timeStamp;
	}


	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

}
