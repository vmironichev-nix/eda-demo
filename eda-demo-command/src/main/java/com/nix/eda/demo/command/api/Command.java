package com.nix.eda.demo.command.api;

import java.util.Map;
import java.util.UUID;

public class Command {
    private UUID id;
    private Map<String, Object> attributes = Map.of();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
