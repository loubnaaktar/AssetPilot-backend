

INSERT INTO categorie (id, nom, description) VALUES
(1, 'Pc Portable',     'Ordinateurs portables'),
(2, 'Ecran',           'Ecrans et moniteurs'),
(3, 'Accessoire',      'Accessoires divers'),
(4, 'Imprimante',      'Imprimantes et multifonctions'),
(5, 'Serveur',         'Serveurs et baies de stockage'),
(6, 'Reseau',          'Equipements reseau (routeurs, switchs)'),
(7, 'Telephonique',    'Telephones et postes fixes');

INSERT INTO utilisateur (id, prenom, nom, email, password, role) VALUES
(2, 'Yassine', 'El Amrani',    'yassine.elamrani@assetpilot.com', '$2b$10$BPp4w9lRc4Ovt0g9pZitA.rZbwpbnEdvbEOZwpnPzX1VV.D9YrQki', 'EMPLOYE'),
(3, 'Salma',   'Benali',       'salma.benali@assetpilot.com',     '$2b$10$6zatIc6..VyfYT9xjuF1de1P8d4XRJdhj4/xHyadCtsyoyCaKpsU6', 'EMPLOYE'),
(4, 'Omar',    'Tazi',         'omar.tazi@assetpilot.com',        '$2b$10$SaBIP6SvDkAflNfsoZwJnu0x33lnJKCNui3pZmgztHNA0DridPpLW', 'EMPLOYE'),
(5, 'Imane',   'Chraibi',      'imane.chraibi@assetpilot.com',    '$2b$10$UJPZAVVGdlpUwkMUZ1aiNOF7qti9rmsYXOFhNCXk71a3zf7QOAz9W', 'EMPLOYE'),
(6, 'Karim',   'Hassani',      'karim.hassani@assetpilot.com',    '$2b$10$3.tPWDT0Ppf2frbDptQSL.sbVvhbFaxX9FsI4Xu2BMp13uC4UxyiK', 'TECHNICIEN'),
(7, 'Nadia',   'Bouzid',       'nadia.bouzid@assetpilot.com',     '$2b$10$f1yiP4LMjINv4Ftys3hvuex6cZyuanpe3RGfuw4X40ZdufMCju65y', 'TECHNICIEN'),
(8, 'Mehdi',   'Alaoui',       'mehdi.alaoui@assetpilot.com',     '$2b$10$9AsIymAoo1jncKYHnCzpeO46/M/KMpFnNUtowf.S4LufCklNseMCi', 'TECHNICIEN');

INSERT INTO employe (id, matricule) VALUES
(2, 'EMP-001'),
(3, 'EMP-002'),
(4, 'EMP-003'),
(5, 'EMP-004');

INSERT INTO technicien (id, specialite) VALUES
(6, 'RESEAU'),
(7, 'HARDWARE'),
(8, 'SOFTWARE');

INSERT INTO equipement (id, numero_serie, modele, marque, date_achat, statut, categorie_id) VALUES
(1, 'SN-DELL-7400-01',   'Latitude 7400',        'Dell',    '2023-01-15', 'EN_STOCK',      1),
(2, 'SN-HP-PROBOOK-01',  'ProBook 450 G7',       'HP',      '2023-03-20', 'EN_STOCK',      1),
(3, 'SN-DELL-650-R01',   'PowerEdge R650',        'Dell',    '2025-03-01', 'EN_STOCK',      5),
(4, 'SN-CISCO-2960-01',  'Catalyst 2960',         'Cisco',   '2025-04-10', 'AFFECTE',       6),
(5, 'SN-CISCO-4321-01',  'ISR 4321',              'Cisco',   '2025-05-12', 'EN_STOCK',      6),
(6, 'SN-LNV-TB16-01',    'ThinkBook 16 Gen 6',    'Lenovo',  '2026-01-05', 'AFFECTE',       1),
(7, 'SN-APL-MBP14-01',   'MacBook Pro 14',        'Apple',   '2026-02-01', 'AFFECTE',       1),
(8, 'SN-DELL-5420-01',   'Latitude 5420',         'Dell',    '2023-09-15', 'EN_PANNE',      1),
(9, 'SN-HP-840-01',      'EliteBook 840 G9',      'HP',      '2026-03-10', 'AFFECTE',       1),
(10, 'SN-DELL-U2720-01', 'UltraSharp U2720Q',     'Dell',    '2025-06-20', 'EN_STOCK',      2),
(11, 'SN-SAM-S27-01',    'S27A600',               'Samsung', '2024-08-01', 'EN_STOCK',      2),
(12, 'SN-LG-24-01',      '24MN430',               'LG',      '2025-10-05', 'AFFECTE',       2),
(13, 'SN-LOG-K120-01',   'Clavier K120',          'Logitech','2025-11-11', 'EN_STOCK',      3),
(14, 'SN-LOG-M185-01',   'Souris M185',           'Logitech','2026-06-01', 'AFFECTE',       3),
(15, 'SN-HP-M404-01',    'LaserJet Pro M404dn',   'HP',      '2022-03-30', 'EN_PANNE',      4),
(16, 'SN-CAN-MF3010-01', 'MF3010',                'Canon',   '2024-01-15', 'EN_STOCK',      4),
(17, 'SN-NET-GS116-01',  'GS116',                 'Netgear', '2025-02-25', 'EN_REPARATION', 6),
(18, 'SN-PHI-243V7-01',  '243V7',                 'Philips', '2021-07-07', 'HORS_SERVICE',  2);

