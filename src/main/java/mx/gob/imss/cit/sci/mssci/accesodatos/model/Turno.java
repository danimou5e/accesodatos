package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo de turnos que se manejan en el IMSS.
 * 
 * @author alfredo.martinezr
 *
 */
public class Turno {

	private Long id;
	private String turno;
	private String descripcion;
	
	public Turno() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
