package br.edu.ufape.lmts.sgu.pdi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import com.querydsl.core.types.dsl.StringPath;

import br.edu.ufape.lmts.sgu.pdi.model.Meta;
import br.edu.ufape.lmts.sgu.pdi.model.QMeta;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long>, 
										   PagingAndSortingRepository<Meta, Long>,
										   QuerydslPredicateExecutor<Meta>,
										   QuerydslBinderCustomizer<QMeta>{
    @Override
    default public void customize(QuerydslBindings bindings, QMeta root) {
        bindings.bind(String.class).first((StringPath path, String value) -> path.containsIgnoreCase(value));
    }
	

}