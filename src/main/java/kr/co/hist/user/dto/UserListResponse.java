package kr.co.hist.user.dto;

import kr.co.hist.user.vo.UserVO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserListResponse {
    private final List<UserVO> userList;
}
