/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import server.pharma_red_v2.security.entity.Method;
import server.pharma_red_v2.security.entity.Role;
import server.pharma_red_v2.security.entity.User;

/**
 *
 * @author maine
 */
public class AuthorizationManager {

    //Loop through user's role method list and checks if there is a method match
    public boolean isUserAuthorized(User user, String methodName) {
        boolean authorized = false;
        // for (Group1 g : user.getGroupList()) {
        for (Role r : user.getRoleList()) {
            for (Method m : r.getMethodList()) {

                if (m.getName().equals(methodName)) {
                    return true;
                }
            }
        }
        // }
        return authorized;
    }
}
