/*roles y usuarios de prueba*/
INSERT INTO `dyetechnology_db`.`roles` (`activo`, `nombre`) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `dyetechnology_db`.`roles` (`activo`, `nombre`) VALUES (1, 'ROLE_CAJERO');
INSERT INTO `dyetechnology_db`.`roles` (`activo`, `nombre`) VALUES (1, 'ROLE_USER');


INSERT INTO `dyetechnology_db`.`usuarios` (`activo`, `apellido`, `clave`, `direccion`, `dni`, `email`, `nombre`, `telefono`, `username`, `id_rol`) VALUES (1, 'sosa', '$10$XuZ6w5vCrWPYRrcZxt3NKOFOP4IY0vE1d5dmbbDgkJt1m3NL5ptC6', 'yatay', '42986303', 'sosaalexander.joamail.com', 'alexander', '3625153078', 'zaxthc', '1');
