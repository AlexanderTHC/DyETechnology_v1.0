/*roles y usuarios de prueba*/
INSERT INTO `dyetechnology_db`.`roles` (`activo`, `nombre`) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `dyetechnology_db`.`roles` (`activo`, `nombre`) VALUES (1, 'ROLE_CAJERO');
INSERT INTO `dyetechnology_db`.`roles` (`activo`, `nombre`) VALUES (1, 'ROLE_USER');

INSERT INTO `usuarios` (`activo`, `apellido`, `clave`, `direccion`, `dni`, `email`, `nombre`, `telefono`, `username`, `id_rol`) VALUES (1, 'Sosa', '$2a$10$sPhZgmkVpsgJxEjUf4QOFOTbwD/zmDXNICxpkCIwKnXPA1DeOSCg2', NULL, NULL, 'sosaalexander.joa@gmail.com', 'Alexander', NULL, 'zax', '1');
INSERT INTO `usuarios` (`activo`, `apellido`, `clave`, `direccion`, `dni`, `email`, `nombre`, `telefono`, `username`, `id_rol`) VALUES (1, 'Gualtieri', '$2a$10$sPhZgmkVpsgJxEjUf4QOFOTbwD/zmDXNICxpkCIwKnXPA1DeOSCg2', NULL, NULL, 'elian.gualtieri@gmail.com', 'Elian', NULL, 'elian', '1');


INSERT INTO `dyetechnology_db`.`categorias` (`activo`, `nombre`) VALUES (1, 'Auriculares');
INSERT INTO `dyetechnology_db`.`categorias` (`activo`, `nombre`) VALUES (1, 'Teclados');
INSERT INTO `dyetechnology_db`.`categorias` (`activo`, `nombre`) VALUES (1, 'Mouse');

INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'auricular 1', '1');
INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'auricular 2', '1');

INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'teclado 1', '2');
INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'teclado 2', '2');
INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'teclado 3', '2');
INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'teclado 4', '2');
INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'teclado 5', '2');

INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'mouse 1', '3');
INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'mouse 2', '3');
INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'mouse 3', '3');
INSERT INTO `dyetechnology_db`.`subcategorias` (`activo`, `nombre`, `id_categoria`) VALUES (1, 'mouse 4', '3');


INSERT INTO `dyetechnology_db`.`productos` (`activo`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `upc`, `id_categoria`) VALUES (1, '10', 'Sin Descripción, ESTO ES UN PRODUCTO DE PRUEBA', 'D_NQ_NP_961245-MLA41816931479_052020-O.webp', 'Mouse Gamer Corsair M65 RGB Elite White', '12979', '5673246', '3');
INSERT INTO `dyetechnology_db`.`productos` (`activo`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `upc`, `id_categoria`) VALUES (1, '5', 'Sin Descripción, ESTO ES UN PRODUCTO DE PRUEBA', 'D_NQ_NP_787221-MLA48007684849_102021-O.webp', 'Smart TV Samsung Series 7 LED 4K 50"', '115000', '8946792', null);
INSERT INTO `dyetechnology_db`.`productos` (`activo`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `upc`, `id_categoria`) VALUES (1, '15', 'Sin Descripción, ESTO ES UN PRODUCTO DE PRUEBA', 'nkb-233-teclado11-897562ccbd6936895316299985198497-640-0.jpg', 'Teclado Noga Gamer NKB-233', '2500', '57853', '2');
INSERT INTO `dyetechnology_db`.`productos` (`activo`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `upc`, `id_categoria`) VALUES (1, '25', 'Sin Descripción, ESTO ES UN PRODUCTO DE PRUEBA', 'D_NQ_NP_973536-MLA50480783173_062022-O.webp', 'Parlante Velikka VKK-8810 con Bluetooth', '8000', '871223', null);

