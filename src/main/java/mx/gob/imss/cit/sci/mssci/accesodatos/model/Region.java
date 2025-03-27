package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo de Regiones.
 * 
 * @author alfredo.martinezr
 *
 */
public class Region extends Audit {

	private Long id;
	private int clave;
	private String descripcion;
	
	public Region() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getClave() {
		return clave;
	}

	public void setClave(int clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
