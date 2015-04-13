package com.dihaw.entity;

public enum UserStatus {

	Enabled,
	Disabled;
	
    public String value() {
        return name();
    }

    public static UserStatus fromValue(String v) {
        return valueOf(v);
    }

}
