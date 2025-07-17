package br.edu.ufape.lmts.sgu.pdi.model;

import java.util.*;
import java.math.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor 
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public  class HistoricoStatus  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private long id;
	private int valor;
	private Date dataAtualizacao;
	private String usuarioAtualizacao;
    	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "meta_id")
	@ToString.Exclude
	private Meta meta; 

}