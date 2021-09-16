package kr.co.hist.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.hist.security.exception.InputNotFoundException;
import kr.co.hist.user.vo.UserVO;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws
            AuthenticationException {
        final UsernamePasswordAuthenticationToken authRequest;

        try {
            final UserVO user = new ObjectMapper().readValue(request.getInputStream(), UserVO.class);
            authRequest = new UsernamePasswordAuthenticationToken(user.getId(), user.getPasswd());
        }catch(IOException ex) {
            throw new InputNotFoundException();
        }
        setDetails(request, authRequest);

        //UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(request.getParameter("id"), request.getParameter("passwd"));
        //setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
