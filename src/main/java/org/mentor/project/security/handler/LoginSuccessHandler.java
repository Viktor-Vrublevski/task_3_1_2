package org.mentor.project.security.handler;

import org.mentor.project.model.Role;
import org.mentor.project.model.User;
import org.mentor.project.service.RoleService;
import org.mentor.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication auth) throws IOException, ServletException {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        if (user.getRoles().size() > 1) {
            response.sendRedirect("/admin/users");
        } else {
            response.sendRedirect("/user");
        }

    }
}
