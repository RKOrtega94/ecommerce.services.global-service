CREATE TABLE cities
(
    id       UUID         NOT NULL default gen_random_uuid(),
    name     VARCHAR(100) NOT NULL,
    state_id UUID         NOT NULL,
    CONSTRAINT pk_cities PRIMARY KEY (id)
);

CREATE TABLE countries
(
    id              UUID NOT NULL default gen_random_uuid(),
    name            VARCHAR(255),
    code            VARCHAR(255),
    phone_code      VARCHAR(255),
    currency        VARCHAR(255),
    currency_symbol VARCHAR(255),
    currency_code   VARCHAR(255),
    flag            VARCHAR(255),
    CONSTRAINT pk_countries PRIMARY KEY (id)
);

-- Countries unique index
CREATE UNIQUE INDEX uq_countries ON countries (name, code)
    where status = 'ACTIVE' and deleted_at is null;

CREATE TABLE states
(
    id         UUID         NOT NULL default gen_random_uuid(),
    name       VARCHAR(100) NOT NULL,
    country_id UUID         NOT NULL,
    CONSTRAINT pk_states PRIMARY KEY (id)
);

CREATE TABLE taxes
(
    id         UUID             NOT NULL,
    name       VARCHAR(100)     NOT NULL,
    percentage DOUBLE PRECISION NOT NULL,
    country_id UUID             NOT NULL,
    CONSTRAINT pk_taxes PRIMARY KEY (id)
);

ALTER TABLE cities
    ADD CONSTRAINT FK_CITIES_ON_STATE FOREIGN KEY (state_id) REFERENCES states (id);

ALTER TABLE states
    ADD CONSTRAINT FK_STATES_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES countries (id);

ALTER TABLE taxes
    ADD CONSTRAINT FK_TAXES_ON_COUNTRY FOREIGN KEY (country_id) REFERENCES countries (id);