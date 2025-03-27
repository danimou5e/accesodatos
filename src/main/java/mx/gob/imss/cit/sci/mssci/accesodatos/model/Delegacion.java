package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo de las Delegaciones IMSS.
 * 
 * @author alfredo.martinezr
 *
 */
public class Delegacion extends Audit {
	
	private Long id;
	private Long clave;
	private Long idRegion;
	private String descripcion;
	
	public Delegacion() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public Long getClave() {
		return clave;
	}

	public void setClave(Long clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
