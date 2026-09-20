CREATE TABLE utilisateur
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    prenom   VARCHAR(255) NOT NULL,
    nom      VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(20)  NOT NULL
);

CREATE TABLE employe
(
    id         BIGINT       NOT NULL PRIMARY KEY,
    matricule  VARCHAR(255),
    CONSTRAINT fk_employe_utilisateur FOREIGN KEY (id) REFERENCES utilisateur (id)
);

CREATE TABLE technicien
(
    id          BIGINT       NOT NULL PRIMARY KEY,
    specialite  VARCHAR(255),
    CONSTRAINT fk_technicien_utilisateur FOREIGN KEY (id) REFERENCES utilisateur (id)
);

CREATE TABLE categorie
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom         VARCHAR(255),
    description VARCHAR(255)
);

CREATE TABLE equipement
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_serie   VARCHAR(255),
    modele         VARCHAR(255),
    marque         VARCHAR(255),
    date_achat     DATE,
    statut         VARCHAR(20),
    categorie_id   BIGINT NOT NULL,
    CONSTRAINT fk_equipement_categorie FOREIGN KEY (categorie_id) REFERENCES categorie (id)
);

CREATE TABLE affectation
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_debut     DATE,
    date_fin       DATE,
    statut         VARCHAR(20),
    employe_id     BIGINT NOT NULL,
    equipement_id  BIGINT NOT NULL,
    CONSTRAINT fk_affectation_employe FOREIGN KEY (employe_id) REFERENCES employe (id),
    CONSTRAINT fk_affectation_equipement FOREIGN KEY (equipement_id) REFERENCES equipement (id)
);

CREATE TABLE incident
(
    id                    BIGINT AUTO_INCREMENT PRIMARY KEY,
    description           VARCHAR(255),
    niveau_urgence        VARCHAR(20),
    statut                VARCHAR(20),
    date_declaration      DATETIME,
    date_resolution       DATETIME,
    rapport_intervention  VARCHAR(255),
    declare_par_id        BIGINT NOT NULL,
    traite_par_id         BIGINT,
    equipement_id         BIGINT NOT NULL,
    CONSTRAINT fk_incident_declare_par FOREIGN KEY (declare_par_id) REFERENCES employe (id),
    CONSTRAINT fk_incident_traite_par FOREIGN KEY (traite_par_id) REFERENCES technicien (id),
    CONSTRAINT fk_incident_equipement FOREIGN KEY (equipement_id) REFERENCES equipement (id)
);

INSERT INTO utilisateur (prenom, nom, email, password, role)
VALUES ('Admin', 'IT', 'admin@assetpilot.com',
        '$2a$10$5K3ueoifpzTP2WRCL0DHyOONIdmQEud3OOxPeg7PO6aqLHogOfCES', 'ADMIN');
