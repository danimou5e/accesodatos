package mx.gob.imss.cit.sci.mssci.accesodatos.dto;

public class ConsultaIncapacidadesDTO {

	private Long idCaso;
	private Long idPaciente;
	private String nombrePaciente;
	private String primerApellido;
	private String segundoApellido;
	private String nss;
	private Long idUnidad;
	private String nombreUnidad;
	private int diasAcumuladosIncapacidad;
	private String inicioCaso;
	private String finCaso;

	public ConsultaIncapacidadesDTO() {
		//constructor
	}

	public Long getIdCaso() {
		return idCaso;
	}

	public void setIdCaso(Long idCaso) {
		this.idCaso = idCaso;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public Long getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Long idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}

	public Long getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(Long idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getNombreUnidad() {
		return nombreUnidad;
	}

	public void setNombreUnidad(String nombreUnidad) {
		this.nombreUnidad = nombreUnidad;
	}

	public int getDiasAcumuladosIncapacidad() {
		return diasAcumuladosIncapacidad;
	}

	public void setDiasAcumuladosIncapacidad(int diasAcumuladosIncapacidad) {
		this.diasAcumuladosIncapacidad = diasAcumuladosIncapacidad;
	}

	public String getInicioCaso() {
		return inicioCaso;
	}

	public void setInicioCaso(String inicioCaso) {
		this.inicioCaso = inicioCaso;
	}

	public String getFinCaso() {
		return finCaso;
	}

	public void setFinCaso(String finCaso) {
		this.finCaso = finCaso;
	}

}
