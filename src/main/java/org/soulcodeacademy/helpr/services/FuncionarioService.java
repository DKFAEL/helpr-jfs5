package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listar(){
        return this.funcionarioRepository.findAll();  //FindAll (todo mundo)
    }

    public Funcionario getFuncionario(Integer idFuncionario){
        Optional<Funcionario> funcionario = this.funcionarioRepository.findById(idFuncionario);
        if (funcionario.isEmpty()){
            throw new RuntimeException("O funcionário não foi encontrado!");
        } else {
            return  funcionario.get(); // pegaovalor da entidade encontrada
        }
    }
}
