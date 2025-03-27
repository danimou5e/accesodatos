package mx.gob.imss.cit.sci.mssci.accesodatos.model;

/**
 * Contiene los perfiles que tiene permiso a dar de alta usuarios de otros determinados perfiles.
 * 
 * @author alfredo.martinezr
 *
 */
public class PerfilAutorizaAlta {

	private Long id;
	private Long idPerfil;
	
	public PerfilAutorizaAlta() {
		//constructor
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

}
