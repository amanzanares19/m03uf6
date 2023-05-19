-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 19-05-2023 a las 18:48:31
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `calendar-restore`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `getColorById` (IN `searchId` INT)   BEGIN
	SELECT * 
 	FROM colors
	WHERE id = searchId;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getColors` (IN `searchName` VARCHAR(255))   BEGIN
	SELECT * 
 	FROM colors
	WHERE name like searchName;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getLastId` ()   BEGIN
	SELECT id, name, red, green, blue FROM colors ORDER BY ID DESC LIMIT 1;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `colors`
--

CREATE TABLE `colors` (
  `id` bigint(20) NOT NULL,
  `name` varchar(250) NOT NULL,
  `red` int(10) UNSIGNED NOT NULL,
  `green` int(10) UNSIGNED NOT NULL,
  `blue` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `colors`
--

INSERT INTO `colors` (`id`, `name`, `red`, `green`, `blue`) VALUES
(1, 'IndianRed', 205, 92, 92),
(2, 'LightCoral', 240, 128, 128),
(3, 'Salmon', 250, 128, 114),
(4, 'DarkSalmon', 233, 150, 122),
(5, 'LightSalmon', 255, 160, 122),
(6, 'Crimson', 220, 20, 60),
(7, 'Red', 255, 0, 0),
(8, 'FireBrick', 178, 34, 34),
(9, 'DarkRed', 139, 0, 0),
(10, 'Pink', 255, 192, 203),
(11, 'LightPink', 255, 182, 193),
(12, 'HotPink', 255, 105, 180),
(13, 'DeepPink', 255, 20, 147),
(14, 'MediumVioletRed', 199, 21, 133),
(15, 'PaleVioletRed', 219, 112, 147),
(16, 'LightSalmon', 255, 160, 122),
(17, 'Coral', 255, 127, 80),
(18, 'Tomato', 255, 99, 71),
(19, 'OrangeRed', 255, 69, 0),
(20, 'DarkOrange', 255, 140, 0),
(21, 'Orange', 255, 165, 0),
(22, 'Gold', 255, 215, 0),
(23, 'Yellow', 255, 255, 0),
(24, 'LightYellow', 255, 255, 224),
(25, 'LemonChiffon', 255, 250, 205),
(26, 'LightGoldenrodYellow', 250, 250, 210),
(27, 'PapayaWhip', 255, 239, 213),
(28, 'Moccasin', 255, 228, 181),
(29, 'PeachPuff', 255, 218, 185),
(30, 'PaleGoldenrod', 238, 232, 170),
(31, 'Khaki', 240, 230, 140),
(32, 'DarkKhaki', 189, 183, 107),
(33, 'Lavender', 230, 230, 250),
(34, 'Thistle', 216, 191, 216),
(35, 'Plum', 221, 160, 221),
(36, 'Violet', 238, 130, 238),
(37, 'Orchid', 218, 112, 214),
(38, 'Fuchsia', 255, 0, 255),
(39, 'Magenta', 255, 0, 255),
(40, 'MediumOrchid', 186, 85, 211),
(41, 'MediumPurple', 147, 112, 219),
(42, 'BlueViolet', 138, 43, 226),
(43, 'DarkViolet', 148, 0, 211),
(44, 'DarkOrchid', 153, 50, 204),
(45, 'DarkMagenta', 139, 0, 139),
(46, 'Purple', 128, 0, 128),
(47, 'Indigo', 75, 0, 130),
(48, 'SlateBlue', 106, 90, 205),
(49, 'DarkSlateBlue', 72, 61, 139),
(50, 'GreenYellow', 173, 255, 47),
(51, 'Chartreuse', 127, 255, 0),
(52, 'LawnGreen', 124, 252, 0),
(53, 'Lime', 0, 255, 0),
(54, 'LimeGreen', 50, 205, 50),
(55, 'PaleGreen', 152, 251, 152),
(56, 'LightGreen', 144, 238, 144),
(57, 'MediumSpringGreen', 0, 250, 154),
(58, 'SpringGreen', 0, 255, 127),
(59, 'MediumSeaGreen', 60, 179, 113),
(60, 'SeaGreen', 46, 139, 87),
(61, 'ForestGreen', 34, 139, 34),
(62, 'Green', 0, 128, 0),
(63, 'DarkGreen', 0, 100, 0),
(64, 'YellowGreen', 154, 205, 50),
(65, 'OliveDrab', 107, 142, 35),
(66, 'Olive', 128, 128, 0),
(67, 'MediumAquamarine', 102, 205, 170),
(68, 'DarkSeaGreen', 143, 188, 143),
(69, 'LightSeaGreen', 32, 178, 170),
(70, 'DarkCyan', 0, 139, 139),
(71, 'Teal', 0, 128, 128),
(72, 'Aqua', 0, 255, 255),
(73, 'Cyan', 0, 255, 255),
(74, 'LightCyan', 224, 255, 255),
(75, 'PaleTurquoise', 175, 238, 238),
(76, 'Aquamarine', 127, 255, 212),
(77, 'Turquoise', 64, 224, 208),
(78, 'MediumTurquoise', 72, 209, 204),
(79, 'DarkTurquoise', 0, 206, 209),
(80, 'CadetBlue', 95, 158, 160),
(81, 'SteelBlue', 70, 130, 180),
(82, 'LightSteelBlue', 176, 196, 222),
(83, 'PowderBlue', 176, 224, 230),
(84, 'LightBlue', 173, 216, 230),
(85, 'SkyBlue', 135, 206, 235),
(86, 'LightSkyBlue', 135, 206, 250),
(87, 'DeepSkyBlue', 0, 191, 255),
(88, 'DodgerBlue', 30, 144, 255),
(89, 'CornflowerBlue', 100, 149, 237),
(90, 'MediumSlateBlue', 123, 104, 238),
(91, 'RoyalBlue', 65, 105, 225),
(92, 'Blue', 0, 0, 255),
(93, 'MediumBlue', 0, 0, 205),
(94, 'DarkBlue', 0, 0, 139),
(95, 'Navy', 0, 0, 128),
(96, 'MidnightBlue', 25, 25, 112),
(97, 'Cornsilk', 255, 248, 220),
(98, 'BlanchedAlmond', 255, 235, 205),
(99, 'Bisque', 255, 228, 196),
(100, 'NavajoWhite', 255, 222, 173),
(101, 'Wheat', 245, 222, 179),
(102, 'BurlyWood', 222, 184, 135),
(103, 'Tan', 210, 180, 140),
(104, 'RosyBrown', 188, 143, 143),
(105, 'SandyBrown', 244, 164, 96),
(106, 'Goldenrod', 218, 165, 32),
(107, 'DarkGoldenrod', 184, 134, 11),
(108, 'Peru', 205, 133, 63),
(109, 'Chocolate', 210, 105, 30),
(110, 'SaddleBrown', 139, 69, 19),
(111, 'Sienna', 160, 82, 45),
(112, 'Brown', 165, 42, 42),
(113, 'Maroon', 128, 0, 0),
(114, 'White', 255, 255, 255),
(115, 'Snow', 255, 250, 250),
(116, 'Honeydew', 240, 255, 240),
(117, 'MintCream', 245, 255, 250),
(118, 'Azure', 240, 255, 255),
(119, 'AliceBlue', 240, 248, 255),
(120, 'GhostWhite', 248, 248, 255),
(121, 'WhiteSmoke', 245, 245, 245),
(122, 'Seashell', 255, 245, 238),
(123, 'Beige', 245, 245, 220),
(124, 'OldLace', 253, 245, 230),
(125, 'FloralWhite', 255, 250, 240),
(126, 'Ivory', 255, 255, 240),
(127, 'AntiqueWhite', 250, 235, 215),
(128, 'Linen', 250, 240, 230),
(129, 'LavenderBlush', 255, 240, 245),
(130, 'MistyRose', 255, 228, 225),
(131, 'Gainsboro', 220, 220, 220),
(132, 'LightGrey', 211, 211, 211),
(133, 'Silver', 192, 192, 192),
(134, 'DarkGray', 169, 169, 169),
(135, 'Gray', 128, 128, 128),
(136, 'DimGray', 105, 105, 105),
(137, 'LightSlateGray', 119, 136, 153),
(138, 'SlateGray', 112, 128, 144),
(139, 'DarkSlateGray', 47, 79, 79),
(140, 'Black', 0, 0, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `employees`
--

CREATE TABLE `employees` (
  `id` bigint(20) NOT NULL,
  `code` varchar(10) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `birthDate` date NOT NULL,
  `hireDate` date NOT NULL,
  `monthlySalary` double UNSIGNED NOT NULL,
  `payments` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `employees`
--

INSERT INTO `employees` (`id`, `code`, `firstname`, `lastname`, `gender`, `birthDate`, `hireDate`, `monthlySalary`, `payments`) VALUES
(1, 'JAS', 'Jordi', 'Puig Casademont', 'MALE', '1980-10-23', '2021-10-01', 2500, 12),
(2, 'MMM', 'Manel', 'Cases Sole', 'MALE', '1985-05-15', '2010-05-01', 2300, 12),
(3, 'GLS', 'Gemma', 'Font Boix', 'FEMALE', '1990-03-19', '2018-09-01', 2900, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `events`
--

CREATE TABLE `events` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `place` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `backgroundColor` bigint(20) NOT NULL,
  `textColor` bigint(20) NOT NULL,
  `visible` bit(1) NOT NULL,
  `registrationDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `events`
--

INSERT INTO `events` (`id`, `name`, `date`, `startTime`, `endTime`, `place`, `description`, `backgroundColor`, `textColor`, `visible`, `registrationDate`) VALUES
(1, 'curs Java SE - Sessió 1', '2021-05-26', '09:00:00', '14:00:00', 'Aula 3', 'Sessió presencial 1 del curs de DAW', 1, 40, b'0', '2015-05-01 09:00:00'),
(2, 'curs Java SE - Sessió 2', '2021-06-29', '09:00:00', '14:00:00', 'Aula 3', 'Sessió presencial 2 del curs de DAW', 1, 40, b'0', '2015-05-01 09:00:00'),
(3, 'curs Java SE - Sessió 3', '2021-06-30', '09:00:00', '14:00:00', 'Aula 3', 'Sessió presencial 3 del curs de DAW', 1, 40, b'0', '2015-05-01 09:00:00'),
(4, 'curs Java SE - Sessió 4', '2021-07-01', '09:00:00', '14:00:00', 'Aula 3', 'Sessió presencial 4 del curs de DAW', 1, 40, b'0', '2015-05-01 09:00:00'),
(5, 'curs Java SE - Sessió 5', '2021-07-02', '09:00:00', '14:00:00', 'Aula 3', 'Sessió presencial 5 del curs de DAW', 1, 40, b'0', '2015-05-01 09:00:00'),
(6, 'curs Java SE - Sessió 6', '2021-07-03', '09:00:00', '14:00:00', 'Aula 3', 'Sessió presencial 6 del curs de DAW', 1, 40, b'0', '2015-05-01 09:00:00'),
(7, 'Sopar de Cap d´Any 2020', '2020-12-31', '21:30:00', '23:30:00', 'Restaurant Cal Boter', 'Sopar familiar anual Cap d´Any', 5, 80, b'0', '2015-05-01 09:00:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `colors`
--
ALTER TABLE `colors`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code_UNIQUE` (`code`);

--
-- Indices de la tabla `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`id`),
  ADD KEY `backgroundColor_idx` (`backgroundColor`),
  ADD KEY `textColor_idx` (`textColor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `colors`
--
ALTER TABLE `colors`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=141;

--
-- AUTO_INCREMENT de la tabla `employees`
--
ALTER TABLE `employees`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `events`
--
ALTER TABLE `events`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `backgroundColor` FOREIGN KEY (`backgroundColor`) REFERENCES `colors` (`id`),
  ADD CONSTRAINT `textColor` FOREIGN KEY (`textColor`) REFERENCES `colors` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
