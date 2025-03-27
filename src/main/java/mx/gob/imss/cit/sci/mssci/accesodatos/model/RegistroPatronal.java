package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.sql.Date;

/**
 * Catalogo de Registro Patronal.
 * 
 * @author alfredo.martinezr
 *
 */
public class RegistroPatronal extends Audit {

	private Long id;
	private String clave;
	private String digVerifRegPatronal;
	private String rfc;
	private String razonSocial;
	private Date fechaAlta;
	
	public RegistroPatronal() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDigVerifRegPatronal() {
		return digVerifRegPatronal;
	}

	public void setDigVerifRegPatronal(String digVerifRegPatronal) {
		this.digVerifRegPatronal = digVerifRegPatronal;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

}
