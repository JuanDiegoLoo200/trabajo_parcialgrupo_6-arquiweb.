package pe.edu.upc.clearair.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.clearair.entities.Usuarios;
import pe.edu.upc.clearair.repositories.IUsuariosRepository;

import java.util.ArrayList;
import java.util.List;

//Clase 2 - Carga detalles del usuario por email para autenticacion JWT
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuariosRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(">>> [QUERY 1 - Usuarios] Ejecutando query: findByEmail -> Buscando usuario con email: " + email);
        Usuarios user = repo.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        if (user.getRol() != null) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRol().getNombre()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPasswordHash(),
                user.getEstaActivo(),
                true, true, true,
                roles
        );
    }
}
