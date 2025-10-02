package com.nelioalves.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.cursomc.domain.Categoria;
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
	
}
