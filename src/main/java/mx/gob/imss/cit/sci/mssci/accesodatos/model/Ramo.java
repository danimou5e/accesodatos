package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo de Ramos de incapacidad.
 * 
 * @author alfredo.martinezr
 *
 */
public class Ramo extends Audit {

	private Long id;
	private Long clave;
	private String descripcion;
	private int diasLimite;
	
	public Ramo() {
		//constructor
	}
 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getDiasLimite() {
		return diasLimite;
	}

	public void setDiasLimite(int diasLimite) {
		this.diasLimite = diasLimite;
	}

}
