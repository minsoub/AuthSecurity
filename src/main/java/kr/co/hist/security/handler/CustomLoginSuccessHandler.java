package kr.co.hist.security.handler;

import kr.co.hist.security.vo.UserDetailsVO;
import kr.co.hist.user.vo.UserVO;
import kr.co.hist.util.TokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        final UserVO user = ((UserDetailsVO) authentication.getPrincipal()).getUserVO();
        final String token = TokenUtils.generateJwtToken(user);

        response.addHeader("Authorization", "BEARER" + " " + token);

        //SecurityContextHolder.getContext().setAuthentication(authentication);
        //response.sendRedirect("/about");
    }
}
