package kr.co.hist.user.service;

import kr.co.hist.user.repository.UserRepository;
import kr.co.hist.user.vo.LoginDTO;
import kr.co.hist.user.vo.UserVO;
import kr.co.hist.util.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserVO signUp(LoginDTO loginDTO) {
        final UserVO user = UserVO.builder()
                .id(loginDTO.getId())
                .passwd(passwordEncoder.encode(loginDTO.getPasswd()))
                .role(UserRole.ROLE_ADMIN)
                .isEnable(true)
                .build();

        return repository.save(user);  // findByUserEmailAndUserPw(loginDTO.getId(), loginDTO.getPasswd());
    }

    public boolean isEmailDuplicated(final String email) {
        System.out.println(email);
        return repository.existsById(email);
    }

    public List<UserVO> findAll() {
        return repository.findAll();
    }

    @Transactional
    public UserVO createUser(UserVO userVO) {
        return repository.save(userVO);
    }

    public UserVO findUserByUserEmail(String userEmail) {
        return repository.findById(userEmail).get();
    }
}
