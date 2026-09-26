INSERT INTO categorie (id, nom, description) VALUES
(1, 'Pc Portable',     'Ordinateurs portables'),
(2, 'Ecran',           'Ecrans et moniteurs'),
(3, 'Accessoire',      'Accessoires divers'),
(4, 'Imprimante',      'Imprimantes et multifonctions'),
(5, 'Serveur',         'Serveurs et baies de stockage'),
(6, 'Reseau',          'Equipements reseau (routeurs, switchs)'),
(7, 'Telephonique',    'Telephones et postes fixes');

INSERT INTO utilisateur (id, prenom, nom, email, password, role) VALUES
(2, 'Fatima Zahra', 'Alami',         'fatima.alami@assetpilot.com',     '$2a$10$ygfDtKNLKFlYQ0pjB.xa7ud5j6Le55BXJg6Jez.7TNL1KEDfIT9tO', 'EMPLOYE'),
(3, 'Mohamed Amine', 'Benjelloun',    'mohamed.benjelloun@assetpilot.com', '$2a$10$5SpFZS9O2vpHWU5u9KR0Z.yekf5xF2jFkQedbY9kptOYjNSYJRqWW', 'EMPLOYE'),
(4, 'Khadija', 'Tazi',              'khadija.tazi@assetpilot.com',      '$2a$10$5Bd9rbueDD5/gNgBVilBzufsuQRJy6adibhi/Uf7qAvtnOD7BRBYi', 'EMPLOYE'),
(5, 'Youssef', 'El Alami',          'youssef.elalami@assetpilot.com',   '$2a$10$Pfq3tIWtX4mEccpHd8LDzuFVTaFFvu4TpPiEZ8sRUWwM17U9fh9ua', 'TECHNICIEN'),
(6, 'Amina', 'Bouazzaoui',          'amina.bouazzaoui@assetpilot.com',  '$2a$10$hBiQT7bYayxBtA0PR2rRSObhmiTHhyMGpI/3QinKCO2LnicLdgzcq', 'TECHNICIEN'),
(7, 'Rachid', 'Ouazzani',          'rachid.ouazzani@assetpilot.com',   '$2a$10$w8F/1NPm2ObWeM4vxgeTkOHFjAqFz0BdYzThXbfLbyiSoDoxXF2n2', 'TECHNICIEN'),
(8, 'Hicham', 'Bennani',            'hicham.bennani@assetpilot.com',     '$2a$10$hBiQT7bYayxBtA0PR2rRSObhmiTHhyMGpI/3QinKCO2LnicLdgzcq', 'TECHNICIEN');

INSERT INTO employe (id, matricule) VALUES
(2, 'EMP-001'),
(3, 'EMP-002'),
(4, 'EMP-003');

INSERT INTO technicien (id, specialite) VALUES
(5, 'RESEAU'),
(6, 'HARDWARE'),
(7, 'SOFTWARE'),
(8, 'HARDWARE');

INSERT INTO equipement (id, numero_serie, modele, marque, date_achat, statut, categorie_id) VALUES
(1, 'SN-DELL-7420-01',   'Latitude 7420',        'Dell',    '2023-02-10', 'AFFECTE',       1),
(2, 'SN-HP-PRO-01',      'ProBook 450 G8',       'HP',      '2023-04-15', 'AFFECTE',       1),
(3, 'SN-LNV-X1C-01',     'ThinkPad X1 Carbon',   'Lenovo',  '2023-06-20', 'EN_PANNE',      1),
(4, 'SN-DELL-5420-02',   'Latitude 5420',        'Dell',    '2022-11-05', 'EN_STOCK',      1),
(5, 'SN-HP-ELITE-01',    'EliteBook 850 G9',     'HP',      '2024-01-12', 'EN_STOCK',      1),
(6, 'SN-APL-MBP16-01',   'MacBook Pro 16',       'Apple',   '2024-03-08', 'EN_PANNE',      1),
(7, 'SN-DELL-U2722-01',  'UltraSharp U2722DE',   'Dell',    '2023-07-22', 'EN_REPARATION', 2),
(8, 'SN-SAM-S32-01',     'S32A600',              'Samsung', '2024-02-18', 'EN_STOCK',      2),
(9, 'SN-LG-27-01',       '27GP850',              'LG',      '2024-05-30', 'EN_STOCK',      2),
(10, 'SN-LOG-MX-01',     'MX Master 3S',         'Logitech','2024-08-14', 'EN_PANNE',      3),
(11, 'SN-LOG-K860-01',   'Ergo K860',            'Logitech','2024-09-05', 'EN_STOCK',      3),
(12, 'SN-HP-M404-02',    'LaserJet Pro M404dn',  'HP',      '2022-08-25', 'EN_REPARATION', 4),
(13, 'SN-CAN-MF445-01',  'imageRUNNER MF445',    'Canon',   '2023-11-10', 'AFFECTE',       4),
(14, 'SN-DELL-R750-01',  'PowerEdge R750',       'Dell',    '2024-04-20', 'AFFECTE',       5),
(15, 'SN-CISCO-9200-01',  'Catalyst 9200',        'Cisco',   '2023-12-03', 'EN_REPARATION', 6),
(16, 'SN-CISCO-4431-01',  'ISR 4431',             'Cisco',   '2024-06-15', 'EN_STOCK',      6),
(17, 'SN-YEO-T48-01',     'T48G',                 'Yealink', '2023-09-12', 'EN_PANNE',      7),
(18, 'SN-YEO-W60-01',     'W60P DECT',            'Yealink', '2024-07-28', 'EN_STOCK',      7);

