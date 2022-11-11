package org.soulcodeacademy.helpr.domain.dto;


import javax.validation.constraints.NotBlank;

public class ClienteDTO  extends  UsuarioDTO{

@NotBlank(message = "O número de telefone é obrigatório")
    private String telefone;


    public String getTelefone() {           //Quando usar entidade e dto?
                                            //Entidade = retorno de dados
                                            //DTO      = Entrada de dados
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
