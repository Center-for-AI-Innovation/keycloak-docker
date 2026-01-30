package com.example.keycloak.spi;

import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticationFlowContext;
import org.keycloak.models.UserModel;

public class DisableUserOnRegistrationAuthenticator implements Authenticator {

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        UserModel user = context.getUser();

        if (user != null) {
            user.setEnabled(false);
            user.setSingleAttribute("approval_status", "pending");
        }

        context.success();
    }

    @Override public void action(AuthenticationFlowContext context) {}
    @Override public boolean requiresUser() { return false; }
    @Override public boolean configuredFor(org.keycloak.models.KeycloakSession s,
                                           org.keycloak.models.RealmModel r,
                                           UserModel u) { return true; }
    @Override public void setRequiredActions(AuthenticationFlowContext context) {}
    @Override public void close() {}
}
