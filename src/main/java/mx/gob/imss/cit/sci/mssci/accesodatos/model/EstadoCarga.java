package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Estados de la carga del archivo NSSA.
 * 
 * @author alfredo.martinezr
 *
 */
public class EstadoCarga extends Audit {

	private Long id;
	private String descripcion;
	
	public EstadoCarga() {
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
