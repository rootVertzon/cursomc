package com.nelioalves.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.services.ClienteService;

@RestController // Define que essa classe é um controlador REST (vai responder requisições HTTP)
@RequestMapping(value="/clientes") // Define o caminho base da URL para esse recurso: /clientes
public class ClienteResource {
	
	@Autowired
	private ClienteService service; 
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) // Mapeia requisições GET com o caminho /cliente/{id}
	public ResponseEntity<?> find(@PathVariable Integer id) {  // Retorna um ResponseEntity com a resposta HTTP (status + corpo)
		Cliente obj = service.buscar(id); // Chama o serviço para buscar um cliente pelo ID
		return ResponseEntity.ok().body(obj);// Retorna resposta com status 200 OK e o corpo contendo o cliente encontrado
		
	}
}
