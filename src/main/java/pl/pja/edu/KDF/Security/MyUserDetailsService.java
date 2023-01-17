package pl.pja.edu.KDF.Security;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.pja.edu.KDF.Domain.Authority;
import pl.pja.edu.KDF.Domain.Owner;
import pl.pja.edu.KDF.Repository.OwnerRepository;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


@Component("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    private final OwnerRepository ownerRepository;

    Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

    public MyUserDetailsService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Authenticating {}", username);

        if (new EmailValidator().isValid(username, null)) {
            return ownerRepository
                    .findOneWithAuthoritiesByEmailIgnoreCase(username)
                    .map(user -> createSpringSecurityUser(username, user))
                    .orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " was not found in the database"));
        }

        String lowercaseLogin = username.toLowerCase(Locale.ENGLISH);
        return ownerRepository
                .findOneWithAuthoritiesByLogin(lowercaseLogin)
                .map(user -> createSpringSecurityUser(lowercaseLogin, user))
                .orElseThrow(() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, Owner user) {
        if (!user.isActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user
                .getAuthorities()
                .stream()
                .map(Authority::getName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
}
