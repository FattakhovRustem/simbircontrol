package com.simbirsoft.simbircontrol.enums;

import com.simbirsoft.simbircontrol.exception.NoEnumException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;

public enum Role {
    ADMIN("ADMIN"),
    LEADER("LEADER"),
    AUTHOR("AUTHOR"),
    PERFORMER("PERFORMER");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    @Converter(autoApply = true)
    public class RoleConverter implements AttributeConverter<Role, String> {
        @Override
        public String convertToDatabaseColumn(Role role) {
            return role.name;
        }

        @Override
        public Role convertToEntityAttribute(String s) {
            return Arrays.stream(Role.values()).filter((p) -> p.name.equalsIgnoreCase(s)).findFirst().orElseThrow(() -> new NoEnumException("Role not found"));
        }
    }
}
