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


INSERT INTO `dyetechnology_db`.`productos` (`activo`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `upc`, `id_categoria`) VALUES (1, '10', 'Sin Descripción, ESTO ES UN PRODUCTO DE PRUEBA', 'cd6b3fb0-3599-45d0-9060-98f368fcac9c.jpg', 'Mouse Gamer Corsair M65 RGB Elite White', '12979', '5673246', '3');
INSERT INTO `dyetechnology_db`.`productos` (`activo`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `upc`, `id_categoria`) VALUES (1, '5', 'Sin Descripción, ESTO ES UN PRODUCTO DE PRUEBA', 'd2cba30e-cef5-4896-9a7f-1bdb393973c4.webp', 'Smart TV Samsung Series 7 LED 4K 50"', '115000', '8946792', null);
INSERT INTO `dyetechnology_db`.`productos` (`activo`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `upc`, `id_categoria`) VALUES (1, '15', 'Sin Descripción, ESTO ES UN PRODUCTO DE PRUEBA', 'acf74b2a-0e81-4a4a-a615-1497fe78e988.webp', 'Teclado Noga Gamer NKB-233', '2500', '57853', '2');
INSERT INTO `dyetechnology_db`.`productos` (`activo`, `cantidad`, `descripcion`, `imagen`, `nombre`, `precio`, `upc`, `id_categoria`) VALUES (1, '25', 'Sin Descripción, ESTO ES UN PRODUCTO DE PRUEBA', '5304f496-c07d-42c9-9c05-51069e6e31b9.webp', 'Parlante Velikka VKK-8810 con Bluetooth', '8000', '871223', null);

