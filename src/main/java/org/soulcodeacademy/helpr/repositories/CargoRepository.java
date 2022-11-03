package org.soulcodeacademy.helpr.repositories;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // indica que a interface é um repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> { // <Entidade, PK>
}

// Repository = é um recurso que permite manipular a entidade no banco de dados
// Adicionar entidades, atualizar entidades, remover entidades e listar (CRUD)
// Indicar qual a entidade vai ser gerenciada pelo repository e qual o tipo da PK.
// Obs: O Spring gera uma classe dinâmica com base em nossa interface CargoRepository
// CrudRepository = contém as operações básicas de um banco de dados
// JpaRepository = possui mais funcionalidades p/ tratar a entidade



//Cargo

//CargoRepository

//Cargo Service ...         TEM QUE CONTER ESSES 3