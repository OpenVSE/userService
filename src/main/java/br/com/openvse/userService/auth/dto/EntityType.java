package br.com.openvse.userService.auth.dto;


import com.fasterxml.jackson.annotation.JsonValue;
import java.util.stream.Stream;

public enum EntityType {

    STUDENT("student"),
    PROFESSOR("professor"),
    ADMIN("admin");

    private final String type;

    EntityType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    public static EntityType getType(String type){
        return Stream.of(EntityType.values()).filter(e -> e.getType().equals(type)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

}
