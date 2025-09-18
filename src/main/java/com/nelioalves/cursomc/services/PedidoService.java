package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido find(Integer id) { // Método responsável por buscar uma Pedido pelo ID
		 Optional<Pedido> obj = repo.findById(id); // Tenta encontrar a categoria com o ID fornecido (retorna um Optional)
		return obj.orElseThrow(() -> new ObjectNotFoundException( // Se encontrar, retorna o objeto. Se não, lança uma exceção personalizada
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())); 
}

	public Pedido buscar(Integer id) {
		// TODO Auto-generated method stub
		return find(id);
	}

}