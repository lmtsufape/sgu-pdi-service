package br.edu.ufape.lmts.sgu.pdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

import br.edu.ufape.lmts.sgu.pdi.model.ObjetivoEspecifico;

public interface ObjetivoEspecificoServiceInterface {
	ObjetivoEspecifico saveObjetivoEspecifico(ObjetivoEspecifico o);
	ObjetivoEspecifico findObjetivoEspecificoById(long id);
	ObjetivoEspecifico updateObjetivoEspecifico(ObjetivoEspecifico u);
	void deleteObjetivoEspecifico(ObjetivoEspecifico u);
	void deleteObjetivoEspecifico(long id);
	Page<ObjetivoEspecifico> getAllObjetivoEspecifico(Predicate predicate, Pageable pageable);
    
    

    
}