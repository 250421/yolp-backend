package com.revature.yolp.utils;

import org.springframework.stereotype.Component;

import com.revature.yolp.dtos.responses.UserSessionDTO;
import com.revature.yolp.services.SessionService;
import com.revature.yolp.utils.custom_exceptions.UnauthorizedException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {

    private final SessionService sessionService;

    /**
     * Gets the current authenticated user from the session
     * 
     * @return The UserSessionDTO containing user info
     * @throws UnauthorizedException if no user is authenticated
     */
    public UserSessionDTO getCurrentUser() {
        UserSessionDTO user = sessionService.getCurrentSession();
        if (user == null) {
            throw new UnauthorizedException("You must be logged in to perform this action");
        }
        return user;
    }

    /**
     * Checks if the current user has the specified role
     * 
     * @param role The role to check
     * @return true if the user has the role, false otherwise
     */
    public boolean hasRole(String role) {
        try {
            UserSessionDTO user = getCurrentUser();
            return user.getRole().name().equals(role);
        } catch (UnauthorizedException e) {
            return false;
        }
    }
}