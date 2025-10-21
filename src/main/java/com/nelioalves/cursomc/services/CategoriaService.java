package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDTO;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) { // Método responsável por find uma Categoria pelo ID
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
	
	public Categoria update(Categoria obj) {
		find(obj.getId()); //Se o Id não existir, vai lançar uma exceção 
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
			
			}
		}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),orderBy);
		return repo.findAll(pageRequest);
		
		
}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
	}
	