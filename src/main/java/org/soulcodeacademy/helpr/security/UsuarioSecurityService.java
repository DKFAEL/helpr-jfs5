package org.soulcodeacademy.helpr.security;

import org.soulcodeacademy.helpr.domain.Usuario;
import org.soulcodeacademy.helpr.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Carregar a entidade usuario do banco de dados
// e converter para o UsuarioSecurity
@Service
public class UsuarioSecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //Vai buscar no banco o usuário com o email indica em username
        Optional<Usuario> usuarioOptional = this.usuarioRepository.findByEmail(username);
        if (usuarioOptional.isEmpty()) {
            // Não encontrou o usuário com o email indicado em username
            throw  new UsernameNotFoundException("Usuário não encontrado!");
        } else {
            Usuario usuario = usuarioOptional.get();
            return  new UsuarioSecurity(usuario.getEmail(), usuario.getSenha(), usuario.getPerfil());
        }
    }
}
