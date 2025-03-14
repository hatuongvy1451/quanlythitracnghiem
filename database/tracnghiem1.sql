-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 11, 2025 lúc 02:59 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `tracnghiem`
--
CREATE DATABASE IF NOT EXISTS `tracnghiem1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tracnghiem1`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `answers`
--

CREATE TABLE `answers` (
  `awID` int(11) NOT NULL,
  `qID` int(11) NOT NULL COMMENT 'id câu hỏi',
  `awContent` text NOT NULL,
  `awPictures` text NOT NULL COMMENT 'url ảnh',
  `isRight` tinyint(4) NOT NULL COMMENT '1: đúng; 0: Sai',
  `awStatus` tinyint(4) NOT NULL COMMENT '1: active; 0: hidden'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `answers`
--

INSERT INTO `answers` (`awID`, `qID`, `awContent`, `awPictures`, `isRight`, `awStatus`) VALUES
(1, 1, 'A. Windows key', '', 1, 1),
(2, 1, 'B. Ctrl + Esc', '', 0, 1),
(3, 1, 'C. Alt + F4', '', 0, 1),
(4, 1, 'D. Win + D', '', 0, 1),
(5, 2, 'A. F8', '', 1, 1),
(6, 2, 'B. F10', '', 0, 1),
(7, 2, 'C. Shift + F8', '', 0, 1),
(8, 2, 'D. Ctrl + F8', '', 0, 1),
(9, 3, 'A. Right-click desktop > Personalize', '', 1, 1),
(10, 3, 'B. Ctrl + P', '', 0, 1),
(11, 3, 'C. Win + R', '', 0, 1),
(12, 3, 'D. Alt + Tab', '', 0, 1),
(13, 4, 'A. Win + E', '', 1, 1),
(14, 4, 'B. Win + F', '', 0, 1),
(15, 4, 'C. Ctrl + E', '', 0, 1),
(16, 4, 'D. Alt + E', '', 0, 1),
(17, 5, 'A. Save as PDF in Word', '', 1, 1),
(18, 5, 'B. Print Screen', '', 0, 1),
(19, 5, 'C. Use Paint', '', 0, 1),
(20, 5, 'D. Ctrl + P', '', 0, 1),
(21, 6, 'A. Start > Power > Restart', '', 1, 1),
(22, 6, 'B. Ctrl + R', '', 0, 1),
(23, 6, 'C. Alt + R', '', 0, 1),
(24, 6, 'D. Win + R', '', 0, 1),
(25, 7, 'A. Right-click > New > Shortcut', '', 1, 1),
(26, 7, 'B. Ctrl + N', '', 0, 1),
(27, 7, 'C. Alt + S', '', 0, 1),
(28, 7, 'D. Win + N', '', 0, 1),
(29, 8, 'A. Start > Control Panel', '', 1, 1),
(30, 8, 'B. Ctrl + P', '', 0, 1),
(31, 8, 'C. Win + C', '', 0, 1),
(32, 8, 'D. Alt + C', '', 0, 1),
(33, 9, 'A. Windows Defender', '', 1, 1),
(34, 9, 'B. Task Manager', '', 0, 1),
(35, 9, 'C. Control Panel', '', 0, 1),
(36, 9, 'D. Settings', '', 0, 1),
(37, 10, 'A. Start > Cmd (Admin)', '', 1, 1),
(38, 10, 'B. Win + X', '', 0, 1),
(39, 10, 'C. Ctrl + Cmd', '', 0, 1),
(40, 10, 'D. Alt + Cmd', '', 0, 1),
(41, 11, 'A. Settings > Language', '', 1, 1),
(42, 11, 'B. Ctrl + L', '', 0, 1),
(43, 11, 'C. Alt + L', '', 0, 1),
(44, 11, 'D. Win + L', '', 0, 1),
(45, 12, 'A. Ctrl + Shift + Esc', '', 1, 1),
(46, 12, 'B. Ctrl + Alt + Del', '', 0, 1),
(47, 12, 'C. Alt + Shift + T', '', 0, 1),
(48, 12, 'D. Win + T', '', 0, 1),
(49, 13, 'A. Right-click > Send to > Compressed (zipped) folder', '', 1, 1),
(50, 13, 'B. Ctrl + Z', '', 0, 1),
(51, 13, 'C. Alt + Z', '', 0, 1),
(52, 13, 'D. Win + Z', '', 0, 1),
(53, 14, 'A. Win + I', '', 1, 1),
(54, 14, 'B. Ctrl + I', '', 0, 1),
(55, 14, 'C. Alt + I', '', 0, 1),
(56, 14, 'D. Win + S', '', 0, 1),
(57, 15, 'A. Settings > Personalization > Colors', '', 1, 1),
(58, 15, 'B. Ctrl + C', '', 0, 1),
(59, 15, 'C. Alt + C', '', 0, 1),
(60, 15, 'D. Win + C', '', 0, 1),
(61, 16, 'A. Settings > Network & Internet > Network and Sharing Center', '', 1, 1),
(62, 16, 'B. Ctrl + N', '', 0, 1),
(63, 16, 'C. Alt + N', '', 0, 1),
(64, 16, 'D. Win + N', '', 0, 1),
(65, 17, 'A. File > New > Blank Document', '', 1, 1),
(66, 17, 'B. Ctrl + N', '', 0, 1),
(67, 17, 'C. Alt + N', '', 0, 1),
(68, 17, 'D. Win + N', '', 0, 1),
(69, 18, 'A. Start > Device Manager', '', 1, 1),
(70, 18, 'B. Ctrl + M', '', 0, 1),
(71, 18, 'C. Alt + M', '', 0, 1),
(72, 18, 'D. Win + M', '', 0, 1),
(73, 19, 'A. Settings > Accounts > Sign-in options', '', 1, 1),
(74, 19, 'B. Ctrl + S', '', 0, 1),
(75, 19, 'C. Alt + S', '', 0, 1),
(76, 19, 'D. Win + S', '', 0, 1),
(77, 20, 'A. Start > Disk Management', '', 1, 1),
(78, 20, 'B. Ctrl + D', '', 0, 1),
(79, 20, 'C. Alt + D', '', 0, 1),
(80, 20, 'D. Win + D', '', 0, 1),
(81, 21, 'A. File > New > Blank Workbook', '', 1, 1),
(82, 21, 'B. Ctrl + E', '', 0, 1),
(83, 21, 'C. Alt + E', '', 0, 1),
(84, 21, 'D. Win + E', '', 0, 1),
(85, 22, 'A. Start > Windows Update', '', 1, 1),
(86, 22, 'B. Ctrl + U', '', 0, 1),
(87, 22, 'C. Alt + U', '', 0, 1),
(88, 22, 'D. Win + U', '', 0, 1),
(89, 23, 'A. Settings > Personalization > Colors', '', 1, 1),
(90, 23, 'B. Ctrl + P', '', 0, 1),
(91, 23, 'C. Alt + P', '', 0, 1),
(92, 23, 'D. Win + P', '', 0, 1),
(93, 24, 'A. Start > Windows Defender', '', 1, 1),
(94, 24, 'B. Ctrl + D', '', 0, 1),
(95, 24, 'C. Alt + D', '', 0, 1),
(96, 24, 'D. Win + D', '', 0, 1),
(97, 25, 'A. Settings > Apps > Apps & features', '', 1, 1),
(98, 25, 'B. Ctrl + A', '', 0, 1),
(99, 25, 'C. Alt + A', '', 0, 1),
(100, 25, 'D. Win + A', '', 0, 1),
(101, 26, 'A. File > New > Blank Document', '', 1, 1),
(102, 26, 'B. Ctrl + N', '', 0, 1),
(103, 26, 'C. Alt + N', '', 0, 1),
(104, 26, 'D. Win + N', '', 0, 1),
(105, 27, 'A. File > Open > Browse', '', 1, 1),
(106, 27, 'B. Ctrl + O', '', 0, 1),
(107, 27, 'C. Alt + O', '', 0, 1),
(108, 27, 'D. Win + O', '', 0, 1),
(109, 28, 'A. Press Enter to start a new paragraph', '', 1, 1),
(110, 28, 'B. Ctrl + Enter', '', 0, 1),
(111, 28, 'C. Alt + Enter', '', 0, 1),
(112, 28, 'D. Win + Enter', '', 0, 1),
(113, 29, 'A. Ctrl + C to copy, Ctrl + V to paste', '', 1, 1),
(114, 29, 'B. Ctrl + X to cut, Ctrl + Z to paste', '', 0, 1),
(115, 29, 'C. Alt + C to copy, Alt + V to paste', '', 0, 1),
(116, 29, 'D. Win + C to copy, Win + V to paste', '', 0, 1),
(117, 30, 'A. Home > Font Size dropdown', '', 1, 1),
(118, 30, 'B. Ctrl + Shift + P', '', 0, 1),
(119, 30, 'C. Alt + Shift + P', '', 0, 1),
(120, 30, 'D. Win + Shift + P', '', 0, 1),
(121, 31, 'A. File > Save As > Choose PDF', '', 1, 1),
(122, 31, 'B. Ctrl + S', '', 0, 1),
(123, 31, 'C. Alt + S', '', 0, 1),
(124, 31, 'D. Win + S', '', 0, 1),
(125, 32, 'A. Insert > Link', '', 1, 1),
(126, 32, 'B. Ctrl + K', '', 0, 1),
(127, 32, 'C. Alt + K', '', 0, 1),
(128, 32, 'D. Win + K', '', 0, 1),
(129, 33, 'A. Insert > Table', '', 1, 1),
(130, 33, 'B. Ctrl + T', '', 0, 1),
(131, 33, 'C. Alt + T', '', 0, 1),
(132, 33, 'D. Win + T', '', 0, 1),
(133, 34, 'A. Insert > Object > Excel Spreadsheet', '', 1, 1),
(134, 34, 'B. Ctrl + E', '', 0, 1),
(135, 34, 'C. Alt + E', '', 0, 1),
(136, 34, 'D. Win + E', '', 0, 1),
(137, 35, 'A. File > Save As > Choose location', '', 1, 1),
(138, 35, 'B. Ctrl + S', '', 0, 1),
(139, 35, 'C. Alt + S', '', 0, 1),
(140, 35, 'D. Win + S', '', 0, 1),
(141, 36, 'A. File > Save As > Choose location and format', '', 1, 1),
(142, 36, 'B. Ctrl + Shift + S', '', 0, 1),
(143, 36, 'C. Alt + Shift + S', '', 0, 1),
(144, 36, 'D. Win + Shift + S', '', 0, 1),
(145, 37, 'A. Right-click > Copy Formatting', '', 1, 1),
(146, 37, 'B. Ctrl + Alt + C', '', 0, 1),
(147, 37, 'C. Alt + C', '', 0, 1),
(148, 37, 'D. Win + C', '', 0, 1),
(149, 38, 'A. References > Quote', '', 1, 1),
(150, 38, 'B. Ctrl + Q', '', 0, 1),
(151, 38, 'C. Alt + Q', '', 0, 1),
(152, 38, 'D. Win + Q', '', 0, 1),
(153, 39, 'A. Select and press Ctrl + D', '', 1, 1),
(154, 39, 'B. Ctrl + Shift + D', '', 0, 1),
(155, 39, 'C. Alt + D', '', 0, 1),
(156, 39, 'D. Win + D', '', 0, 1),
(157, 40, 'A. File > Options > Advanced', '', 1, 1),
(158, 40, 'B. Ctrl + F', '', 0, 1),
(159, 40, 'C. Alt + F', '', 0, 1),
(160, 40, 'D. Win + F', '', 0, 1),
(161, 41, 'A. Select and press Ctrl + D', '', 1, 1),
(162, 41, 'B. Ctrl + Shift + D', '', 0, 1),
(163, 41, 'C. Alt + D', '', 0, 1),
(164, 41, 'D. Win + D', '', 0, 1),
(165, 42, 'A. References > Quote', '', 1, 1),
(166, 42, 'B. Ctrl + Q', '', 0, 1),
(167, 42, 'C. Alt + Q', '', 0, 1),
(168, 42, 'D. Win + Q', '', 0, 1),
(169, 43, 'A. Select and press Ctrl + D', '', 1, 1),
(170, 43, 'B. Ctrl + Shift + D', '', 0, 1),
(171, 43, 'C. Alt + D', '', 0, 1),
(172, 43, 'D. Win + D', '', 0, 1),
(173, 44, 'A. File > Options > Advanced', '', 1, 1),
(174, 44, 'B. Ctrl + F', '', 0, 1),
(175, 44, 'C. Alt + F', '', 0, 1),
(176, 44, 'D. Win + F', '', 0, 1),
(177, 45, 'A. Select and press Ctrl + D', '', 1, 1),
(178, 45, 'B. Ctrl + Shift + D', '', 0, 1),
(179, 45, 'C. Alt + D', '', 0, 1),
(180, 45, 'D. Win + D', '', 0, 1),
(181, 46, 'A. References > Quote', '', 1, 1),
(182, 46, 'B. Ctrl + Q', '', 0, 1),
(183, 46, 'C. Alt + Q', '', 0, 1),
(184, 46, 'D. Win + Q', '', 0, 1),
(185, 47, 'A. Select and press Ctrl + D', '', 1, 1),
(186, 47, 'B. Ctrl + Shift + D', '', 0, 1),
(187, 47, 'C. Alt + D', '', 0, 1),
(188, 47, 'D. Win + D', '', 0, 1),
(189, 48, 'A. File > Options > Advanced', '', 1, 1),
(190, 48, 'B. Ctrl + F', '', 0, 1),
(191, 48, 'C. Alt + F', '', 0, 1),
(192, 48, 'D. Win + F', '', 0, 1),
(193, 49, 'A. Select and press Ctrl + D', '', 1, 1),
(194, 49, 'B. Ctrl + Shift + D', '', 0, 1),
(195, 49, 'C. Alt + D', '', 0, 1),
(196, 49, 'D. Win + D', '', 0, 1),
(197, 50, 'A. References > Quote', '', 1, 1),
(198, 50, 'B. Ctrl + Q', '', 0, 1),
(199, 50, 'C. Alt + Q', '', 0, 1),
(200, 50, 'D. Win + Q', '', 0, 1),
(201, 51, 'A. File > New > Blank Workbook', '', 1, 1),
(202, 51, 'B. Ctrl + N', '', 0, 1),
(203, 51, 'C. Alt + N', '', 0, 1),
(204, 51, 'D. Win + N', '', 0, 1),
(205, 52, 'A. File > Open > Browse', '', 1, 1),
(206, 52, 'B. Ctrl + O', '', 0, 1),
(207, 52, 'C. Alt + O', '', 0, 1),
(208, 52, 'D. Win + O', '', 0, 1),
(209, 53, 'A. Right-click column header > Insert', '', 1, 1),
(210, 53, 'B. Ctrl + I', '', 0, 1),
(211, 53, 'C. Alt + I', '', 0, 1),
(212, 53, 'D. Win + I', '', 0, 1),
(213, 54, 'A. Ctrl + C to copy, Ctrl + V to paste', '', 1, 1),
(214, 54, 'B. Ctrl + X to cut, Ctrl + Z to paste', '', 0, 1),
(215, 54, 'C. Alt + C to copy, Alt + V to paste', '', 0, 1),
(216, 54, 'D. Win + C to copy, Win + V to paste', '', 0, 1),
(217, 55, 'A. Home > Font Size dropdown', '', 1, 1),
(218, 55, 'B. Ctrl + Shift + P', '', 0, 1),
(219, 55, 'C. Alt + Shift + P', '', 0, 1),
(220, 55, 'D. Win + Shift + P', '', 0, 1),
(221, 56, 'A. File > Save As > Choose format', '', 1, 1),
(222, 56, 'B. Ctrl + S', '', 0, 1),
(223, 56, 'C. Alt + S', '', 0, 1),
(224, 56, 'D. Win + S', '', 0, 1),
(225, 57, 'A. Insert > Link', '', 1, 1),
(226, 57, 'B. Ctrl + K', '', 0, 1),
(227, 57, 'C. Alt + K', '', 0, 1),
(228, 57, 'D. Win + K', '', 0, 1),
(229, 58, 'A. Insert > Table', '', 1, 1),
(230, 58, 'B. Ctrl + T', '', 0, 1),
(231, 58, 'C. Alt + T', '', 0, 1),
(232, 58, 'D. Win + T', '', 0, 1),
(233, 59, 'A. Insert > Object > Excel Spreadsheet', '', 1, 1),
(234, 59, 'B. Ctrl + E', '', 0, 1),
(235, 59, 'C. Alt + E', '', 0, 1),
(236, 59, 'D. Win + E', '', 0, 1),
(237, 60, 'A. File > Save As > Choose location', '', 1, 1),
(238, 60, 'B. Ctrl + S', '', 0, 1),
(239, 60, 'C. Alt + S', '', 0, 1),
(240, 60, 'D. Win + S', '', 0, 1),
(241, 61, 'A. File > Save As > Choose format', '', 1, 1),
(242, 61, 'B. Ctrl + Shift + S', '', 0, 1),
(243, 61, 'C. Alt + Shift + S', '', 0, 1),
(244, 61, 'D. Win + Shift + S', '', 0, 1),
(245, 62, 'A. Right-click column header > Insert', '', 1, 1),
(246, 62, 'B. Ctrl + I', '', 0, 1),
(247, 62, 'C. Alt + I', '', 0, 1),
(248, 62, 'D. Win + I', '', 0, 1),
(249, 63, 'A. References > Quote', '', 1, 1),
(250, 63, 'B. Ctrl + Q', '', 0, 1),
(251, 63, 'C. Alt + Q', '', 0, 1),
(252, 63, 'D. Win + Q', '', 0, 1),
(253, 64, 'A. Select and press Ctrl + D', '', 1, 1),
(254, 64, 'B. Ctrl + Shift + D', '', 0, 1),
(255, 64, 'C. Alt + D', '', 0, 1),
(256, 64, 'D. Win + D', '', 0, 1),
(257, 65, 'A. File > Options > Advanced', '', 1, 1),
(258, 65, 'B. Ctrl + F', '', 0, 1),
(259, 65, 'C. Alt + F', '', 0, 1),
(260, 65, 'D. Win + F', '', 0, 1),
(261, 66, 'A. Select and press Ctrl + D', '', 1, 1),
(262, 66, 'B. Ctrl + Shift + D', '', 0, 1),
(263, 66, 'C. Alt + D', '', 0, 1),
(264, 66, 'D. Win + D', '', 0, 1),
(265, 67, 'A. References > Quote', '', 1, 1),
(266, 67, 'B. Ctrl + Q', '', 0, 1),
(267, 67, 'C. Alt + Q', '', 0, 1),
(268, 67, 'D. Win + Q', '', 0, 1),
(269, 68, 'A. Select and press Ctrl + D', '', 1, 1),
(270, 68, 'B. Ctrl + Shift + D', '', 0, 1),
(271, 68, 'C. Alt + D', '', 0, 1),
(272, 68, 'D. Win + D', '', 0, 1),
(273, 69, 'A. File > Options > Advanced', '', 1, 1),
(274, 69, 'B. Ctrl + F', '', 0, 1),
(275, 69, 'C. Alt + F', '', 0, 1),
(276, 69, 'D. Win + F', '', 0, 1),
(277, 70, 'A. Select and press Ctrl + D', '', 1, 1),
(278, 70, 'B. Ctrl + Shift + D', '', 0, 1),
(279, 70, 'C. Alt + D', '', 0, 1),
(280, 70, 'D. Win + D', '', 0, 1),
(281, 71, 'A. References > Quote', '', 1, 1),
(282, 71, 'B. Ctrl + Q', '', 0, 1),
(283, 71, 'C. Alt + Q', '', 0, 1),
(284, 71, 'D. Win + Q', '', 0, 1),
(285, 72, 'A. Formulas > Insert Function', '', 1, 1),
(286, 72, 'B. Ctrl + Shift + F', '', 0, 1),
(287, 72, 'C. Alt + Shift + F', '', 0, 1),
(288, 72, 'D. Win + Shift + F', '', 0, 1),
(289, 73, 'A. Insert > Chart', '', 1, 1),
(290, 73, 'B. Ctrl + Shift + C', '', 0, 1),
(291, 73, 'C. Alt + Shift + C', '', 0, 1),
(292, 73, 'D. Win + Shift + C', '', 0, 1),
(293, 74, 'A. Data > Filter', '', 1, 1),
(294, 74, 'B. Ctrl + Shift + F', '', 0, 1),
(295, 74, 'C. Alt + Shift + F', '', 0, 1),
(296, 74, 'D. Win + Shift + F', '', 0, 1),
(297, 75, 'A. Data > Tables > Create Table', '', 1, 1),
(298, 75, 'B. Ctrl + Shift + T', '', 0, 1),
(299, 75, 'C. Alt + Shift + T', '', 0, 1),
(300, 75, 'D. Win + Shift + T', '', 0, 1),
(301, 76, 'A. File > New > Blank Presentation', '', 1, 1),
(302, 76, 'B. Ctrl + N', '', 0, 1),
(303, 76, 'C. Alt + N', '', 0, 1),
(304, 76, 'D. Win + N', '', 0, 1),
(305, 77, 'A. File > Open > Browse', '', 1, 1),
(306, 77, 'B. Ctrl + O', '', 0, 1),
(307, 77, 'C. Alt + O', '', 0, 1),
(308, 77, 'D. Win + O', '', 0, 1),
(309, 78, 'A. Home > New Slide', '', 1, 1),
(310, 78, 'B. Ctrl + M', '', 0, 1),
(311, 78, 'C. Alt + M', '', 0, 1),
(312, 78, 'D. Win + M', '', 0, 1),
(313, 79, 'A. Ctrl + C to copy, Ctrl + V to paste', '', 1, 1),
(314, 79, 'B. Ctrl + X to cut, Ctrl + Z to paste', '', 0, 1),
(315, 79, 'C. Alt + C to copy, Alt + V to paste', '', 0, 1),
(316, 79, 'D. Win + C to copy, Win + V to paste', '', 0, 1),
(317, 80, 'A. Home > Font Size dropdown', '', 1, 1),
(318, 80, 'B. Ctrl + Shift + P', '', 0, 1),
(319, 80, 'C. Alt + Shift + P', '', 0, 1),
(320, 80, 'D. Win + Shift + P', '', 0, 1),
(321, 81, 'A. File > Save As > Choose format', '', 1, 1),
(322, 81, 'B. Ctrl + S', '', 0, 1),
(323, 81, 'C. Alt + S', '', 0, 1),
(324, 81, 'D. Win + S', '', 0, 1),
(325, 82, 'A. Insert > Link', '', 1, 1),
(326, 82, 'B. Ctrl + K', '', 0, 1),
(327, 82, 'C. Alt + K', '', 0, 1),
(328, 82, 'D. Win + K', '', 0, 1),
(329, 83, 'A. Insert > Table', '', 1, 1),
(330, 83, 'B. Ctrl + T', '', 0, 1),
(331, 83, 'C. Alt + T', '', 0, 1),
(332, 83, 'D. Win + T', '', 0, 1),
(333, 84, 'A. Insert > Object > Excel Spreadsheet', '', 1, 1),
(334, 84, 'B. Ctrl + E', '', 0, 1),
(335, 84, 'C. Alt + E', '', 0, 1),
(336, 84, 'D. Win + E', '', 0, 1),
(337, 85, 'A. File > Save As > Choose location', '', 1, 1),
(338, 85, 'B. Ctrl + S', '', 0, 1),
(339, 85, 'C. Alt + S', '', 0, 1),
(340, 85, 'D. Win + S', '', 0, 1),
(341, 86, 'A. File > Save As > Choose format', '', 1, 1),
(342, 86, 'B. Ctrl + S', '', 0, 1),
(343, 86, 'C. Alt + S', '', 0, 1),
(344, 86, 'D. Win + S', '', 0, 1),
(345, 87, 'A. Right-click > Copy Formatting', '', 1, 1),
(346, 87, 'B. Ctrl + Alt + C', '', 0, 1),
(347, 87, 'C. Alt + C', '', 0, 1),
(348, 87, 'D. Win + C', '', 0, 1),
(349, 88, 'A. References > Quote', '', 1, 1),
(350, 88, 'B. Ctrl + Q', '', 0, 1),
(351, 88, 'C. Alt + Q', '', 0, 1),
(352, 88, 'D. Win + Q', '', 0, 1),
(353, 89, 'A. Select and press Ctrl + D', '', 1, 1),
(354, 89, 'B. Ctrl + Shift + D', '', 0, 1),
(355, 89, 'C. Alt + D', '', 0, 1),
(356, 89, 'D. Win + D', '', 0, 1),
(357, 90, 'A. File > Options > Advanced', '', 1, 1),
(358, 90, 'B. Ctrl + F', '', 0, 1),
(359, 90, 'C. Alt + F', '', 0, 1),
(360, 90, 'D. Win + F', '', 0, 1),
(361, 91, 'A. Select and press Ctrl + D', '', 1, 1),
(362, 91, 'B. Ctrl + Shift + D', '', 0, 1),
(363, 91, 'C. Alt + D', '', 0, 1),
(364, 91, 'D. Win + D', '', 0, 1),
(365, 92, 'A. References > Quote', '', 1, 1),
(366, 92, 'B. Ctrl + Q', '', 0, 1),
(367, 92, 'C. Alt + Q', '', 0, 1),
(368, 92, 'D. Win + Q', '', 0, 1),
(369, 93, 'A. Select and press Ctrl + D', '', 1, 1),
(370, 93, 'B. Ctrl + Shift + D', '', 0, 1),
(371, 93, 'C. Alt + D', '', 0, 1),
(372, 93, 'D. Win + D', '', 0, 1),
(373, 94, 'A. File > Options > Advanced', '', 1, 1),
(374, 94, 'B. Ctrl + F', '', 0, 1),
(375, 94, 'C. Alt + F', '', 0, 1),
(376, 94, 'D. Win + F', '', 0, 1),
(377, 95, 'A. Select and press Ctrl + D', '', 1, 1),
(378, 95, 'B. Ctrl + Shift + D', '', 0, 1),
(379, 95, 'C. Alt + D', '', 0, 1),
(380, 95, 'D. Win + D', '', 0, 1),
(381, 96, 'A. References > Quote', '', 1, 1),
(382, 96, 'B. Ctrl + Q', '', 0, 1),
(383, 96, 'C. Alt + Q', '', 0, 1),
(384, 96, 'D. Win + Q', '', 0, 1),
(385, 97, 'A. Select and press Ctrl + D', '', 1, 1),
(386, 97, 'B. Ctrl + Shift + D', '', 0, 1),
(387, 97, 'C. Alt + D', '', 0, 1),
(388, 97, 'D. Win + D', '', 0, 1),
(389, 98, 'A. File > Options > Advanced', '', 1, 1),
(390, 98, 'B. Ctrl + F', '', 0, 1),
(391, 98, 'C. Alt + F', '', 0, 1),
(392, 98, 'D. Win + F', '', 0, 1),
(393, 99, 'A. Select and press Ctrl + D', '', 1, 1),
(394, 99, 'B. Ctrl + Shift + D', '', 0, 1),
(395, 99, 'C. Alt + D', '', 0, 1),
(396, 99, 'D. Win + D', '', 0, 1),
(397, 100, 'A. References > Quote', '', 1, 1),
(398, 100, 'B. Ctrl + Q', '', 0, 1),
(399, 100, 'C. Alt + Q', '', 0, 1),
(400, 100, 'D. Win + Q', '', 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `exams`
--

CREATE TABLE `exams` (
  `testCode` varchar(20) NOT NULL,
  `exOrder` varchar(1) NOT NULL COMMENT 'A;B;C;D;E;F',
  `exCode` varchar(20) NOT NULL COMMENT '=testCode + exOrder',
  `ex_quesIDs` text NOT NULL COMMENT 'mảng các id câu hỏi'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `exams`
--

INSERT INTO `exams` (`testCode`, `exOrder`, `exCode`, `ex_quesIDs`) VALUES
('TEST001', 'A', 'EX001', '3,1,7,2,5,8,4,9,10,6'),
('TEST001', 'B', 'EX002', '15,14,11,18,20,13,19,16,17,12'),
('TEST002', 'A', 'EX003', '23,22,27,21,26,29,24,25,28,30'),
('TEST002', 'B', 'EX004', '36,31,32,34,38,39,33,37,40,35'),
('TEST003', 'A', 'EX005', '43,41,45,42,47,44,49,46,50,48'),
('TEST003', 'B', 'EX006', '53,51,56,52,54,55,58,57,60,59');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `logs`
--

CREATE TABLE `logs` (
  `logID` int(11) NOT NULL,
  `logContent` text NOT NULL,
  `logUserID` int(11) NOT NULL,
  `logExCode` varchar(20) NOT NULL,
  `logDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `questions`
--

CREATE TABLE `questions` (
  `qID` int(11) NOT NULL,
  `qContent` text NOT NULL COMMENT 'nội dung câu hỏi',
  `qPictures` text NOT NULL COMMENT 'url hình đính kèm',
  `qTopicID` int(11) NOT NULL,
  `qLevel` varchar(10) NOT NULL COMMENT 'easy, meidum, diff',
  `qStatus` tinyint(4) NOT NULL COMMENT '1: active; 0: hidden'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `questions`
--

INSERT INTO `questions` (`qID`, `qContent`, `qPictures`, `qTopicID`, `qLevel`, `qStatus`) VALUES
(1, 'Phím tắt nào để mở menu Start trong Windows 10?', '', 1, 'dễ', 1),
(2, 'Cách nào để kích hoạt Safe Mode trong Windows?', '', 1, 'trung bình', 1),
(3, 'Làm thế nào để thay đổi màn hình nền trong Windows 10?', '', 1, 'dễ', 1),
(4, 'Cách mở File Explorer nhanh chóng bằng phím tắt?', '', 1, 'dễ', 1),
(5, 'Làm thế nào để tạo một tệp PDF từ tài liệu Word?', '', 1, 'trung bình', 1),
(6, 'Cách khởi động lại máy tính trong Windows?', '', 1, 'dễ', 1),
(7, 'Làm thế nào để thêm một phím tắt mới vào Windows?', '', 1, 'trung bình', 1),
(8, 'Cách mở Control Panel trong Windows?', '', 1, 'dễ', 1),
(9, 'Làm thế nào để tìm và gỡ bảo mật Windows?', '', 1, 'trung bình', 1),
(10, 'Cách mở Command Prompt với quyền Administrator?', '', 1, 'trung bình', 1),
(11, 'Làm thế nào để thay đổi ngôn ngữ giao diện trong Windows?', '', 1, 'trung bình', 1),
(12, 'Cách mở Task Manager trong Windows?', '', 1, 'dễ', 1),
(13, 'Làm thế nào để tạo một tệp zip?', '', 1, 'trung bình', 1),
(14, 'Cách mở Settings trong Windows?', '', 1, 'dễ', 1),
(15, 'Làm thế nào để thay đổi màu sắc của phông chữ trong Windows?', '', 1, 'dễ', 1),
(16, 'Cách mở Network and Sharing Center trong Windows?', '', 1, 'trung bình', 1),
(17, 'Làm thế nào để tạo một tệp Word mới?', '', 1, 'dễ', 1),
(18, 'Cách mở Device Manager trong Windows?', '', 1, 'trung bình', 1),
(19, 'Làm thế nào để thay đổi mật khẩu Windows?', '', 1, 'trung bình', 1),
(20, 'Cách mở Disk Management trong Windows?', '', 1, 'trung bình', 1),
(21, 'Làm thế nào để tạo một tệp Excel mới?', '', 1, 'dễ', 1),
(22, 'Cách mở Windows Update trong Windows?', '', 1, 'dễ', 1),
(23, 'Làm thế nào để thay đổi màu sắc của màn hình nền trong Windows?', '', 1, 'trung bình', 1),
(24, 'Cách mở Windows Defender trong Windows?', '', 1, 'dễ', 1),
(25, 'Làm thế nào để tìm và gỡ các phần mềm không cần thiết trong Windows?', '', 1, 'trung bình', 1),
(26, 'Làm thế nào để tạo một tài liệu mới trong Word?', '', 2, 'dễ', 1),
(27, 'Cách mở tài liệu Word đã lưu trước đó?', '', 2, 'dễ', 1),
(28, 'Làm thế nào để thêm một đoạn văn mới vào tài liệu Word?', '', 2, 'dễ', 1),
(29, 'Cách sao chép và dán nội dung trong Word?', '', 2, 'dễ', 1),
(30, 'Làm thế nào để thay đổi kích thước phông chữ trong Word?', '', 2, 'dễ', 1),
(31, 'Cách tạo một định dạng tài liệu mới trong Word?', '', 2, 'trung bình', 1),
(32, 'Làm thế nào để thêm một đường dẫn hoặc liên kết trong tài liệu Word?', '', 2, 'trung bình', 1),
(33, 'Cách tạo một bảng trong Word?', '', 2, 'trung bình', 1),
(34, 'Làm thế nào để thêm một bảng tính vào tài liệu Word?', '', 2, 'trung bình', 1),
(35, 'Cách tạo một bản sao của tài liệu Word?', '', 2, 'dễ', 1),
(36, 'Làm thế nào để tạo một bản sao màu sắc của tài liệu Word?', '', 2, 'trung bình', 1),
(37, 'Cách tạo một bản sao màu sắc của một đoạn văn trong Word?', '', 2, 'trung bình', 1),
(38, 'Làm thế nào để thêm một đoạn trích trong tài liệu Word?', '', 2, 'trung bình', 1),
(39, 'Cách tạo một bản sao của một đoạn văn trong Word?', '', 2, 'dễ', 1),
(40, 'Làm thế nào để thay đổi định dạng của một tài liệu Word?', '', 2, 'trung bình', 1),
(41, 'Cách tạo một bản sao của một đoạn văn trong Word?', '', 2, 'dễ', 1),
(42, 'Làm thế nào để thêm một đoạn trích trong tài liệu Word?', '', 2, 'trung bình', 1),
(43, 'Cách tạo một bản sao của một đoạn văn trong Word?', '', 2, 'dễ', 1),
(44, 'Làm thế nào để thay đổi định dạng của một tài liệu Word?', '', 2, 'trung bình', 1),
(45, 'Cách tạo một bản sao của một đoạn văn trong Word?', '', 2, 'dễ', 1),
(46, 'Làm thế nào để thêm một đoạn trích trong tài liệu Word?', '', 2, 'trung bình', 1),
(47, 'Cách tạo một bản sao của một đoạn văn trong Word?', '', 2, 'dễ', 1),
(48, 'Làm thế nào để thay đổi định dạng của một tài liệu Word?', '', 2, 'trung bình', 1),
(49, 'Cách tạo một bản sao của một đoạn văn trong Word?', '', 2, 'dễ', 1),
(50, 'Làm thế nào để thêm một đoạn trích trong tài liệu Word?', '', 2, 'trung bình', 1),
(51, 'Làm thế nào để tạo một tài liệu mới trong Excel?', '', 3, 'dễ', 1),
(52, 'Cách mở tài liệu Excel đã lưu trước đó?', '', 3, 'dễ', 1),
(53, 'Làm thế nào để thêm một cột mới vào tài liệu Excel?', '', 3, 'dễ', 1),
(54, 'Cách sao chép và dán nội dung trong Excel?', '', 3, 'dễ', 1),
(55, 'Làm thế nào để thay đổi kích thước phông chữ trong Excel?', '', 3, 'dễ', 1),
(56, 'Cách tạo một định dạng tài liệu mới trong Excel?', '', 3, 'trung bình', 1),
(57, 'Làm thế nào để thêm một đường dẫn hoặc liên kết trong tài liệu Excel?', '', 3, 'trung bình', 1),
(58, 'Cách tạo một bảng trong Excel?', '', 3, 'trung bình', 1),
(59, 'Làm thế nào để thêm một bảng tính vào tài liệu Excel?', '', 3, 'trung bình', 1),
(60, 'Cách tạo một bản sao của tài liệu Excel?', '', 3, 'dễ', 1),
(61, 'Làm thế nào để tạo một bản sao màu sắc của tài liệu Excel?', '', 3, 'trung bình', 1),
(62, 'Cách tạo một bản sao màu sắc của một cột trong Excel?', '', 3, 'trung bình', 1),
(63, 'Làm thế nào để thêm một đoạn trích trong tài liệu Excel?', '', 3, 'trung bình', 1),
(64, 'Cách tạo một bản sao của một cột trong Excel?', '', 3, 'dễ', 1),
(65, 'Làm thế nào để thay đổi định dạng của một tài liệu Excel?', '', 3, 'trung bình', 1),
(66, 'Cách tạo một bản sao của một cột trong Excel?', '', 3, 'dễ', 1),
(67, 'Làm thế nào để thêm một đoạn trích trong tài liệu Excel?', '', 3, 'trung bình', 1),
(68, 'Cách tạo một bản sao của một cột trong Excel?', '', 3, 'dễ', 1),
(69, 'Làm thế nào để thay đổi định dạng của một tài liệu Excel?', '', 3, 'trung bình', 1),
(70, 'Cách tạo một bản sao của một cột trong Excel?', '', 3, 'dễ', 1),
(71, 'Làm thế nào để thêm một đoạn trích trong tài liệu Excel?', '', 3, 'trung bình', 1),
(72, 'Làm thế nào để thêm một hàm mới vào Excel?', '', 3, 'trung bình', 1),
(73, 'Cách tạo một biểu đồ trong Excel?', '', 3, 'trung bình', 1),
(74, 'Làm thế nào để lọc dữ liệu trong Excel?', '', 3, 'trung bình', 1),
(75, 'Cách tạo một bảng tính tự động trong Excel?', '', 3, 'khó', 1),
(76, 'Làm thế nào để tạo một bài trình bày mới trong PowerPoint?', '', 4, 'dễ', 1),
(77, 'Cách mở bài trình bày PowerPoint đã lưu trước đó?', '', 4, 'dễ', 1),
(78, 'Làm thế nào để thêm một trang mới vào bài trình bày PowerPoint?', '', 4, 'dễ', 1),
(79, 'Cách sao chép và dán nội dung trong PowerPoint?', '', 4, 'dễ', 1),
(80, 'Làm thế nào để thay đổi kích thước phông chữ trong PowerPoint?', '', 4, 'dễ', 1),
(81, 'Cách tạo một định dạng bài trình bày mới trong PowerPoint?', '', 4, 'trung bình', 1),
(82, 'Làm thế nào để thêm một đường dẫn hoặc liên kết vào bài trình bày PowerPoint?', '', 4, 'trung bình', 1),
(83, 'Cách tạo một bảng trong PowerPoint?', '', 4, 'trung bình', 1),
(84, 'Làm thế nào để thêm một bảng tính vào bài trình bày PowerPoint?', '', 4, 'trung bình', 1),
(85, 'Cách tạo một bản sao của bài trình bày PowerPoint?', '', 4, 'dễ', 1),
(86, 'Làm thế nào để tạo một bản sao màu sắc của bài trình bày PowerPoint?', '', 4, 'trung bình', 1),
(87, 'Cách tạo một bản sao màu sắc của một trang trong PowerPoint?', '', 4, 'trung bình', 1),
(88, 'Làm thế nào để thêm một đoạn trích vào bài trình bày PowerPoint?', '', 4, 'trung bình', 1),
(89, 'Cách tạo một bản sao của một trang trong PowerPoint?', '', 4, 'dễ', 1),
(90, 'Làm thế nào để thay đổi định dạng của bài trình bày PowerPoint?', '', 4, 'trung bình', 1),
(91, 'Cách tạo một bản sao của một trang trong PowerPoint?', '', 4, 'dễ', 1),
(92, 'Làm thế nào để thêm một đoạn trích vào bài trình bày PowerPoint?', '', 4, 'trung bình', 1),
(93, 'Cách tạo một bản sao của một trang trong PowerPoint?', '', 4, 'dễ', 1),
(94, 'Làm thế nào để thay đổi định dạng của bài trình bày PowerPoint?', '', 4, 'trung bình', 1),
(95, 'Cách tạo một bản sao của một trang trong PowerPoint?', '', 4, 'dễ', 1),
(96, 'Làm thế nào để thêm một đoạn trích vào bài trình bày PowerPoint?', '', 4, 'trung bình', 1),
(97, 'Cách tạo một bản sao của một trang trong PowerPoint?', '', 4, 'dễ', 1),
(98, 'Làm thế nào để thay đổi định dạng của bài trình bày PowerPoint?', '', 4, 'trung bình', 1),
(99, 'Cách tạo một bản sao của một trang trong PowerPoint?', '', 4, 'dễ', 1),
(100, 'Làm thế nào để thêm một đoạn trích vào bài trình bày PowerPoint?', '', 4, 'trung bình', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `result`
--

CREATE TABLE `result` (
  `rs_num` tinyint(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `exCode` varchar(20) NOT NULL,
  `rs_anwsers` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'các đáp án đã chọn' CHECK (json_valid(`rs_anwsers`)),
  `rs_mark` decimal(10,0) NOT NULL,
  `rs_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `result`
--

INSERT INTO `result` (`rs_num`, `userID`, `exCode`, `rs_anwsers`, `rs_mark`, `rs_date`) VALUES
(1, 2, 'EX001', '[\"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\"]', 10, '2025-01-21 10:00:00'),
(2, 3, 'EX002', '[\"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"C\"]', 9, '2025-01-21 10:30:00'),
(3, 4, 'EX003', '[\"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"B\", \"D\"]', 8, '2025-01-22 11:00:00'),
(4, 5, 'EX004', '[\"A\", \"A\", \"A\", \"A\", \"A\", \"B\", \"B\", \"B\", \"B\", \"B\"]', 5, '2025-01-22 11:30:00'),
(5, 6, 'EX005', '[\"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"C\", \"B\", \"D\"]', 7, '2025-01-23 12:00:00'),
(6, 7, 'EX006', '[\"A\", \"A\", \"A\", \"A\", \"A\", \"A\", \"D\", \"C\", \"B\", \"D\"]', 6, '2025-01-23 12:30:00'),
(7, 2, 'EX003', '[\"D\",\"D\",\"B\",\"D\",\"A\",\"C\",\"A\",\"A\",\"A\",\"A\"]', 5, '2025-03-11 00:00:16'),
(8, 2, 'EX002', '[\"A\",\"A\",\"A\",\"A\",\"B\",\"D\",\"B\",\"C\",\"A\",\"C\"]', 5, '2025-03-11 00:00:14');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test`
--

CREATE TABLE `test` (
  `testID` int(11) NOT NULL,
  `testCode` varchar(20) NOT NULL COMMENT 'mã bài thi',
  `testTilte` text NOT NULL,
  `testTime` int(11) NOT NULL COMMENT 'thời gian làm bài (phút)',
  `tpID` int(11) NOT NULL COMMENT 'id của chủ đề/bài học',
  `num_easy` int(11) NOT NULL COMMENT 'số lượng câu dễ',
  `num_medium` int(11) NOT NULL COMMENT 'số lượng câu trung bình',
  `num_diff` int(11) NOT NULL COMMENT 'số lượng câu khó',
  `testLimit` tinyint(4) NOT NULL COMMENT 'số lần thi',
  `testDate` date NOT NULL,
  `testStatus` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `test`
--

INSERT INTO `test` (`testID`, `testCode`, `testTilte`, `testTime`, `tpID`, `num_easy`, `num_medium`, `num_diff`, `testLimit`, `testDate`, `testStatus`) VALUES
(1, 'TEST001', 'Hệ điều hành Windows', 30, 1, 8, 7, 5, 20, '2025-01-21', 1),
(2, 'TEST002', 'Microsoft Word', 30, 2, 6, 8, 6, 20, '2025-01-22', 1),
(3, 'TEST003', 'Microsoft Excel', 30, 3, 7, 6, 7, 20, '2025-01-23', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `topics`
--

CREATE TABLE `topics` (
  `tpID` int(11) NOT NULL,
  `tpTitle` text NOT NULL COMMENT 'tên topic',
  `tpParent` int(11) NOT NULL COMMENT 'id của topic cha',
  `tpStatus` tinyint(4) NOT NULL COMMENT '1: active; 0: hidden'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `topics`
--

INSERT INTO `topics` (`tpID`, `tpTitle`, `tpParent`, `tpStatus`) VALUES
(1, 'Hệ điều hành Windows', 0, 1),
(2, 'Microsoft Word', 0, 1),
(3, 'Microsoft Excel', 0, 1),
(4, 'Microsoft PowerPoint', 0, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `userName` varchar(40) NOT NULL COMMENT 'login = userName',
  `userEmail` varchar(20) NOT NULL,
  `userPassword` varchar(40) NOT NULL DEFAULT '123456' COMMENT 'mã hóa dùng md5',
  `userFullName` varchar(40) NOT NULL,
  `isAdmin` tinyint(4) NOT NULL DEFAULT 0 COMMENT '1: admin; 0: user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`userID`, `userName`, `userEmail`, `userPassword`, `userFullName`, `isAdmin`) VALUES
(1, 'admin', 'admin@gmail.com', '123456', 'Admin', 1),
(2, 'thuhuyen', 'thuhuyen@gmail.com', '123456', 'Thu Huyen', 0),
(3, 'tranthib', 'tranthib@gmail.com', '123456', 'Trần Thị B', 0),
(4, 'hathif', 'hatheF@gmail.com', '123456', 'Hà Thị F', 0),
(5, 'vuquangg', 'vuquangG@gmail.com', '123456', 'Vũ Quang G', 0),
(6, 'tuongvy', 'tuongvy@gmail.com', 'tuongvy', 'Hà Tường Vy', 1),
(7, 'ngocan', 'nna@gmail.com', 'ngocan', 'Nguyễn Ngọc An', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`awID`),
  ADD KEY `qID` (`qID`);

--
-- Chỉ mục cho bảng `exams`
--
ALTER TABLE `exams`
  ADD PRIMARY KEY (`testCode`,`exOrder`),
  ADD KEY `testCode` (`testCode`),
  ADD KEY `exCode` (`exCode`);

--
-- Chỉ mục cho bảng `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`logID`),
  ADD KEY `logUserID` (`logUserID`);

--
-- Chỉ mục cho bảng `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`qID`),
  ADD KEY `qTopicID` (`qTopicID`);

--
-- Chỉ mục cho bảng `result`
--
ALTER TABLE `result`
  ADD PRIMARY KEY (`rs_num`) USING BTREE,
  ADD KEY `userID` (`userID`),
  ADD KEY `exID` (`exCode`),
  ADD KEY `exCode` (`exCode`);

--
-- Chỉ mục cho bảng `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`testID`,`tpID`) USING BTREE,
  ADD KEY `tpID` (`tpID`),
  ADD KEY `testCode` (`testCode`);

--
-- Chỉ mục cho bảng `topics`
--
ALTER TABLE `topics`
  ADD PRIMARY KEY (`tpID`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `answers`
--
ALTER TABLE `answers`
  MODIFY `awID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=401;

--
-- AUTO_INCREMENT cho bảng `logs`
--
ALTER TABLE `logs`
  MODIFY `logID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `questions`
--
ALTER TABLE `questions`
  MODIFY `qID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT cho bảng `result`
--
ALTER TABLE `result`
  MODIFY `rs_num` tinyint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `test`
--
ALTER TABLE `test`
  MODIFY `testID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `topics`
--
ALTER TABLE `topics`
  MODIFY `tpID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `answers`
--
ALTER TABLE `answers`
  ADD CONSTRAINT `answers_ibfk_1` FOREIGN KEY (`qID`) REFERENCES `questions` (`qID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `exams`
--


--
-- Các ràng buộc cho bảng `logs`
--

--
-- Các ràng buộc cho bảng `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`qTopicID`) REFERENCES `topics` (`tpID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `result`
--
ALTER TABLE `result`
  ADD CONSTRAINT `result_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Các ràng buộc cho bảng `test`
--
ALTER TABLE `test`
  ADD CONSTRAINT `test_ibfk_1` FOREIGN KEY (`tpID`) REFERENCES `topics` (`tpID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `test_ibfk_2` FOREIGN KEY (`testCode`) REFERENCES `exams` (`testCode`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
