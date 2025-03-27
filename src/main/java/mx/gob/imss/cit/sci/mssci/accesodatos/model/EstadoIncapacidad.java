package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo para el agrupamiento de incapacidades.
 * 
 * @author alfredo.martinezr
 *
 */
public class EstadoIncapacidad extends Audit {
	
	private Long id;
	private Boolean estado;
	private String descripcion;
	
	public EstadoIncapacidad() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
