INSERT INTO tickets (id, title, description, is_open) VALUES (1, 'Error de acceso', 'No permite iniciar sesion', TRUE);
INSERT INTO tickets (id, title, description, is_open) VALUES (2, 'Impresora sin conexion', 'La impresora no responde', TRUE);
INSERT INTO tickets (id, title, description, is_open) VALUES (3, 'Software actualizado', 'Se actualizo la aplicacion', FALSE);
INSERT INTO tickets (id, title, description, is_open) VALUES (4, 'Falla de red', 'Sin conexion en laboratorio', TRUE);
INSERT INTO tickets (id, title, description, is_open) VALUES (5, 'Cambio de teclado', 'Se reemplazo el teclado', FALSE);

ALTER TABLE tickets ALTER COLUMN id RESTART WITH 6;
