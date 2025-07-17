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
public  class ObjetivoEspecifico  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private long id;
	private int codigo;
	private String descricao;
    	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "objetivoEstrategico_id")
	@ToString.Exclude
	private ObjetivoEstrategico objetivoEstrategico; 

}