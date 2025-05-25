package io.samancore.workflow.model;

import java.util.List;
import java.util.Objects;

public record State(
        String id,
        String name,
        StateCategoryType category,
        Boolean isInitial,
        List<String> stateRoles
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Objects.equals(id, state.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}