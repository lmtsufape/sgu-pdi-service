package br.edu.ufape.lmts.sgu.pdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

import br.edu.ufape.lmts.sgu.pdi.model.HistoricoStatus;

public interface HistoricoStatusServiceInterface {
	HistoricoStatus saveHistoricoStatus(HistoricoStatus o);
	HistoricoStatus findHistoricoStatusById(long id);
	HistoricoStatus updateHistoricoStatus(HistoricoStatus u);
	void deleteHistoricoStatus(HistoricoStatus u);
	void deleteHistoricoStatus(long id);
	Page<HistoricoStatus> getAllHistoricoStatus(Predicate predicate, Pageable pageable);
    
    

    
}