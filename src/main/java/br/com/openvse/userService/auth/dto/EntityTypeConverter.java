package br.com.openvse.userService.auth.dto;

import java.beans.PropertyEditorSupport;

public class EntityTypeConverter extends PropertyEditorSupport {

    public void setAsText(final String text) throws IllegalArgumentException{
        setValue(EntityType.getType(text));
    }
}
