package com.nelioalves.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) { // Método responsável por buscar uma Categoria pelo ID
		 Optional<Categoria> obj = repo.findById(id); // Tenta encontrar a categoria com o ID fornecido (retorna um Optional)
		return obj.orElseThrow(() -> new ObjectNotFoundException( // Se encontrar, retorna o objeto. Se não, lança uma exceção personalizada
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 
}

	public Categoria buscar(Integer id) {
		// TODO Auto-generated method stub
		return find(id);
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //Novo obj inserido, tem que ter um ID nulo, se já tiver algo com o mesmo ID, o metodo save (linha abaixo), vai considerar um atualização, não uma inserção nova
		return repo.save(obj);
	}
}