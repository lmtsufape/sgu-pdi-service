package br.edu.ufape.lmts.sgu.pdi.controller.dto.response;

import java.util.*;
import java.math.*;
import br.edu.ufape.lmts.sgu.pdi.model.*;
import br.edu.ufape.lmts.sgu.pdi.config.SpringApplicationContext;
import org.modelmapper.ModelMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter @Setter @NoArgsConstructor
public  class ObjetivoEstrategicoResponse  {
	private Long id;
	private String codigo;
	private String descricao;
	private String eixo;



	public ObjetivoEstrategicoResponse(ObjetivoEstrategico obj) {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		modelMapper.map(obj, this);	
	}

}
