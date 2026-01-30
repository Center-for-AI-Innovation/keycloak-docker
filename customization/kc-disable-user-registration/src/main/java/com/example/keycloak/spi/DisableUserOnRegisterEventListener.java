package com.example.keycloak.spi;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

public class DisableUserOnRegisterEventListener implements EventListenerProvider {

    private final KeycloakSession session;

    public DisableUserOnRegisterEventListener(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void onEvent(Event event) {
        if (event == null || event.getType() != EventType.REGISTER) {
            return;
        }

        RealmModel realm = session.realms().getRealm(event.getRealmId());
        if (realm == null) return;

        UserModel user = session.users().getUserById(realm, event.getUserId());
        if (user == null) return;

        user.setEnabled(false);
        user.setSingleAttribute("approval_status", "pending");
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        // no-op: we only care about user self-registration events
    }

    @Override
    public void close() {
        // no-op
    }
}
