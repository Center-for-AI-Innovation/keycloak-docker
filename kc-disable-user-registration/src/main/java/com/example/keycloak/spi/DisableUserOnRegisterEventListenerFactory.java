package com.example.keycloak.spi;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class DisableUserOnRegisterEventListenerFactory
        implements EventListenerProviderFactory {

    public static final String ID = "disable-user-on-register";

    @Override
    public EventListenerProvider create(KeycloakSession session) {
        return new DisableUserOnRegisterEventListener(session);
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void init(Config.Scope config) {
        // no-op
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // no-op
    }

    @Override
    public void close() {
        // no-op
    }
}
