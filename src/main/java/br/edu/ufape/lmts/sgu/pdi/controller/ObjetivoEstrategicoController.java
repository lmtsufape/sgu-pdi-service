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
import br.edu.ufape.lmts.sgu.pdi.controller.dto.request.ObjetivoEstrategicoRequest;
import br.edu.ufape.lmts.sgu.pdi.controller.dto.response.ObjetivoEstrategicoResponse;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class ObjetivoEstrategicoController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("objetivoEstrategico")
	public Page<ObjetivoEstrategicoResponse> getAllObjetivoEstrategico(@QuerydslPredicate(root = ObjetivoEstrategico.class) Predicate predicate,
											   @PageableDefault(value = 2, page = 0) 
											   @SortDefault(sort = "id", direction = Direction.ASC)
											   Pageable pageable) {
		
		Page<ObjetivoEstrategico> pageObjetivoEstrategico = facade.getAllObjetivoEstrategico(predicate, pageable);
		List<ObjetivoEstrategicoResponse> listResponse = pageObjetivoEstrategico.getContent()
											 .stream()
											 .map(ObjetivoEstrategicoResponse::new)
											 .toList();
		
		return new PageImpl<ObjetivoEstrategicoResponse> (listResponse, pageable, pageObjetivoEstrategico.getTotalElements());
	}
	
	
	
	@PostMapping("objetivoEstrategico")
	public ObjetivoEstrategicoResponse createObjetivoEstrategico(@Valid @RequestBody ObjetivoEstrategicoRequest newObj) {
		return new ObjetivoEstrategicoResponse(facade.saveObjetivoEstrategico(newObj.convertToEntity()));
	}
	
	@GetMapping("objetivoEstrategico/{id}")
	public ObjetivoEstrategicoResponse getObjetivoEstrategicoById(@PathVariable Long id) {
		try {
			return new ObjetivoEstrategicoResponse(facade.findObjetivoEstrategicoById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ObjetivoEstrategico " + id + " not found.");
		}
	}
	
	@PatchMapping("objetivoEstrategico/{id}")
	public ObjetivoEstrategicoResponse updateObjetivoEstrategico(@PathVariable Long id, @Valid @RequestBody ObjetivoEstrategicoRequest obj) {
		try {
			//ObjetivoEstrategico o = obj.convertToEntity();
			ObjetivoEstrategico oldObject = facade.findObjetivoEstrategicoById(id);

			TypeMap<ObjetivoEstrategicoRequest, ObjetivoEstrategico> typeMapper = modelMapper
													.typeMap(ObjetivoEstrategicoRequest.class, ObjetivoEstrategico.class)
													.addMappings(mapper -> {
														mapper.skip(ObjetivoEstrategico::setId);
													});																
			typeMapper.map(obj, oldObject);		
			return new ObjetivoEstrategicoResponse(facade.updateObjetivoEstrategico(oldObject));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("objetivoEstrategico/{id}")
	public String deleteObjetivoEstrategico(@PathVariable Long id) {
		try {
			facade.deleteObjetivoEstrategico(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}
