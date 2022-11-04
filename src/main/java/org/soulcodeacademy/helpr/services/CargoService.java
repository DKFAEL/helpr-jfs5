package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {
    @Autowired
    private CargoRepository cargoRepository;

//Listar todos
public List<Cargo> listar(){
    //Retorna os dados databela em forma de lista
    return  this.cargoRepository.findAll();
}
// Listar um pelo ID
    public Cargo getCargo(Integer idCargo) {
         //SELECT * FROM cargo WHERE idCargo = ?
        // Optional = pode haver cargo ou não
        Optional<Cargo> cargo = this.cargoRepository.findById(idCargo);

        if (cargo.isEmpty()){  // Não encontrou o cargo?
            //Não encontrou o cargo com id solicitado
            throw new RuntimeException("O cargo não foi encontrado!");
        } else {
            return  cargo.get(); // Extrair o cargo de dentro do optional
        }
    }
    //SALVAR
    public Cargo salvar (Cargo novoCargo){
     novoCargo.setIdCargo(null); // Limpar o campo id para não substituir
        // INSERT INTO cargo
        Cargo cargoSalvo = this.cargoRepository.save(novoCargo);
        return cargoSalvo;
    }
    //ATUALIZAR
    public Cargo atualizar(Integer idCargo, Cargo novoCargo){
    // verificar se o cargo existe mesmo
        Cargo cargoAtual = this.getCargo(idCargo);

        cargoAtual.setNome(novoCargo.getNome());
        cargoAtual.setDescricao(novoCargo.getDescricao());
        cargoAtual.setSalario(novoCargo.getSalario());

      Cargo atualizado =  this.cargoRepository.save(cargoAtual);
      return atualizado;
    }
    //DELETAR
    public void deletar (Integer idCargo){
    //DELETE FROM cargo WEHRE idCargo
        Cargo cargo = this.getCargo(idCargo);
        this.cargoRepository.delete(cargo);
    }
}

