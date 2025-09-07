package com.nelioalves.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nelioalves.cursomc.domain.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}


/*esse codigo faz a funçoes findAll(), findById(), save(), delete() sem escrever código, 
 * pois, JpaRepository é uma interface do Spring Data JPA que já 
 * traz um monte de métodos prontos para manipular dados no banco*/
