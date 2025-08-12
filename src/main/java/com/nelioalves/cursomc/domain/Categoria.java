package com.nelioalves.cursomc.domain; /*Declara o pacote onde a classe está — só organiza os arquivos no projeto*/

import java.io.Serializable; 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity // Diz que essa classe é uma entidade JPA
public class Categoria implements Serializable { /*permitir que objetos possam ser convertidos para bytes (enviar pela rede*/
	private static final long serialVersionUID = 1L;
	
	
	@Id // Define o campo como chave primária
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Geração automática do ID
	private Integer id;
	private String nome;
	
	// Relacionamento muitos p muitos com Produto (mapeado pelo lado 'categorias' em Produto)
	@JsonManagedReference //evitar loop infinito na hora de gerar JSON 
	@ManyToMany(mappedBy="categorias")
	private List<Produto> produtos = new ArrayList<>();
	
	
	// Construtor com os atributos principais
	public Categoria() {
	}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	
    // Getters e setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	// hashCode baseado apenas no ID
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// equals baseado apenas no ID
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
}
