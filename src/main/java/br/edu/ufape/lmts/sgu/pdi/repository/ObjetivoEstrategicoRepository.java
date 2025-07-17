package br.edu.ufape.lmts.sgu.pdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.querydsl.core.types.dsl.StringPath;

import br.edu.ufape.lmts.sgu.pdi.model.ObjetivoEstrategico;
import br.edu.ufape.lmts.sgu.pdi.model.QObjetivoEstrategico;

@Repository
public interface ObjetivoEstrategicoRepository extends JpaRepository<ObjetivoEstrategico, Long>, 
										   PagingAndSortingRepository<ObjetivoEstrategico, Long>,
										   QuerydslPredicateExecutor<ObjetivoEstrategico>,
										   QuerydslBinderCustomizer<QObjetivoEstrategico>{
    @Override
    default public void customize(QuerydslBindings bindings, QObjetivoEstrategico root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
	

}