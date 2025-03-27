package mx.gob.imss.cit.sci.mssci.accesodatos.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Catalogo  internacional de enfermedades.
 * 
 * @author alfredo.martinezr
 *
 */
@Entity(name = "scic_cie")
public class Cie extends Audit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "cve_id_cie")
	private Long id;
	
	@Column( name = "cve_cie")
	private String clave;
	
	@Column( name = "des_cie")
	private String descripcion;

	public Cie() {
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
