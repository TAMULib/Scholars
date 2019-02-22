package edu.tamu.scholars.middleware.auth.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import edu.tamu.scholars.middleware.config.SecurityExpressionConfig;

@ExtendWith(SpringExtension.class)
public class SecurityExpressionConfigTest {

    @Test
    public void testDefaultConstructor() {
        SecurityExpressionConfig securityExpressionConfig = new SecurityExpressionConfig();
        assertNotNull(securityExpressionConfig);
    }

    @Test
    public void testRoleHierarchy() {
        SecurityExpressionConfig securityExpressionConfig = new SecurityExpressionConfig();
        RoleHierarchy roleHierarchy = securityExpressionConfig.roleHierarchy();
        testRoleSuperAdminHierarchy(roleHierarchy);
        testRoleAdminHierarchy(roleHierarchy);
        testRoleUserHierarchy(roleHierarchy);
    }

    private void testRoleSuperAdminHierarchy(RoleHierarchy roleHierarchy) {
        List<GrantedAuthority> assignableAuthorities = new ArrayList<GrantedAuthority>();
        assignableAuthorities.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
        List<? extends GrantedAuthority> reachableAuthorities = new ArrayList<GrantedAuthority>(roleHierarchy.getReachableGrantedAuthorities(assignableAuthorities));
        assertEquals(3, reachableAuthorities.size());
        assertEquals("ROLE_USER", reachableAuthorities.get(0).getAuthority());
        assertEquals("ROLE_ADMIN", reachableAuthorities.get(1).getAuthority());
        assertEquals("ROLE_SUPER_ADMIN", reachableAuthorities.get(2).getAuthority());
    }

    private void testRoleAdminHierarchy(RoleHierarchy roleHierarchy) {
        List<GrantedAuthority> assignableAuthorities = new ArrayList<GrantedAuthority>();
        assignableAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        List<? extends GrantedAuthority> reachableAuthorities = new ArrayList<GrantedAuthority>(roleHierarchy.getReachableGrantedAuthorities(assignableAuthorities));
        assertEquals(2, reachableAuthorities.size());
        assertEquals("ROLE_USER", reachableAuthorities.get(0).getAuthority());
        assertEquals("ROLE_ADMIN", reachableAuthorities.get(1).getAuthority());
    }

    private void testRoleUserHierarchy(RoleHierarchy roleHierarchy) {
        List<GrantedAuthority> assignableAuthorities = new ArrayList<GrantedAuthority>();
        assignableAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        List<? extends GrantedAuthority> reachableAuthorities = new ArrayList<GrantedAuthority>(roleHierarchy.getReachableGrantedAuthorities(assignableAuthorities));
        assertEquals(1, reachableAuthorities.size());
        assertEquals("ROLE_USER", reachableAuthorities.get(0).getAuthority());
    }

}
