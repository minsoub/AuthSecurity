package kr.co.hist.user.repository;

import kr.co.hist.security.vo.UserDetailsVO;
import kr.co.hist.user.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserVO, String> {

    UserVO findByIdAndPasswd(String userId, String userPw);

    Optional<UserVO> findById(String email);
}
