package com.simbirsoft.simbircontrol.enums;

import com.simbirsoft.simbircontrol.exception.NoEnumException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.ResourceBundle;

public enum State {
    BACKLOG("BACKLOG"),
    IN_PROGRESS("IN_PROGRESS"),
    DONE("DONE");

    private final String name;

    State(String name) {
        this.name = name;
    }
/*
    @Converter(autoApply = true)
    public class StateConverter implements AttributeConverter<State, String> {
        @Override
        public String convertToDatabaseColumn(State state) {
            return state.name;
        }

        @Override
        public State convertToEntityAttribute(String s) {
            return Arrays.stream(State.values()).filter((p) -> p.name.equalsIgnoreCase(s)).findFirst().orElseThrow(() -> new NoEnumException(String.format(ResourceBundle.getBundle("resource").getString("stateNotFound"), s)));
        }
    }

 */
}
