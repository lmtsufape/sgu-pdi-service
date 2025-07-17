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
import br.edu.ufape.lmts.sgu.pdi.controller.dto.request.HistoricoStatusRequest;
import br.edu.ufape.lmts.sgu.pdi.controller.dto.response.HistoricoStatusResponse;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/api/v1/")
public class HistoricoStatusController {
	@Autowired
	private Facade facade;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("historicoStatus")
	public Page<HistoricoStatusResponse> getAllHistoricoStatus(@QuerydslPredicate(root = HistoricoStatus.class) Predicate predicate,
											   @PageableDefault(value = 2, page = 0) 
											   @SortDefault(sort = "id", direction = Direction.ASC)
											   Pageable pageable) {
		
		Page<HistoricoStatus> pageHistoricoStatus = facade.getAllHistoricoStatus(predicate, pageable);
		List<HistoricoStatusResponse> listResponse = pageHistoricoStatus.getContent()
											 .stream()
											 .map(HistoricoStatusResponse::new)
											 .toList();
		
		return new PageImpl<HistoricoStatusResponse> (listResponse, pageable, pageHistoricoStatus.getTotalElements());
	}
	
	
	
	@PostMapping("historicoStatus")
	public HistoricoStatusResponse createHistoricoStatus(@Valid @RequestBody HistoricoStatusRequest newObj) {
		return new HistoricoStatusResponse(facade.saveHistoricoStatus(newObj.convertToEntity()));
	}
	
	@GetMapping("historicoStatus/{id}")
	public HistoricoStatusResponse getHistoricoStatusById(@PathVariable Long id) {
		try {
			return new HistoricoStatusResponse(facade.findHistoricoStatusById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HistoricoStatus " + id + " not found.");
		}
	}
	
	@PatchMapping("historicoStatus/{id}")
	public HistoricoStatusResponse updateHistoricoStatus(@PathVariable Long id, @Valid @RequestBody HistoricoStatusRequest obj) {
		try {
			//HistoricoStatus o = obj.convertToEntity();
			HistoricoStatus oldObject = facade.findHistoricoStatusById(id);

			TypeMap<HistoricoStatusRequest, HistoricoStatus> typeMapper = modelMapper
													.typeMap(HistoricoStatusRequest.class, HistoricoStatus.class)
													.addMappings(mapper -> {
														mapper.skip(HistoricoStatus::setId);
														mapper.when(ctx -> false).map(HistoricoStatusRequest::getMeta, HistoricoStatus::setMeta); 
													});																
			typeMapper.map(obj, oldObject);		
			if (obj.getMeta() != null) {
			    if (obj.getMeta().getId() != 0) {
			        Meta associacaoGerenciada = facade.findMetaById(obj.getMeta().getId());
			        oldObject.setMeta(associacaoGerenciada);
			    } else {
			        oldObject.setMeta(null);
			    }
			} else if (obj.isMetaExplicitamenteNula()) {
			    oldObject.setMeta(null);
			}
			return new HistoricoStatusResponse(facade.updateHistoricoStatus(oldObject));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("historicoStatus/{id}")
	public String deleteHistoricoStatus(@PathVariable Long id) {
		try {
			facade.deleteHistoricoStatus(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}
