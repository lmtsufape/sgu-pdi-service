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
public  class ObjetivoEstrategicoRequest  {
	private long id;
	private String codigo;
	private String descricao;
	private String eixo;


	public ObjetivoEstrategico convertToEntity() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		ObjetivoEstrategico obj = modelMapper.map(this, ObjetivoEstrategico.class);
		return obj;
	}





}
