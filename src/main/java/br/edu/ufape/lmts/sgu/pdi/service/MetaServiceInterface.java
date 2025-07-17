package br.edu.ufape.lmts.sgu.pdi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.querydsl.core.types.Predicate;

import br.edu.ufape.lmts.sgu.pdi.model.Meta;

public interface MetaServiceInterface {
	Meta saveMeta(Meta o);
	Meta findMetaById(long id);
	Meta updateMeta(Meta u);
	void deleteMeta(Meta u);
	void deleteMeta(long id);
	Page<Meta> getAllMeta(Predicate predicate, Pageable pageable);
    
    

    
}