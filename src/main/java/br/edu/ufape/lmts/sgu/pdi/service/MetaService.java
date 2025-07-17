package br.edu.ufape.lmts.sgu.pdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.ufape.lmts.sgu.pdi.repository.MetaRepository;
import br.edu.ufape.lmts.sgu.pdi.model.Meta;

@Service
public class MetaService implements MetaServiceInterface {
	@Autowired
	private MetaRepository repository;


	public Meta saveMeta(Meta newInstance) {
		return repository.save(newInstance);
	}

	public Meta updateMeta(Meta transientObject) {
		return repository.save(transientObject);
	}

	public Meta findMetaById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Meta with id = " + id));
	}

	public Page<Meta> getAllMeta(Predicate predicate, Pageable pageable){
		return repository.findAll(predicate, pageable);
	}

	public void deleteMeta(Meta persistentObject){
		this.deleteMeta(persistentObject.getId());
		
	}
	
	public void deleteMeta(long id){
		Meta obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Meta with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}