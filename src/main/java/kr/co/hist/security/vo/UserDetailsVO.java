package kr.co.hist.security.vo;

import kr.co.hist.user.vo.UserVO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
@Getter
public class UserDetailsVO implements UserDetails {

    @Delegate
    private final UserVO userVO;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userVO.getPasswd();
    }

    @Override
    public String getUsername() {
        return userVO.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userVO.isEnable();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userVO.isEnable();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userVO.isEnable();
    }

    @Override
    public boolean isEnabled() {
        return userVO.isEnable();
    }
}
