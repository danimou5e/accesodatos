package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo de los rangos de dias acumulados del sistema.
 * 
 * @author alfredo.martinezr
 *
 */
public class Rango extends Audit {

	private Long id ;
	private int clave;
	// Limite inferior del rango
	private int inicio;
	// Limite superior del rango
	private int fin;

	public Rango() {
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

	public int getInicio() {
		return inicio;
	}

	public void setInicio(int inicio) {
		this.inicio = inicio;
	}

	public int getFin() {
		return fin;
	}

	public void setFin(int fin) {
		this.fin = fin;
	}

}
