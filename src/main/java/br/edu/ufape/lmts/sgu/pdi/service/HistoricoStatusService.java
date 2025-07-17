package br.edu.ufape.lmts.sgu.pdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.lmts.sgu.pdi.repository.HistoricoStatusRepository;
import br.edu.ufape.lmts.sgu.pdi.model.HistoricoStatus;

@Service
public class HistoricoStatusService implements HistoricoStatusServiceInterface {
	@Autowired
	private HistoricoStatusRepository repository;


	public HistoricoStatus saveHistoricoStatus(HistoricoStatus newInstance) {
		return repository.save(newInstance);
	}

	public HistoricoStatus updateHistoricoStatus(HistoricoStatus transientObject) {
		return repository.save(transientObject);
	}

	public HistoricoStatus findHistoricoStatusById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist HistoricoStatus with id = " + id));
	}

	public Page<HistoricoStatus> getAllHistoricoStatus(Predicate predicate, Pageable pageable){
		return repository.findAll(predicate, pageable);
	}

	public void deleteHistoricoStatus(HistoricoStatus persistentObject){
		this.deleteHistoricoStatus(persistentObject.getId());
		
	}
	
	public void deleteHistoricoStatus(long id){
		HistoricoStatus obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist HistoricoStatus with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}