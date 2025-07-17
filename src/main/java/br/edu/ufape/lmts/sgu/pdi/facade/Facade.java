package br.edu.ufape.lmts.sgu.pdi.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufape.lmts.sgu.pdi.model.*;
import br.edu.ufape.lmts.sgu.pdi.service.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

@Service
public class Facade {
	//HistoricoStatus--------------------------------------------------------------
	@Autowired
	private HistoricoStatusServiceInterface  historicoStatusService;
		
	public HistoricoStatus saveHistoricoStatus(HistoricoStatus newInstance) {
		return historicoStatusService.saveHistoricoStatus(newInstance);
	}

	public HistoricoStatus updateHistoricoStatus(HistoricoStatus transientObject) {
		return historicoStatusService.updateHistoricoStatus(transientObject);
	}

	public HistoricoStatus findHistoricoStatusById(long id) {
		return historicoStatusService.findHistoricoStatusById(id);
	}

	public Page<HistoricoStatus> getAllHistoricoStatus(Predicate predicate, Pageable pageable) {
		return historicoStatusService.getAllHistoricoStatus(predicate, pageable);
	}

	public void deleteHistoricoStatus(HistoricoStatus persistentObject) {
		historicoStatusService.deleteHistoricoStatus(persistentObject);
	}

	public void deleteHistoricoStatus(long id) {
		historicoStatusService.deleteHistoricoStatus(id);
	}
	

	//ObjetivoEspecifico--------------------------------------------------------------
	@Autowired
	private ObjetivoEspecificoServiceInterface  objetivoEspecificoService;
		
	public ObjetivoEspecifico saveObjetivoEspecifico(ObjetivoEspecifico newInstance) {
		return objetivoEspecificoService.saveObjetivoEspecifico(newInstance);
	}

	public ObjetivoEspecifico updateObjetivoEspecifico(ObjetivoEspecifico transientObject) {
		return objetivoEspecificoService.updateObjetivoEspecifico(transientObject);
	}

	public ObjetivoEspecifico findObjetivoEspecificoById(long id) {
		return objetivoEspecificoService.findObjetivoEspecificoById(id);
	}

	public Page<ObjetivoEspecifico> getAllObjetivoEspecifico(Predicate predicate, Pageable pageable) {
		return objetivoEspecificoService.getAllObjetivoEspecifico(predicate, pageable);
	}

	public void deleteObjetivoEspecifico(ObjetivoEspecifico persistentObject) {
		objetivoEspecificoService.deleteObjetivoEspecifico(persistentObject);
	}

	public void deleteObjetivoEspecifico(long id) {
		objetivoEspecificoService.deleteObjetivoEspecifico(id);
	}
	

	//Meta--------------------------------------------------------------
	@Autowired
	private MetaServiceInterface  metaService;
		
	public Meta saveMeta(Meta newInstance) {
		return metaService.saveMeta(newInstance);
	}

	public Meta updateMeta(Meta transientObject) {
		return metaService.updateMeta(transientObject);
	}

	public Meta findMetaById(long id) {
		return metaService.findMetaById(id);
	}

	public Page<Meta> getAllMeta(Predicate predicate, Pageable pageable) {
		return metaService.getAllMeta(predicate, pageable);
	}

	public void deleteMeta(Meta persistentObject) {
		metaService.deleteMeta(persistentObject);
	}

	public void deleteMeta(long id) {
		metaService.deleteMeta(id);
	}
	

	//ObjetivoEstrategico--------------------------------------------------------------
	@Autowired
	private ObjetivoEstrategicoServiceInterface  objetivoEstrategicoService;
		
	public ObjetivoEstrategico saveObjetivoEstrategico(ObjetivoEstrategico newInstance) {
		return objetivoEstrategicoService.saveObjetivoEstrategico(newInstance);
	}

	public ObjetivoEstrategico updateObjetivoEstrategico(ObjetivoEstrategico transientObject) {
		return objetivoEstrategicoService.updateObjetivoEstrategico(transientObject);
	}

	public ObjetivoEstrategico findObjetivoEstrategicoById(long id) {
		return objetivoEstrategicoService.findObjetivoEstrategicoById(id);
	}

	public Page<ObjetivoEstrategico> getAllObjetivoEstrategico(Predicate predicate, Pageable pageable) {
		return objetivoEstrategicoService.getAllObjetivoEstrategico(predicate, pageable);
	}

	public void deleteObjetivoEstrategico(ObjetivoEstrategico persistentObject) {
		objetivoEstrategicoService.deleteObjetivoEstrategico(persistentObject);
	}

	public void deleteObjetivoEstrategico(long id) {
		objetivoEstrategicoService.deleteObjetivoEstrategico(id);
	}
	

}