package io.samancore.workflow.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public record Transition(
        String id,
        String name,
        State stateFrom,
        State stateTo,
        List<String> transitionRoles,
        Map<String, Objects> data
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Transition that = (Transition) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}