INSERT INTO affectation (id, date_debut, date_fin, statut, employe_id, equipement_id) VALUES
(1, '2026-01-05', NULL,        'ACTIF',    2, 6),
(2, '2026-02-01', NULL,        'ACTIF',    3, 7),
(3, '2026-03-10', NULL,        'ACTIF',    4, 9),
(4, '2026-04-02', NULL,        'ACTIF',    5, 4),
(5, '2026-05-20', NULL,        'ACTIF',    2, 12),
(6, '2026-06-01', NULL,        'ACTIF',    3, 14),
(7, '2024-05-15', '2025-12-20','RESTITUE', 4, 1),
(8, '2026-01-10', '2026-08-30','RESTITUE', 5, 2),
(9, '2025-11-01', '2026-06-15','RESTITUE', 5, 11),
(10, '2025-09-01', '2026-05-25','RESTITUE', 2, 16);

INSERT INTO incident (id, description, niveau_urgence, statut, date_declaration, date_resolution, rapport_intervention, declare_par_id, traite_par_id, equipement_id) VALUES
(1, 'Fissure sur legerite de l''ecran',              'MOYEN',  'EN_COURS', '2026-09-02 09:30:00', NULL,                       NULL,                          2, 8, 12),
(2, 'Clavier ne repond plus aux frappes',            'FAIBLE', 'OUVERT',   '2026-09-10 14:00:00', NULL,                       NULL,                          3, NULL, 13),
(3, 'Le laptop ne demarre plus, led clignote',       'ELEVE',  'EN_COURS', '2026-09-05 08:15:00', NULL,                       'Diagnostic en cours...',       4, 7, 8),
(4, 'Imprimante bloque les feuilles a chaque tirage','MOYEN',  'RESOLU',   '2026-08-20 10:00:00', '2026-08-21 16:00:00',    'Remplacement du rouleau d''entrainement', 5, 7, 15),
(5, 'Serveur en surcharge, alertes cpu elevees',     'ELEVE',  'RESOLU',   '2026-07-15 02:00:00', '2026-07-15 06:30:00',    'Optimisation des index et nettoyage des logs', 2, 6, 3),
(6, 'Reseau tres lent sur tout l''etage',            'MOYEN',  'OUVERT',   '2026-09-12 11:45:00', NULL,                       NULL,                          3, NULL, 4),
(7, 'Ecran devient noir de facon soudaine',          'MOYEN',  'RESOLU',   '2026-06-10 09:00:00', '2026-06-11 11:00:00',    'Remplacement de la carte d''alimentation', 4, 8, 11),
(8, 'Batterie se decharge extremement vite',         'FAIBLE', 'RESOLU',   '2026-05-05 09:00:00', '2026-05-06 10:00:00',    'Recalibrage de la batterie',   2, 6, 6),
(9, 'Souris detectee par intermittence',             'FAIBLE', 'OUVERT',   '2026-09-14 15:20:00', NULL,                       NULL,                          3, NULL, 14),
(10, 'Switch en panne, plus de connectivite',        'ELEVE',  'EN_COURS', '2026-09-08 13:10:00', NULL,                       'En attente de la piece de rechange', 5, 6, 17),
(11, 'Taches et liseres sur l''affichage',           'MOYEN',  'RESOLU',   '2026-03-03 09:00:00', '2026-03-04 10:30:00',    'Materiel declare hors service', 4, 8, 18),
(12, 'MacBook anormalement lent au demarrage',       'MOYEN',  'OUVERT',   '2026-09-11 17:00:00', NULL,                       NULL,                          3, NULL, 7);

UPDATE equipement SET statut = 'EN_STOCK' WHERE id = 1;