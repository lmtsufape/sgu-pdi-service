package br.edu.ufape.lmts.sgu.pdi.controller.dto.request;

import br.edu.ufape.lmts.sgu.pdi.config.SpringApplicationContext;
import br.edu.ufape.lmts.sgu.pdi.model.*;

import java.util.*;
import java.math.*;

import org.modelmapper.ModelMapper;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;


@Getter @Setter @NoArgsConstructor 
public  class ObjetivoEspecificoRequest  {
	private long id;
	private int codigo;
	private String descricao;
    	@JsonInclude(JsonInclude.Include.ALWAYS)
	private ObjetivoEstrategicoRequest objetivoEstrategico; 
	@JsonIgnore
	private boolean objetivoEstrategicoExplicitamenteNula = false;	
	@JsonSetter("objetivoEstrategico")
	public void setObjetivoEstrategico(ObjetivoEstrategicoRequest objetivoEstrategico) {
		this.objetivoEstrategico = objetivoEstrategico;
		this.objetivoEstrategicoExplicitamenteNula = (objetivoEstrategico == null);
	}	


	public ObjetivoEspecifico convertToEntity() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		ObjetivoEspecifico obj = modelMapper.map(this, ObjetivoEspecifico.class);
		return obj;
	}





}
