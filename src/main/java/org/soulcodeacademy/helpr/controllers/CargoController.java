package org.soulcodeacademy.helpr.controllers;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/cargos")
    public List<Cargo> listar(){
        // Requisição -> Controller -> Service -> Repository -> SELECT * FROM cargp;d
        return this.cargoService.listar() ;
    }

    @GetMapping("/cargos/{idCargo}")
    public Cargo getCargo(@PathVariable Integer idCargo){
        //@PathVariable -> extrai do endpoint o valor diinâmico
        return this.cargoService.getCargo(idCargo);
    }
}