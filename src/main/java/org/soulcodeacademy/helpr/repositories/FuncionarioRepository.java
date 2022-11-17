package org.soulcodeacademy.helpr.repositories;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> { // Generics


    List<Funcionario> findByCargo(Cargo cargo); // Filtrar os funcionarios que possuem o cargo
    List<Funcionario> findByFotoIsNull(); // Filtra funcionariosem foto definida
    List<Funcionario> findByFotoIsNotNull(); // Filtra funcionario com foto definida

}
