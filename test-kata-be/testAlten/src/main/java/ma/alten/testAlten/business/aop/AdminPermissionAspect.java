package ma.alten.testAlten.business.aop;

import ma.alten.testAlten.business.exceptions.PermissionDeniedException;
import ma.alten.testAlten.security.service.UserDetailsImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class AdminPermissionAspect {

    @Pointcut("@annotation(ma.alten.testAlten.business.aop.annotation.AdminPermission)")
    public void adminPermissionMethods() {}

    @Before("adminPermissionMethods()")
    public void checkAdminPermission() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetailsImpl) {
                UserDetailsImpl userDetails = (UserDetailsImpl) principal;
                String currentEmail = userDetails.getEmail();

                if (!"admin@admin.com".equals(currentEmail)) {
                    throw new PermissionDeniedException("You don't have permission to perform this action.");
                }
            }
        }
    }
}
