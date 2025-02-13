package ma.alten.testAlten.security.utilities;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Iterator;

public class CurrentUser {
    private static final Logger log = LoggerFactory.getLogger(CurrentUser.class);

    public CurrentUser() {
    }

    public static String getUserName() {
        String userName = null;

        try {
            userName = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (Exception var2) {
            Exception e = var2;
            log.error(e.getMessage());
        }

        return userName;
    }

    public static boolean hasAuthority(String authority) {
        try {
            UsernamePasswordAuthenticationToken authorities = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
            Iterator var2 = authorities.getAuthorities().iterator();

            while(var2.hasNext()) {
                GrantedAuthority aut = (GrantedAuthority)var2.next();
                if (authority.equals(aut.getAuthority())) {
                    return true;
                }
            }
        } catch (Exception var4) {
            Exception e = var4;
            log.error(e.getMessage());
        }

        return false;
    }
}

