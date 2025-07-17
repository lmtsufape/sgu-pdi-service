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
public  class HistoricoStatusRequest  {
	private long id;
	private int valor;
	private Date dataAtualizacao;
	private String usuarioAtualizacao;
    	@JsonInclude(JsonInclude.Include.ALWAYS)
	private MetaRequest meta; 
	@JsonIgnore
	private boolean metaExplicitamenteNula = false;	
	@JsonSetter("meta")
	public void setMeta(MetaRequest meta) {
		this.meta = meta;
		this.metaExplicitamenteNula = (meta == null);
	}	


	public HistoricoStatus convertToEntity() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		HistoricoStatus obj = modelMapper.map(this, HistoricoStatus.class);
		return obj;
	}





}
