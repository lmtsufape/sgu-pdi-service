package br.edu.ufape.lmts.sgu.pdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.lmts.sgu.pdi.repository.ObjetivoEspecificoRepository;
import br.edu.ufape.lmts.sgu.pdi.model.ObjetivoEspecifico;

@Service
public class ObjetivoEspecificoService implements ObjetivoEspecificoServiceInterface {
	@Autowired
	private ObjetivoEspecificoRepository repository;


	public ObjetivoEspecifico saveObjetivoEspecifico(ObjetivoEspecifico newInstance) {
		return repository.save(newInstance);
	}

	public ObjetivoEspecifico updateObjetivoEspecifico(ObjetivoEspecifico transientObject) {
		return repository.save(transientObject);
	}

	public ObjetivoEspecifico findObjetivoEspecificoById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist ObjetivoEspecifico with id = " + id));
	}

	public Page<ObjetivoEspecifico> getAllObjetivoEspecifico(Predicate predicate, Pageable pageable){
		return repository.findAll(predicate, pageable);
	}

	public void deleteObjetivoEspecifico(ObjetivoEspecifico persistentObject){
		this.deleteObjetivoEspecifico(persistentObject.getId());
		
	}
	
	public void deleteObjetivoEspecifico(long id){
		ObjetivoEspecifico obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist ObjetivoEspecifico with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}