package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo de Unidades Medicas IMSS.
 * 
 * @author alfredo.martinezr
 *
 */
public class Unidad extends Audit {

	private Long id;
	private String cvePresupuestalUnidad;
	private String nombreUnidad;
	private Long idUnidadOrigen;
	private int nivel;
	private Boolean mf;
	private int numUnidad;
	private String cvePreii;
	private Long idDelegacion;

	public Unidad() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCvePresupuestalUnidad() {
		return cvePresupuestalUnidad;
	}

	public void setCvePresupuestalUnidad(String cvePresupuestalUnidad) {
		this.cvePresupuestalUnidad = cvePresupuestalUnidad;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public Long getIdUnidadOrigen() {
		return idUnidadOrigen;
	}

	public void setIdUnidadOrigen(Long idUnidadOrigen) {
		this.idUnidadOrigen = idUnidadOrigen;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public Boolean getMf() {
		return mf;
	}

	public void setMf(Boolean mf) {
		this.mf = mf;
	}

	public int getNumUnidad() {
		return numUnidad;
	}

	public void setNumUnidad(int numUnidad) {
		this.numUnidad = numUnidad;
	}

	public String getCvePreii() {
		return cvePreii;
	}

	public void setCvePreii(String cvePreii) {
		this.cvePreii = cvePreii;
	}

	public Long getIdDelegacion() {
		return idDelegacion;
	}

	public void setIdDelegacion(Long idDelegacion) {
		this.idDelegacion = idDelegacion;
	}

}
