package kr.co.hist.user.controller;

import kr.co.hist.user.dto.UserListResponse;
import kr.co.hist.user.service.UserService;
import kr.co.hist.user.vo.LoginDTO;
import kr.co.hist.user.vo.UserVO;
import kr.co.hist.util.TokenUtils;
import kr.co.hist.util.UserRole;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value="/user")
@Log4j2
public class UserController {
    private final UserService userService;

    @NonNull
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value="/loginView")
    public String loginView() {
        return "user/login";
    }

    @PostMapping(value="/signup")
    public ResponseEntity<String> signUp(@RequestBody LoginDTO loginDTO) {

        log.debug("signup called.....");
        log.debug(loginDTO.toString());
        boolean result = userService.isEmailDuplicated(loginDTO.getId());
        System.out.println(result);

        return !userService.isEmailDuplicated(loginDTO.getId())
                ? ResponseEntity.badRequest().build()
                : ResponseEntity.ok(TokenUtils.generateJwtToken(userService.signUp(loginDTO)));
    }
    @GetMapping(value="/list")
    public ResponseEntity<UserListResponse> findAll() {
        final UserListResponse list = UserListResponse.builder()
                .userList(userService.findAll()).build();

        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/init")
    public ResponseEntity createAdmin() {
        UserVO userVO = UserVO.builder()
                        .id("minsoub@gmail.com")
                        .passwd(passwordEncoder.encode("1234"))
                        .role(UserRole.ROLE_ADMIN)
                        .isEnable(true)
                        .build();

        return ResponseEntity.ok(userService.createUser(userVO));
    }
}
