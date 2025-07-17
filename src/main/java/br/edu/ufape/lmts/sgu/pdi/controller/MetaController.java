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
import br.edu.ufape.lmts.sgu.pdi.controller.dto.request.MetaRequest;
import br.edu.ufape.lmts.sgu.pdi.controller.dto.response.MetaResponse;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class MetaController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("meta")
	public Page<MetaResponse> getAllMeta(@QuerydslPredicate(root = Meta.class) Predicate predicate,
											   @PageableDefault(value = 2, page = 0) 
											   @SortDefault(sort = "id", direction = Direction.ASC)
											   Pageable pageable) {
		
		Page<Meta> pageMeta = facade.getAllMeta(predicate, pageable);
		List<MetaResponse> listResponse = pageMeta.getContent()
											 .stream()
											 .map(MetaResponse::new)
											 .toList();
		
		return new PageImpl<MetaResponse> (listResponse, pageable, pageMeta.getTotalElements());
	}
	
	
	
	@PostMapping("meta")
	public MetaResponse createMeta(@Valid @RequestBody MetaRequest newObj) {
		return new MetaResponse(facade.saveMeta(newObj.convertToEntity()));
	}
	
	@GetMapping("meta/{id}")
	public MetaResponse getMetaById(@PathVariable Long id) {
		try {
			return new MetaResponse(facade.findMetaById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Meta " + id + " not found.");
		}
	}
	
	@PatchMapping("meta/{id}")
	public MetaResponse updateMeta(@PathVariable Long id, @Valid @RequestBody MetaRequest obj) {
		try {
			//Meta o = obj.convertToEntity();
			Meta oldObject = facade.findMetaById(id);

			TypeMap<MetaRequest, Meta> typeMapper = modelMapper
													.typeMap(MetaRequest.class, Meta.class)
													.addMappings(mapper -> {
														mapper.skip(Meta::setId);
														mapper.when(ctx -> false).map(MetaRequest::getObjetivoEspecifico, Meta::setObjetivoEspecifico); 
													});																
			typeMapper.map(obj, oldObject);		
			if (obj.getObjetivoEspecifico() != null) {
			    if (obj.getObjetivoEspecifico().getId() != 0) {
			        ObjetivoEspecifico associacaoGerenciada = facade.findObjetivoEspecificoById(obj.getObjetivoEspecifico().getId());
			        oldObject.setObjetivoEspecifico(associacaoGerenciada);
			    } else {
			        oldObject.setObjetivoEspecifico(null);
			    }
			} else if (obj.isObjetivoEspecificoExplicitamenteNula()) {
			    oldObject.setObjetivoEspecifico(null);
			}
			return new MetaResponse(facade.updateMeta(oldObject));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("meta/{id}")
	public String deleteMeta(@PathVariable Long id) {
		try {
			facade.deleteMeta(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}
