CREATE TABLE cities
(
    id         UUID                        NOT NULL,
    status     VARCHAR(255)                NOT NULL,
    created_by VARCHAR(255)                NOT NULL,
    updated_by VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(100)                NOT NULL,
    state_id   UUID                        NOT NULL,
    CONSTRAINT pk_cities PRIMARY KEY (id)
);

CREATE TABLE countries
(
    id              UUID                        NOT NULL,
    status          VARCHAR(255)                NOT NULL,
    created_by      VARCHAR(255)                NOT NULL,
    updated_by      VARCHAR(255)                NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    deleted_at      TIMESTAMP WITHOUT TIME ZONE,
    name            VARCHAR(255),
    code            VARCHAR(255),
    phone_code      VARCHAR(255),
    currency        VARCHAR(255),
    currency_symbol VARCHAR(255),
    currency_code   VARCHAR(255),
    CONSTRAINT pk_countries PRIMARY KEY (id)
);

CREATE TABLE states
(
    id         UUID                        NOT NULL,
    status     VARCHAR(255)                NOT NULL,
    created_by VARCHAR(255)                NOT NULL,
    updated_by VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(100)                NOT NULL,
    country_id UUID                        NOT NULL,
    CONSTRAINT pk_states PRIMARY KEY (id)
);

CREATE TABLE taxes
(
    id         UUID                        NOT NULL,
    status     VARCHAR(255)                NOT NULL,
    created_by VARCHAR(255)                NOT NULL,
    updated_by VARCHAR(255)                NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    deleted_at TIMESTAMP WITHOUT TIME ZONE,
    name       VARCHAR(100)                NOT NULL,
    percentage DOUBLE PRECISION            NOT NULL,
    country_id UUID                        NOT NULL,
    CONSTRAINT pk_taxes PRIMARY KEY (id)
);

ALTER TABLE cities
    ADD CONSTRAINT FK_CITIES_ON_STATE FOREIGN KEY (state_id) REFERENCES states (id);

ALTER TABLE states
    ADD CONSTRAINT FK_STATES_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES countries (id);

ALTER TABLE taxes
    ADD CONSTRAINT FK_TAXES_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES countries (id);