CREATE TABLE categories
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100)                 NOT NULL,
    last_name  VARCHAR(100)                 NOT NULL,
    email      VARCHAR(150)                 NOT NULL UNIQUE,
    password   VARCHAR(255)                 NOT NULL,
    role       ENUM ('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_TECHNICIAN') NOT NULL,
    created_at TIMESTAMP                    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP                    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE assets
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    serial_number VARCHAR(100) NOT NULL UNIQUE,
    model         VARCHAR(100) NOT NULL,
    brand         VARCHAR(100) NOT NULL,
    category_id   BIGINT       NOT NULL,
    purchase_date DATE         NULL,
    status        ENUM ('EN_STOCK','ASSIGNED','OUT_OF_ORDER','IN_REPAIR','DISPOSED') NOT NULL DEFAULT 'EN_STOCK',
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_assets_category FOREIGN KEY (category_id) REFERENCES categories (id)
);

CREATE TABLE assignments
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    asset_id    BIGINT    NOT NULL UNIQUE,
    employee_id BIGINT    NOT NULL,
    assigned_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    released_at TIMESTAMP NULL,
    CONSTRAINT fk_assignments_asset FOREIGN KEY (asset_id) REFERENCES assets (id),
    CONSTRAINT fk_assignments_employee FOREIGN KEY (employee_id) REFERENCES users (id)
);

CREATE TABLE incidents
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    asset_id      BIGINT  NOT NULL,
    reported_by   BIGINT  NOT NULL,
    technician_id BIGINT  NULL,
    description   TEXT    NOT NULL,
    urgency       ENUM ('LOW','MEDIUM','HIGH') NOT NULL DEFAULT 'MEDIUM',
    status        ENUM ('PENDING','ASSIGNED','IN_PROGRESS','RESOLVED','CLOSED') NOT NULL DEFAULT 'PENDING',
    report        TEXT    NULL,
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_incidents_asset FOREIGN KEY (asset_id) REFERENCES assets (id),
    CONSTRAINT fk_incidents_reported_by FOREIGN KEY (reported_by) REFERENCES users (id),
    CONSTRAINT fk_incidents_technician FOREIGN KEY (technician_id) REFERENCES users (id)
);

-- ========== DONNEES DE DEPART ==========
INSERT INTO categories (name)
VALUES ('Pc Portable'),
       ('Écran'),
       ('Accessoire');

-- Compte admin par defaut : admin@assetpilot.com / password
INSERT INTO users (first_name, last_name, email, password, role)
VALUES ('Admin', 'IT', 'admin@assetpilot.com',
        '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2', 'ROLE_ADMIN');