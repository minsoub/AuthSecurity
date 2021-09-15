package kr.co.hist.security.service;

import kr.co.hist.security.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsVO loadUserByUsername(String email) {
        return userRepository.findByUserEmail(email).map(u->new UserDetailsVO(u, Collections.singleton(
                new SimpleGrantedAuthority(u.getRole().getValue()))).orElseThrow(()->new UserNotFoundException(email));
    }
}
