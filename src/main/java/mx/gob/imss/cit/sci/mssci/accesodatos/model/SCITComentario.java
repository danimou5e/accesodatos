package mx.gob.imss.cit.sci.mssci.accesodatos.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scit_comentario")
@Getter
@Setter
public class SCITComentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cve_id_comentario")
	private Long idComentario;

	@Column(name="cve_id_comentario_padre")
	private Long idComentarioPadre;

	@Column(name="ref_texto")
	private String comentarioHechoAlCaso;

	@Column(name="stp_fecha_comentario")
	private Date fecGeneracionComentario;

	@Column(name="stp_fecha_revaloracion")
	private Date fecRevalorarCaso;

	@Column(name="cve_matricula_alta")
	private String matriculaAlta;

	@Column(name="stp_actualiza_registro")
	private Date fecActualizaRegistro;

	@Column(name="cve_matricula_actualiza")
	private String matriculaActualizaRegistro;

	@Column(name = "cve_id_caso")
	private Long idCaso;
	
	@Column(name = "ind_ult_comentario")
	private int indUltimoComentario;

	@ManyToOne
	@JoinColumn(name = "cve_id_usuario", nullable = false)
	private SCITUsuario usuarioEmiteComentario;

	@ManyToOne
	@JoinColumn(name = "cve_id_clasificacion", nullable = false)
	private SCICClasificacion clasificacion;

}
