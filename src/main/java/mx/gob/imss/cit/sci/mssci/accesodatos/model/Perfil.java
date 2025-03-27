package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo de perfiles de acceso al sistema.
 * 
 * @author alfredo.martinezr
 *
 */
public class Perfil extends Audit {
	
	private Long id;
	private String clave;
	private String descripcion;
	
	public Perfil() {
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
