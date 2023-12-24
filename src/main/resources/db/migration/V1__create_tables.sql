CREATE TABLE animals (
  id UUID NOT NULL,
   type VARCHAR(255),
   sex VARCHAR(255),
   age VARCHAR(255),
   nick_or_number VARCHAR(255),
   breed VARCHAR(255),
   owner VARCHAR(255),
   avatar VARCHAR(255),
   CONSTRAINT pk_animals PRIMARY KEY (id)
);

CREATE TABLE sicks (
  id UUID NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_sicks PRIMARY KEY (id)
);

CREATE TABLE books (
  id UUID NOT NULL,
   title VARCHAR(255),
   type VARCHAR(255),
   author VARCHAR(255),
   publish_year VARCHAR(255),
   avatar VARCHAR(255),
   file VARCHAR(255),
   CONSTRAINT pk_books PRIMARY KEY (id)
);

CREATE TABLE roles (
  id UUID NOT NULL,
   name VARCHAR(255),
   name_localization VARCHAR(255),
   CONSTRAINT pk_roles PRIMARY KEY (id)
);

CREATE TABLE users (
  id UUID NOT NULL,
   fio VARCHAR(255),
   birthday TIMESTAMP WITHOUT TIME ZONE,
   phone VARCHAR(255),
   email VARCHAR(255),
   password VARCHAR(255),
   avatar VARCHAR(255),
   activated BOOLEAN NOT NULL,
   role_id UUID,
   CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

CREATE TABLE inspects (
  id UUID NOT NULL,
   plan_date TIMESTAMP WITHOUT TIME ZONE,
   inspect_status VARCHAR(255),
   animal_id UUID,
   CONSTRAINT pk_inspects PRIMARY KEY (id)
);

ALTER TABLE inspects ADD CONSTRAINT FK_INSPECTS_ON_ANIMAL FOREIGN KEY (animal_id) REFERENCES animals (id);

CREATE TABLE diseases (
  id UUID NOT NULL,
   date_start TIMESTAMP WITHOUT TIME ZONE,
   date_end TIMESTAMP WITHOUT TIME ZONE,
   date_to_therapy TIMESTAMP WITHOUT TIME ZONE,
   first_diagnosis VARCHAR(6000),
   second_diagnosis VARCHAR(6000),
   anamnesis VARCHAR(6000),
   temperature FLOAT NOT NULL,
   pulse INTEGER NOT NULL,
   breath INTEGER NOT NULL,
   common_health VARCHAR(6000),
   fatness VARCHAR(6000),
   external_skin_status VARCHAR(6000),
   internal_shell_status VARCHAR(6000),
   lymph_status VARCHAR(6000),
   gastro_system_research VARCHAR(6000),
   breath_system_research VARCHAR(6000),
   heart_system_research VARCHAR(6000),
   nervous_system_research VARCHAR(6000),
   urogenital_system_research VARCHAR(6000),
   sick_id UUID,
   curator_id UUID,
   animal_id UUID,
   CONSTRAINT pk_diseases PRIMARY KEY (id)
);

ALTER TABLE diseases ADD CONSTRAINT FK_DISEASES_ON_ANIMAL FOREIGN KEY (animal_id) REFERENCES animals (id);

ALTER TABLE diseases ADD CONSTRAINT FK_DISEASES_ON_CURATOR FOREIGN KEY (curator_id) REFERENCES users (id);

ALTER TABLE diseases ADD CONSTRAINT FK_DISEASES_ON_SICK FOREIGN KEY (sick_id) REFERENCES sicks (id);