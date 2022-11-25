package org.soulcodeacademy.helpr.services.erros;

//Esta classe representa o erro de regra de negócio
//quando não encontramos cargo,cliente,funcionarios,chamados
// no banco
public class RecursoNaoEncontradoError extends RuntimeException {
    public RecursoNaoEncontradoError(String message) {
        super(message); // Passamos a mensagem para a Runtime
    }
}
