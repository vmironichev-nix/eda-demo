package com.nix.eda.demo.command.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface EventsRepository extends CrudRepository<com.nix.eda.demo.command.dao.Event, UUID> {
}
