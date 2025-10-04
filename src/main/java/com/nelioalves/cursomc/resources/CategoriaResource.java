package com.nelioalves.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.services.CategoriaService;

@RestController // Define que essa classe é um controlador REST (vai responder requisições HTTP)
@RequestMapping(value="/categorias") // Define o caminho base da URL para esse recurso: /categorias
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service; 
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // Mapeia requisições GET com o caminho /categorias/{id}
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {  // Retorna um ResponseEntity com a resposta HTTP (status + corpo)
		Categoria obj = service.find(id); // Chama o serviço para buscar a categoria pelo ID
		return ResponseEntity.ok().body(obj);// Retorna resposta com status 200 OK e o corpo contendo a categoria encontrada
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	@RequestMapping(method=RequestMethod.GET) 
	public ResponseEntity<List<CategoriaDTO>> findAll() { 
		List<Categoria> list = service.findAll(); 
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	//pq nessa parte de cima eu gero um collect e na parte de baixo não?
	
	@RequestMapping(value="/page", method=RequestMethod.GET) 
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="LinesPerPage", defaultValue="24")Integer linesPerPage,// 24 linhas é bom pq é multiplos de 1,2,3 e 4, mais facil para mostrar na página
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="Direction", defaultValue="ASC") String direction) { 
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction); 
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
