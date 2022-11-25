package org.soulcodeacademy.helpr.services.erros;

//Esta classe de erro representa quando uma operação está
//faltando dados. Ex: Atualizar -> idFuncionario nuulo
public class ParametrosInsuficientesError extends  RuntimeException{
    public  ParametrosInsuficientesError(String message) {
        super(message);
    }
}
