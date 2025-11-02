package com.nelioalves.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDTO;
import com.nelioalves.cursomc.services.ClienteService;

@RestController // Define que essa classe é um controlador REST (vai responder requisições HTTP)
@RequestMapping(value="/clientes") // Define o caminho base da URL para esse recurso: /clientes
public class ClienteResource {
	
	@Autowired
	private ClienteService service; 
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // Mapeia requisições GET com o caminho /cliente/{id}
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {  // Retorna um ResponseEntity com a resposta HTTP (status + corpo)
		Cliente obj = service.find(id); // Chama o serviço para buscar um cliente pelo ID
		return ResponseEntity.ok().body(obj);// Retorna resposta com status 200 OK e o corpo contendo o cliente encontrado
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<ClienteDTO>> findAll() { 
		List<Cliente> list = service.findAll(); 
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	//pq nessa parte de cima eu gero um collect e na parte de baixo não?
	
	@RequestMapping(value="/page", method=RequestMethod.GET) 
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="LinesPerPage", defaultValue="24")Integer linesPerPage,// 24 linhas é bom pq é multiplos de 1,2,3 e 4, mais facil para mostrar na página
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			@RequestParam(value="Direction", defaultValue="ASC") String direction) { 
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction); 
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
	
	
	
}
