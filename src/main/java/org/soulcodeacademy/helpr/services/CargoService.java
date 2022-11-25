package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.dto.CargoDTO;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.soulcodeacademy.helpr.services.erros.RecursoNaoEncontradoError;
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
            throw new RecursoNaoEncontradoError("O cargo não foi encontrado!");
        } else {
            return  cargo.get(); // Extrair o cargo de dentro do optional
        }
    }
    //SALVAR
    public Cargo salvar (CargoDTO   dto){

        // INSERT INTO cargo
        //Criando uma entidade nova a partir do DTO
        Cargo cargo = new Cargo(null, dto.getNome(), dto.getDescricao(), dto.getSalario());
        Cargo cargoSalvo = this.cargoRepository.save(cargo);
        return cargoSalvo;
    }
    //ATUALIZAR
    public Cargo atualizar(Integer idCargo, CargoDTO dto){
    // verificar se o cargo existe mesmo
        Cargo cargoAtual = this.getCargo(idCargo);

        cargoAtual.setNome(dto.getNome());
        cargoAtual.setDescricao(dto.getDescricao());
        cargoAtual.setSalario(dto.getSalario());

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

