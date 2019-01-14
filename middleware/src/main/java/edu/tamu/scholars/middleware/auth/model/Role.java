package edu.tamu.scholars.middleware.auth.model;

import java.util.HashMap;
import java.util.Map;

public enum Role {

    // @formatter:off
    ROLE_SUPER_ADMIN("Super Administrator"),
    ROLE_ADMIN("Administrator"),
    ROLE_USER("User");
    // @formatter:on

    private final String value;

    private static Map<String, Role> map = new HashMap<String, Role>();

    static {
        for (Role role : Role.values()) {
            map.put(role.value, role);
        }
    }

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role withValue(String value) {
        return map.get(value);
    }

}
