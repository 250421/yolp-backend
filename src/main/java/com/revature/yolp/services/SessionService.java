package com.revature.yolp.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.revature.yolp.dtos.responses.UserSessionDTO;
import com.revature.yolp.models.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionService {

    private static final String USER_SESSION_KEY = "USER_SESSION";

    /**
     * Creates a new session for the authenticated user
     * 
     * @param user The authenticated user
     * @return The UserSessionDTO with limited user information
     */
    public UserSessionDTO createSession(User user) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession(true);

        UserSessionDTO userSession = new UserSessionDTO(user);
        session.setAttribute(USER_SESSION_KEY, userSession);

        return userSession;
    }

    /**
     * Gets the current user session
     * 
     * @return The UserSessionDTO or null if no session exists
     */
    public UserSessionDTO getCurrentSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }

        return (UserSessionDTO) session.getAttribute(USER_SESSION_KEY);
    }

    /**
     * Invalidates the current session
     */
    public void invalidateSession() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }
}