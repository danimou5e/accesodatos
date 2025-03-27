package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo Tipo Incapacidad.
 * 
 * @author alfredo.martinezr
 *
 */
public class TipoIncapacidad extends Audit {

	private Long id;
	private int clave;
	private String decripcion;
	
	public TipoIncapacidad() {
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

	public String getDecripcion() {
		return decripcion;
	}

	public void setDecripcion(String decripcion) {
		this.decripcion = decripcion;
	}

}
