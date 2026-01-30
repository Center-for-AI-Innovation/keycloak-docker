package com.example.keycloak.spi;

import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.KeycloakSession;

public class DisableUserOnRegistrationAuthenticatorFactory
        implements AuthenticatorFactory {

    public static final String ID = "disable-user-on-registration";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        return new DisableUserOnRegistrationAuthenticator();
    }

    @Override
    public String getDisplayType() {
        return "Disable User On Registration";
    }

    @Override
    public String getHelpText() {
        return "Disables newly registered local users and marks them pending approval.";
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public boolean isEnabled(KeycloakSession session,
                             org.keycloak.models.RealmModel realm) {
        return true;
    }
}
