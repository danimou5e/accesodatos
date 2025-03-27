package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo de movimientos (cargas del NSSA A SCI)
 * 
 * @author alfredo.martinezr
 *
 */
public class TipoMovimiento extends Audit {

	private Long id;
	private String descripcion;
	
	public TipoMovimiento() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
