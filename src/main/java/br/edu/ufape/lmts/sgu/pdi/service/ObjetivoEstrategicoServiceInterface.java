package br.edu.ufape.lmts.sgu.pdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

import br.edu.ufape.lmts.sgu.pdi.model.ObjetivoEstrategico;

public interface ObjetivoEstrategicoServiceInterface {
	ObjetivoEstrategico saveObjetivoEstrategico(ObjetivoEstrategico o);
	ObjetivoEstrategico findObjetivoEstrategicoById(long id);
	ObjetivoEstrategico updateObjetivoEstrategico(ObjetivoEstrategico u);
	void deleteObjetivoEstrategico(ObjetivoEstrategico u);
	void deleteObjetivoEstrategico(long id);
	Page<ObjetivoEstrategico> getAllObjetivoEstrategico(Predicate predicate, Pageable pageable);
    
    

    
}