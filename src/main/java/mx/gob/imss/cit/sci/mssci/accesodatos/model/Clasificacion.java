package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo de clasificaciones de casos.
 * 
 * @author alfredo.martinezr
 *
 */
public class Clasificacion extends Audit {

	private Long id;
	private char clave;
	private String descripcion;
	
	public Clasificacion() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public char getClave() {
		return clave;
	}

	public void setClave(char clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
