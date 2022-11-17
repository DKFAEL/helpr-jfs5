package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.dto.ChamadoDTO;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {
    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private  ClienteService clienteService ;

    @Autowired
    FuncionarioService funcionarioService;

    public List<Chamado> ListarChamadoss(){
        return  this.chamadoRepository.findAll();
    }

    public Chamado getChamado (Integer idChamado) {
    return  this.chamadoRepository.findById(idChamado)
            .orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
    }

    public Chamado salvar (ChamadoDTO dto) {
    Cliente cliente = this.clienteService.getCliente(dto.getIdCliente());

    Chamado chamado = new Chamado(null, dto.getTitulo(), dto.getDescricao());
    chamado.setCliente(cliente); // associa  o  cliente ao chamado
    return this.chamadoRepository.save(chamado);
    }

    public  Chamado atualizar (Integer idChamado, ChamadoDTO dto){
        Chamado chamado = this.getChamado(idChamado);
        Cliente cliente = this.clienteService.getCliente(dto.getIdCliente());
        chamado.setTitulo(dto.getTitulo());
        chamado.setDescricao(dto.getDescricao());
        chamado.setCliente(cliente);
        if(dto.getIdFuncionario() == null) {
            throw new RuntimeException("idFuncionario obrigatório");
        } else {
            Funcionario funcionario = this.funcionarioService.getFuncionario(dto.getIdFuncionario());

            switch (dto.getStatus()) {
                case RECEBIDO -> {
                    chamado.setStatus(StatusChamado.RECEBIDO);
                    chamado.setFuncionario(null);
                    chamado.setDataFechamento(null);
                }
                case ATRIBUIDO -> {
                    chamado.setStatus(StatusChamado.CONCLUIDO);
                    chamado.setFuncionario(funcionario);
                    chamado.setDataFechamento(LocalDate.now());
                }
            }
        }

        return   this.chamadoRepository.save(chamado);

    }

    public List<Chamado> listarPorStatus(StatusChamado status) {
        return  this.chamadoRepository.findByStatus(status);
    }

    public List<Chamado> listarPorFuncionario(Integer idFuncionario) {
        Funcionario funcionario = this.funcionarioService.getFuncionario(idFuncionario);
        return  this.chamadoRepository.findByFuncionario(funcionario);
    }

    public List<Chamado> listarPorCliente (Integer idCliente) {
        Cliente cliente = this.clienteService.getCliente(idCliente);
        return this.chamadoRepository.findByCliente(cliente);
    }

    public List<Chamado> listarPorIntervaloDatas (LocalDate data1, LocalDate data2){
        return  this.chamadoRepository.buscarEntreDatas(data1, data2);
    }
}
