package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Catalogo tipo registro pago.
 * 
 * @author alfredo.martinezr
 *
 */
public class TipoRegistroPago extends Audit {

	private Long id;
	private String descripcion;
	// Tipo de registro para diferenciar los pagos manuales 
	// donde (1)Normal y (2) Manual
	private int tipoRegPago;
	
	public TipoRegistroPago() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTipoRegPago() {
		return tipoRegPago;
	}

	public void setTipoRegPago(int tipoRegPago) {
		this.tipoRegPago = tipoRegPago;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
