package com.dihaw.entity;

public enum Role {

	ROLE_ADMIN,
	ROLE_USER,
	ROLE_GUEST;
	
    public String value() {
        return name();
    }

    public static Role fromValue(String v) {
        return valueOf(v);
    }

}
