package org.soulcodeacademy.helpr.services;

import org.soulcodeacademy.helpr.domain.Cargo;
import org.soulcodeacademy.helpr.domain.Chamado;
import org.soulcodeacademy.helpr.domain.Cliente;
import org.soulcodeacademy.helpr.domain.Funcionario;
import org.soulcodeacademy.helpr.domain.enums.Perfil;
import org.soulcodeacademy.helpr.domain.enums.StatusChamado;
import org.soulcodeacademy.helpr.repositories.CargoRepository;
import org.soulcodeacademy.helpr.repositories.ChamadoRepository;
import org.soulcodeacademy.helpr.repositories.ClienteRepository;
import org.soulcodeacademy.helpr.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

// Torna o objeto de PopulateService disponível para toda a aplicação (global)
@Service // indica para o Spring que esta classe será gerenciada por ele
public class PopulateService {
    @Autowired // injetar o objeto direto na classe
    private CargoRepository cargoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private PasswordEncoder encoder;

    public void populate() {
        // Integer idCargo, String nome, String descricao, Double salario
        Cargo c1 = new Cargo(null, "Diretor Geral", "Gerencia a empresa", 30000.0);
        Cargo c2 = new Cargo(null, "Diretor de Setor", "Gerencia um setor da empresa", 18000.0);
        Cargo c3 = new Cargo(null, "Técnico geral", "Resolve os chamados urgentes", 12000.0);

        Funcionario f1 = new Funcionario(null,"Renato Pereira", "renato.pereira@gmail.com","68258098144", encoder.encode("12345"), null, c1);
        f1.setPerfil(Perfil.ADMIN);
        Funcionario f2 = new Funcionario(null,"Raulf Luiz", "raulf@gmail.com","68258123144", encoder.encode("54321"), null, c2);

        Cliente cli1 = new Cliente(null, "Rafael Nunes","rafaelnns@gmail.com","07987654412",encoder.encode("12345"), "71984085932");
        Cliente cli2 = new Cliente(null, "Lucas Nunes","lucasnns@gmail.com","11287654412",encoder.encode("54321"),"71984085932");

        Chamado ch1 = new Chamado(null,"Primeiro chamado do sistema","Revisar as entidades criadas");
        ch1.setCliente(cli1);

        Chamado ch2 = new Chamado(null,"Segundo chamado do sistema","alterar as entidades criadas");
        ch2.setCliente(cli2);
        ch2.setFuncionario(f1);
        ch2.setStatus(StatusChamado.ATRIBUIDO);

        // vamos persistir as entidades = salvar no banco
        this.cargoRepository.saveAll(List.of(c1,c2,c3));
        this.funcionarioRepository.saveAll(List.of(f1,f2));
        this.clienteRepository.saveAll(List.of(cli1,cli2));
        this.chamadoRepository.saveAll(List.of(ch1,ch2));
    }
}

// O objetivo desta classe é inserir no banco, dados fictícios (de teste)
// IOC = Inversion of Control = Inversão de Controle = É ele quem manda nas instâncias
// Container = é o local onde o Spring guarda os objetos anotados
// @Service = indica que a classe é um serviço
// Injeção de Dependências = quando o Spring injeta os objetos na classe