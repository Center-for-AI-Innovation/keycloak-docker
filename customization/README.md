# Custom Keycloak Image w Provider JAR + Events

This repo builds a custom Keycloak image that includes our provider JAR and (optionally) configures Keycloak for event logging / listeners.

## Prerequisites
- Java (match the provider’s build requirements)
- Maven or Gradle (depending on provider project)
- Docker (with Buildx enabled)


## Build the Provider JAR

```shell
cd kc-disable-user-registration
mvn clean pacakge
```

The JAR should end up under: `kc-disable-user-registration/target/*.jar`

## Dockerfile Overview

We use a multi-stage build:

Stage 1: builder
- Copies provider JAR(s) into Keycloak’s providers directory: `/opt/keycloak/providers/`
- Runs `kc.sh build` so Keycloak can index providers and optimize the server distribution.

Stage 2: runtime
- Copies the built Keycloak distribution from the builder stage into a clean runtime image.

> Notes: If your AWS runtime is x86_64, the simplest approach is to build linux/amd64 (see below).


## Docker Build

Decide your target platform (important for AWS)
- Most AWS clusters (Intel/AMD): use linux/amd64

Build for AWS x86_64 (recommended default)
```shell
docker buildx build --platform linux/amd64 \
  -f Dockerfile.keycloak-custom \
  -t 076942044391.dkr.ecr.us-east-2.amazonaws.com/illinois-chat-keycloak:kc-disable-user \
  --push .
```

## Enable Events in Keycloak Admin Console

Keycloak can record admin events and user events (login, register, logout, etc.).
You can also route events to an Event Listener (built-in or custom provider).

### Enable / Select Event Listeners

Go to Realm settings → Events

Find Event listeners (a multi-select list).
- Add one or more listeners, e.g. your custom listener ID e.g. `disable-user-on-register`, or built-in ones like `jboss-logging` (logs to server log).
- Click Save.

Note: If you added a custom listener provider but don’t see it in the list:
- confirm the JAR is in `/opt/keycloak/providers/`
- confirm `kc.sh build` ran after copying it
- check logs on startup for provider load errors
