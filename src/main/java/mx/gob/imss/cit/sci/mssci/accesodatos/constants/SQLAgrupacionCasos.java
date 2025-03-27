package mx.gob.imss.cit.sci.mssci.accesodatos.constants;

public class SQLAgrupacionCasos {
	
	private SQLAgrupacionCasos() {}

 public static final String QS_CONSULTA_CASOS_TOTALES = "SELECT "
			+ " caso.cve_id_estado_incapacidad AS ESTADOINC, "
			+ " inc.cve_id_caso AS IDCASO, "
			+ " pac.cve_id_paciente  AS IDPACIENTE, "
			+ " uni2.des_nombre_unidad AS NOMBREUNIDADEXP,  "
			+ " uni.des_nombre_unidad AS NOMBREUNIADSCRIPCION, "
			+ " pac.nom_nombre_pac AS PACIENTE, "
			+ " pac.nom_apellido_paterno_pac AS PAPELLIDO, "
			+ " pac.nom_apellido_materno_pac AS SAPELLIDO, "
			+ " pac.ref_nss AS NSS, "
			+ " inc.cve_reg_patronal AS REGPATRONAL, "
			+ " caso.num_dias_acumulados AS DIASACUMULADOSINCAPACIDAD, "
			+ " caso.fec_ini_caso AS INICIOCASO, "
			+ " caso.fec_ult_incap_caso AS FINCASO, "
			+ " cie.cve_cie AS CIE, "
			+ " ref_diagnostico AS ULTIMODIAGNOSTICO, "
			+ " caso.num_dias_prob_rec AS DPR, "
			+ " caso.imp_monto_acumulado AS MONTOACUMULADO "
			+ "FROM scit_incapacidad inc "
			+ " INNER JOIN scit_caso caso ON inc.cve_id_caso = caso.cve_id_caso "
			+ " INNER JOIN scit_paciente pac ON pac.cve_id_paciente = caso.cve_id_paciente "
			+ " INNER JOIN scic_unidad uni ON uni.cve_id_unidad = pac.cve_id_unidad "
			+ " LEFT JOIN scic_cie cie ON cie.cve_id_cie = inc.cve_id_cie "
			+ " INNER JOIN scic_unidad uni2 ON inc.cve_id_unidad_expedicion=uni2.cve_id_unidad "
			+ " WHERE pac.ref_nss = ? "
			+ " AND caso.cve_id_estado_incapacidad != 3 "
			+ " AND inc.cve_id_incapacidad IN (SELECT MAX(cve_id_incapacidad) from  scit_incapacidad inc_caso where caso.cve_id_caso = inc_caso.cve_id_caso) " 
			+ " GROUP BY "
			+ " inc.cve_id_caso "
			+ " ORDER BY "
			+ " caso.fec_ini_caso DESC, "
			+ " caso.num_dias_acumulados DESC, "
			+ " caso.cve_id_caso DESC, "
			+ " inc.stp_alta_registro DESC ";
 
 public static final String QS_ACTUALIZA_INCAPACIDADES_CON_NUEVO_ID_CASO = "UPDATE "
			+ " scit_incapacidad SET cve_id_caso = ? ,"
			+ "stp_actualiza_registro = NOW(), "
			+ " cve_matricula_actualiza = ? "
			+ " WHERE cve_id_caso = ? ";
 
 public static final String QS_CONSULTA_ID_INCAPACIDADES_POR_NUEVO_ID_CASO = "SELECT "
			+ " inc.cve_id_incapacidad AS IDINCAPACIDAD "
			+ " FROM scit_incapacidad inc "
			+ " WHERE inc.cve_id_caso = ? ";
 
 public static final String QS_ACTUALIZA_IND_ULT_COMENTARIO = "UPDATE "
			+ " scit_comentario SET ind_ult_comentario = ? "
			+ " WHERE cve_id_comentario = ? ";
 
}
