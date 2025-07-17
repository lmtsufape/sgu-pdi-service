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
public  class HistoricoStatusResponse  {
	private Long id;
	private int valor;
	private Date dataAtualizacao;
	private String usuarioAtualizacao;
	private MetaResponse meta; 



	public HistoricoStatusResponse(HistoricoStatus obj) {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		modelMapper.map(obj, this);	
	}

}
