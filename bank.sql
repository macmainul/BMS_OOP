-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Apr 16, 2025 at 04:12 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bms`
--

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE `bank` (
  `id` bigint(20) NOT NULL,
  `accountNo` varchar(255) NOT NULL,
  `amount` double NOT NULL,
  `depositeAt` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`id`, `accountNo`, `amount`, `depositeAt`, `status`, `user_id`) VALUES
(1, '123', 12, '2024-12-11 20:02:53', 'Accepted', 1),
(2, '123', 100, '2024-12-11 20:03:05', 'Accepted', 1),
(5, '123', 500, '2024-12-11 20:26:47', 'Accepted', 1),
(6, '123', 1000, '2024-12-11 20:35:27', 'Accepted', 1),
(7, '123', -100, '2024-12-11 20:35:51', 'Accepted', 1),
(8, '123', 100, '2024-12-11 20:49:13', 'Accepted', 1),
(9, '111', -10, '2024-12-11 22:09:13', 'Accepted', 1),
(10, '111', 10, '2024-12-11 22:09:13', 'Accepted', 3),
(11, '123', -5, '2024-12-11 22:11:17', 'Accepted', 3),
(12, '123', 5, '2024-12-11 22:11:17', 'Accepted', 1),
(13, '123', 50, '2024-12-11 22:14:22', 'Accepted', 1),
(14, '123', 100, '2024-12-11 22:15:48', 'Accepted', 1),
(15, '111', -100, '2024-12-11 22:17:36', 'Accepted', 1),
(16, '111', 100, '2024-12-11 22:17:36', 'Accepted', 3),
(17, '123', 500, '2024-12-12 22:23:00', 'Accepted', 1),
(18, '123', -500, '2024-12-12 22:24:17', 'Accepted', 1),
(20, '123', 50000000, '2024-12-12 22:25:05', 'Accepted', 1),
(21, '111', -5000000, '2024-12-12 22:25:59', 'Accepted', 1),
(22, '111', 5000000, '2024-12-12 22:25:59', 'Accepted', 3),
(23, '123', -50000, '2024-12-12 22:26:47', 'Accepted', 1),
(24, '123', -500000, '2024-12-12 22:26:57', 'Accepted', 1),
(25, '123', -5000000, '2024-12-12 22:27:05', 'Accepted', 1),
(26, '123', -3000000, '2024-12-12 22:27:17', 'Accepted', 1),
(27, '123', -30000000, '2024-12-12 22:27:26', 'Accepted', 1),
(28, '123', -600000, '2024-12-12 22:27:44', 'Accepted', 1),
(29, '123', -5851657, '2024-12-12 22:28:03', 'Accepted', 1),
(30, '111', -5000105, '2024-12-12 22:28:13', 'Accepted', 3),
(31, '123', 50, '2024-12-13 17:16:42', 'Accepted', 1),
(32, '123', 500, '2024-12-13 17:17:20', 'Accepted', 1),
(33, '123', -400, '2024-12-13 17:17:52', 'Accepted', 1),
(34, '111', -400, '2024-12-13 17:18:21', 'Accepted', 1),
(35, '111', 400, '2024-12-13 17:18:21', 'Accepted', 3),
(36, '123', 250, '2024-12-13 17:18:50', 'Accepted', 1),
(37, '111', -400, '2024-12-13 17:19:21', 'Accepted', 3),
(38, '123', 6000, '2024-12-13 18:57:32', 'Accepted', 1),
(39, '123', 10000, '2024-12-13 18:58:05', 'Accepted', 1),
(40, '123', -1000, '2024-12-13 18:58:38', 'Accepted', 1),
(41, '111', -1000, '2024-12-13 18:59:13', 'Accepted', 1),
(42, '111', 1000, '2024-12-13 18:59:13', 'Accepted', 3),
(43, '123', -100, '2024-12-13 21:23:33', 'Accepted', 1),
(44, '123', 5000, '2024-12-13 23:50:44', 'Accepted', 1),
(45, '123', 1000, '2024-12-13 23:51:26', 'Accepted', 1),
(46, '123', -5000, '2024-12-13 23:52:16', 'Accepted', 1),
(47, '111', -5000, '2024-12-13 23:53:28', 'Accepted', 1),
(48, '111', 5000, '2024-12-13 23:53:28', 'Accepted', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqpxfqmkelmon6fgt4q6o90q56` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bank`
--
ALTER TABLE `bank`
  ADD CONSTRAINT `FKqpxfqmkelmon6fgt4q6o90q56` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
