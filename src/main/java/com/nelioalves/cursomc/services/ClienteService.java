package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) { // Método responsável por buscar um Cliente pelo ID
		 Optional<Cliente> obj = repo.findById(id); // Tenta encontrar um Cliente com o ID fornecido (retorna um Optional)
		return obj.orElseThrow(() -> new ObjectNotFoundException( // Se encontrar, retorna o objeto. Se não, lança uma exceção personalizada
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 
}

	public Cliente buscar(Integer id) {
		// TODO Auto-generated method stub
		return find(id);
	}

}