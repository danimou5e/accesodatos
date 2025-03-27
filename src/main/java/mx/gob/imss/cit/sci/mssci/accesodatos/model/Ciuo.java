package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Clave de ocupaciones.
 * 
 * @author alfredo.martinezr
 *
 */
public class Ciuo extends Audit {

	private Long id;
	private String clave;
	private String descripcion;
	
	public Ciuo() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
