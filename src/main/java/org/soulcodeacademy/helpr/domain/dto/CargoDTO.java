package org.soulcodeacademy.helpr.domain.dto;

//DTO- Objeto de transferência de dados
// É util para validarmos as informações  transferida pelo cliente

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CargoDTO {
    //Impede que o valor de nome seja "", por exemplo.
    //Messege é o texto da validação
@NotBlank(message = "Campo nome é obrigatorio")
    private String nome;
    @NotBlank(message = "Campo descricao é obrigatorio")
    private String descricao;
    @NotNull(message = "Campo salário é obrigatorio")
    @Min(value = 500, message = "Campo salário invalido")
    @Max(value = 100000, message = "Campo salário invalido")
    private Double salario ;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descrico) {
        this.descricao = descrico;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