INSERT INTO affectation (id, date_debut, date_fin, statut, employe_id, equipement_id) VALUES
(1, '2024-09-01', NULL,        'ACTIF',    2, 1),
(2, '2024-09-15', NULL,        'ACTIF',    3, 2),
(3, '2024-10-01', NULL,        'ACTIF',    4, 3),
(4, '2024-10-10', NULL,        'ACTIF',    2, 7),
(5, '2024-11-05', NULL,        'ACTIF',    3, 10),
(6, '2024-11-20', NULL,        'ACTIF',    4, 13),
(7, '2023-10-01', '2024-08-31','RESTITUE', 2, 4),
(8, '2023-11-15', '2024-09-30','RESTITUE', 3, 5),
(9, '2024-01-10', '2024-06-15','RESTITUE', 4, 8),
(10, '2023-09-01', '2024-02-28','RESTITUE', 2, 11);

INSERT INTO incident (id, description, niveau_urgence, statut, date_declaration, date_resolution, rapport_intervention, declare_par_id, traite_par_id, equipement_id) VALUES
(1, '[Equipe: IT] [Etage: 2eme] Ecran clignote et image instable sur moniteur Dell',           'MOYEN',  'EN_COURS', '2026-09-02 09:30:00', NULL,                       NULL,                          2, 5, 7),
(2, '[Equipe: RH] [Etage: 1er] Clavier Logitech ne repond plus, touches bloquees',             'FAIBLE', 'OUVERT',   '2026-09-10 14:00:00', NULL,                       NULL,                          3, NULL, 10),
(3, '[Equipe: IT] [Etage: 3eme] MacBook Pro ne demarre plus, ecran noir au boot',              'ELEVE',  'EN_COURS', '2026-09-05 08:15:00', NULL,                       'Diagnostic carte mere en cours',    4, 6, 6),
(4, '[Equipe: Comptabilite] [Etage: 2eme] Imprimante Canon bourrage papier repetitif',         'MOYEN',  'RESOLU',   '2026-08-20 10:00:00', '2026-08-21 16:00:00',    'Nettoyage rouleaux et remplacement kit', 2, 6, 13),
(5, '[Equipe: IT] [Etage: 4eme] Serveur Dell PowerEdge alertes temperature CPU',               'ELEVE',  'RESOLU',   '2026-07-15 02:00:00', '2026-07-15 06:30:00',    'Nettoyage ventilateurs, pate thermique', 3, 5, 14),
(6, '[Equipe: Marketing] [Etage: 1er] Reseau lent, pertes de paquets sur switch Cisco',       'MOYEN',  'OUVERT',   '2026-09-12 11:45:00', NULL,                       NULL,                          4, NULL, 15),
(7, '[Equipe: IT] [Etage: 3eme] Ecran Samsung scintillement aleatoire',                        'MOYEN',  'RESOLU',   '2026-06-10 09:00:00', '2026-06-11 11:00:00',    'Remplacement cable DisplayPort',      2, 5, 8),
(8, '[Equipe: Direction] [Etage: 5eme] Souris MX Master deconnexion Bluetooth frequente',      'FAIBLE', 'RESOLU',   '2026-05-05 09:00:00', '2026-05-06 10:00:00',    'Reappairage et mise a jour firmware',   3, 6, 10),
(9, '[Equipe: RH] [Etage: 2eme] Telephone Yealink T48G sans tonalite',                         'FAIBLE', 'OUVERT',   '2026-09-14 15:20:00', NULL,                       NULL,                          4, NULL, 17),
(10, '[Equipe: IT] [Etage: 4eme] Switch Cisco 9200 ports down, plus de connectivite',          'ELEVE',  'EN_COURS', '2026-09-08 13:10:00', NULL,                       'Attente module SFP de rechange',      2, 5, 15),
(11, '[Equipe: Comptabilite] [Etage: 1er] HP ProBook surchauffe et arret brutal',              'ELEVE',  'RESOLU',   '2026-03-03 09:00:00', '2026-03-04 10:30:00',    'Remplacement ventirad et pate thermique', 3, 6, 2),
(12, '[Equipe: Marketing] [Etage: 3eme] Lenovo ThinkPad lenteur anormale au demarrage',       'MOYEN',  'OUVERT',   '2026-09-11 17:00:00', NULL,                       NULL,                          4, NULL, 3),
(13, '[Equipe: IT] [Etage: 2eme] Imprimante HP M404 erreur 49.XXXX recursif',                 'MOYEN',  'EN_COURS', '2026-09-15 10:30:00', NULL,                       'Mise a jour firmware en cours',       2, 7, 12),
(14, '[Equipe: Direction] [Etage: 5eme] MacBook Pro 16 batterie ne charge plus',              'ELEVE',  'RESOLU',   '2026-04-20 14:00:00', '2026-04-21 11:00:00',    'Remplacement batterie et connecteur',   3, 6, 6);

UPDATE equipement SET statut = 'EN_STOCK' WHERE id IN (4, 5, 8, 9, 11, 16, 18);