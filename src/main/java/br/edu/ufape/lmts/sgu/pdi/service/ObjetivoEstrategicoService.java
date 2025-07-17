package br.edu.ufape.lmts.sgu.pdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.lmts.sgu.pdi.repository.ObjetivoEstrategicoRepository;
import br.edu.ufape.lmts.sgu.pdi.model.ObjetivoEstrategico;

@Service
public class ObjetivoEstrategicoService implements ObjetivoEstrategicoServiceInterface {
	@Autowired
	private ObjetivoEstrategicoRepository repository;


	public ObjetivoEstrategico saveObjetivoEstrategico(ObjetivoEstrategico newInstance) {
		return repository.save(newInstance);
	}

	public ObjetivoEstrategico updateObjetivoEstrategico(ObjetivoEstrategico transientObject) {
		return repository.save(transientObject);
	}

	public ObjetivoEstrategico findObjetivoEstrategicoById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist ObjetivoEstrategico with id = " + id));
	}

	public Page<ObjetivoEstrategico> getAllObjetivoEstrategico(Predicate predicate, Pageable pageable){
		return repository.findAll(predicate, pageable);
	}

	public void deleteObjetivoEstrategico(ObjetivoEstrategico persistentObject){
		this.deleteObjetivoEstrategico(persistentObject.getId());
		
	}
	
	public void deleteObjetivoEstrategico(long id){
		ObjetivoEstrategico obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist ObjetivoEstrategico with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}