package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.dto.ClienteDTO;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.services.erros.RecursoNaoEncontradoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //Torna o objeto da classe injetavel
public class ClienteService {
@Autowired // injeção x:
    private ClienteRepository clienteRepository;
    public List<Cliente> listar () {
        return  this.clienteRepository.findAll();
    }

    public  Cliente getCliente(Integer idCliente) {

        Optional<Cliente> cliente = this.clienteRepository.findById(idCliente);
        if (cliente.isEmpty()) {
            throw  new RecursoNaoEncontradoError("Cliente não Encontrado! :( ");
        } else {
            return  cliente.get();
        }
    }

    public Cliente salvar( ClienteDTO dto) {
        // Criação de entidade Cliente, a partir dos dados validados do DTO
        Cliente cliente = new Cliente(null, dto.getNome(),dto.getEmail(),dto.getCpf(),dto.getSenha(),dto.getTelefone());
        Cliente salvo = this.clienteRepository.save(cliente);
        return salvo;
    }

    public Cliente atualizar (Integer idCliente, ClienteDTO dto){
        Cliente cliente = this.getCliente(idCliente);

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setCpf(dto.getCpf());
        cliente.setSenha(dto.getSenha());
        cliente.setTelefone(dto.getTelefone());

        Cliente atualizado = this.clienteRepository.save(cliente);
        return atualizado;
    }

    public void deletar(Integer idCliente){
        Cliente cliente = this.getCliente(idCliente);
        this.clienteRepository.delete(cliente);
    }
}
