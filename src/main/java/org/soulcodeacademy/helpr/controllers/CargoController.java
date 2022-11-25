package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.dto.CargoDTO;
import org.soulcodeacademy.helpr.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController //Retornos de dados
public class CargoController {

    //Endpoint é o endereço que será acessado no backend
    @GetMapping("/oi") //com base em localhosto8080/oi retorna a String
    public String dizOla(){
        return "Olá Mundo!"; // resposta da requisição
    }

    @GetMapping("/batata2")
    public Integer valor() {
        return 1000;  // resposta da requisição
    }

    @Autowired
    private CargoService cargoService;
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO')")
    @GetMapping("/cargos")
    public List<Cargo> listar(){
        // Requisição -> Controller -> Service -> Repository -> SELECT * FROM cargp;d
        return this.cargoService.listar() ;
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FUNCIONARIO')")
    @GetMapping("/cargos/{idCargo}")
    public Cargo getCargo(@PathVariable Integer idCargo){
        //@PathVariable -> extrai do endpoint o valor dinâmico
        return this.cargoService.getCargo(idCargo);
    }

    //Podemos usar o mesmo endpoint para verbos diferentes
    @PreAuthorize("Hasrole('ROLE_ADMIN')")
    @PostMapping("/cargos") // REQUISIÇÃO TIPO POST PARA/CARGOS
    public Cargo salvar(@Valid @RequestBody CargoDTO cargo) {
        // @RequestBody -extrair o  JSON do corpo e converter para Cargo (deserializar)
        Cargo salvo = this.cargoService.salvar(cargo);
        return salvo; // a resposta será o cargo inserido
    }
    @PreAuthorize("Hasrole('ROLE_ADMIN')")
    @PutMapping("/cargos/{idCargo}")
    public Cargo atualizar (@PathVariable Integer idCargo,@Valid @RequestBody CargoDTO cargo) {
        Cargo atualizado = this.cargoService.atualizar(idCargo, cargo);
        return atualizado;
    }
    @PreAuthorize("Hasrole('ROLE_ADMIN')")
    @DeleteMapping("/cargos/{idCargo}") // verbo DELETE no /cargo/1
    public void deletar (@PathVariable Integer idCargo) {
    this.cargoService.deletar(idCargo);
    }
}