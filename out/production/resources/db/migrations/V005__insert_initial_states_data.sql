-- Insert all 24 provinces of Ecuador
INSERT INTO states (name, country_id)
SELECT name, (SELECT id FROM countries WHERE code = 'EC')
FROM (VALUES ('Azuay'),
             ('Bolívar'),
             ('Cañar'),
             ('Carchi'),
             ('Chimborazo'),
             ('Cotopaxi'),
             ('El Oro'),
             ('Esmeraldas'),
             ('Galápagos'),
             ('Guayas'),
             ('Imbabura'),
             ('Loja'),
             ('Los Ríos'),
             ('Manabí'),
             ('Morona Santiago'),
             ('Napo'),
             ('Orellana'),
             ('Pastaza'),
             ('Pichincha'),
             ('Santa Elena'),
             ('Santo Domingo de los Tsáchilas'),
             ('Sucumbíos'),
             ('Tungurahua'),
             ('Zamora Chinchipe')) AS provinces(name)
ORDER BY name;