package com.example.keycloak.spi;

import java.util.Collections;
import java.util.List;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.authentication.ConfigurableAuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

public class DisableUserOnRegistrationAuthenticatorFactory
        implements AuthenticatorFactory, ConfigurableAuthenticatorFactory {

    public static final String ID = "disable-user-on-registration";

    private static final AuthenticationExecutionModel.Requirement[] REQUIREMENTS =
            new AuthenticationExecutionModel.Requirement[] {
                    AuthenticationExecutionModel.Requirement.REQUIRED,
                    AuthenticationExecutionModel.Requirement.DISABLED
            };

    @Override
    public String getId() { return ID; }

    @Override
    public String getDisplayType() { return "Disable User On Registration"; }

    @Override
    public String getHelpText() {
        return "Disables newly registered local users and marks them pending approval.";
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        return new DisableUserOnRegistrationAuthenticator();
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENTS;
    }

    @Override
    public boolean isConfigurable() { return false; }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return Collections.emptyList();
    }

    @Override
    public String getReferenceCategory() { return null; }

    @Override
    public boolean isUserSetupAllowed() { return false; }

    // ---- ProviderFactory lifecycle hooks (required in your KC version) ----
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
