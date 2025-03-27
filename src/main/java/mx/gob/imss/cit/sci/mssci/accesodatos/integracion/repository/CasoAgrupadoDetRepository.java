package mx.gob.imss.cit.sci.mssci.accesodatos.integracion.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.imss.cit.sci.mssci.accesodatos.model.CasoAgrupadoDet;

@Repository
public interface CasoAgrupadoDetRepository extends JpaRepository<CasoAgrupadoDet, Long> {
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(nativeQuery = true, value = "INSERT INTO scit_caso_agrupado_det (cve_id_caso_agrupado, cve_id_incapacidad, cve_matricula_alta) "
										+ "VALUES (:idCasoAgrupado, :idIncapacidad, 'SCIADEM') ")
	void insertaCasoAgrupadoDet(@Param("idCasoAgrupado") Long idCasoAgrupado, @Param("idIncapacidad") Long idIncapacidad);

}
