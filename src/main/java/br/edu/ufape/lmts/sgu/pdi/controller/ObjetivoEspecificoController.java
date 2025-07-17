package br.edu.ufape.lmts.sgu.pdi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;

import br.edu.ufape.lmts.sgu.pdi.model.*;
import br.edu.ufape.lmts.sgu.pdi.facade.Facade;
import br.edu.ufape.lmts.sgu.pdi.controller.dto.request.ObjetivoEspecificoRequest;
import br.edu.ufape.lmts.sgu.pdi.controller.dto.response.ObjetivoEspecificoResponse;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class ObjetivoEspecificoController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("objetivoEspecifico")
	public Page<ObjetivoEspecificoResponse> getAllObjetivoEspecifico(@QuerydslPredicate(root = ObjetivoEspecifico.class) Predicate predicate,
											   @PageableDefault(value = 2, page = 0) 
											   @SortDefault(sort = "id", direction = Direction.ASC)
											   Pageable pageable) {
		
		Page<ObjetivoEspecifico> pageObjetivoEspecifico = facade.getAllObjetivoEspecifico(predicate, pageable);
		List<ObjetivoEspecificoResponse> listResponse = pageObjetivoEspecifico.getContent()
											 .stream()
											 .map(ObjetivoEspecificoResponse::new)
											 .toList();
		
		return new PageImpl<ObjetivoEspecificoResponse> (listResponse, pageable, pageObjetivoEspecifico.getTotalElements());
	}
	
	
	
	@PostMapping("objetivoEspecifico")
	public ObjetivoEspecificoResponse createObjetivoEspecifico(@Valid @RequestBody ObjetivoEspecificoRequest newObj) {
		return new ObjetivoEspecificoResponse(facade.saveObjetivoEspecifico(newObj.convertToEntity()));
	}
	
	@GetMapping("objetivoEspecifico/{id}")
	public ObjetivoEspecificoResponse getObjetivoEspecificoById(@PathVariable Long id) {
		try {
			return new ObjetivoEspecificoResponse(facade.findObjetivoEspecificoById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ObjetivoEspecifico " + id + " not found.");
		}
	}
	
	@PatchMapping("objetivoEspecifico/{id}")
	public ObjetivoEspecificoResponse updateObjetivoEspecifico(@PathVariable Long id, @Valid @RequestBody ObjetivoEspecificoRequest obj) {
		try {
			//ObjetivoEspecifico o = obj.convertToEntity();
			ObjetivoEspecifico oldObject = facade.findObjetivoEspecificoById(id);

			TypeMap<ObjetivoEspecificoRequest, ObjetivoEspecifico> typeMapper = modelMapper
													.typeMap(ObjetivoEspecificoRequest.class, ObjetivoEspecifico.class)
													.addMappings(mapper -> {
														mapper.skip(ObjetivoEspecifico::setId);
														mapper.when(ctx -> false).map(ObjetivoEspecificoRequest::getObjetivoEstrategico, ObjetivoEspecifico::setObjetivoEstrategico); 
													});																
			typeMapper.map(obj, oldObject);		
			if (obj.getObjetivoEstrategico() != null) {
			    if (obj.getObjetivoEstrategico().getId() != 0) {
			        ObjetivoEstrategico associacaoGerenciada = facade.findObjetivoEstrategicoById(obj.getObjetivoEstrategico().getId());
			        oldObject.setObjetivoEstrategico(associacaoGerenciada);
			    } else {
			        oldObject.setObjetivoEstrategico(null);
			    }
			} else if (obj.isObjetivoEstrategicoExplicitamenteNula()) {
			    oldObject.setObjetivoEstrategico(null);
			}
			return new ObjetivoEspecificoResponse(facade.updateObjetivoEspecifico(oldObject));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("objetivoEspecifico/{id}")
	public String deleteObjetivoEspecifico(@PathVariable Long id) {
		try {
			facade.deleteObjetivoEspecifico(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}
