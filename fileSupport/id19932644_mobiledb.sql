-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jan 24, 2023 at 03:53 PM
-- Server version: 10.5.16-MariaDB
-- PHP Version: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id19932644_mobiledb`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(1, 'admin', 'admin@gmail.com', '$2y$10$OmQryG870.ERe/GMdd7hpOooOhTKsJ61F4qkmhaoqG0wi.x9Ka3NC'),
(12, 'yonathan SUS', 'yonathanSUS@gmail.com', '$2y$10$Cgw6vzYw9pQQZOvddFwMq.ckBf5gK5RHuWbND02PnR7w8CZf0/HV6'),
(15, 'marchel SUS', 'marchelSUS@gmail.com', '$2y$10$p7zJGbYSHPYKC9fvhYkA7eRIx2e4a0oDYp6j3WAH5bYqV73pK7Kjm'),
(20, 'owen SUS', 'owenSUS@gmail.com', '$2y$10$h.MsW668pV2aX6W2ZymMUOgmbA4vKLT52YDmJ/SrScdrzzm7ErvM6'),
(21, 'yonathan rahadi', 'yonathan123@gmail.com', '$2y$10$UzOOCijSNpzipsHNMX1xBeW6cdyPy82lnwF0X51rLmFhxXox9N36a'),
(22, 'email dikha', 'email@gmail.com', '$2y$10$VZ8bo.NcHvKEppoBrXvzOupaX2s9I6X6bnAGpcz69BI6HxOuRr9ca'),
(23, 'testing', 'testing@gmail.com', '$2y$10$ccAjY1p0Kbwr7O47GOJBP.4GHJK0u3oKfH9ycSy.83qK.BOmf1/XW'),
(24, 'testing1', 'testing1@gmail.com', '$2y$10$Igr4/jI61gx19xzYtfmx/uKRej0KKXwLAM4E3IDXp/RVpmbjF7J0e'),
(25, 'testing2', 'testing2@gmail.com', '$2y$10$0YetlkABoj.ud.Q0ECriGO0OEXt5GRvj5hKg7mtXTlxOFWyDztXeW'),
(26, 'JooMblo', 'Josua@gmail.com', '$2y$10$4hN7N1tjqwdZsC7.oQ7RJu0ocNnkf4anC8dOPiMGOTfeaUFbO.jHG'),
(27, 'Marchel', 'aberlansyah23@gmail.com', '$2y$10$NRr4Een6I2ug.WxgSWiYSeaqMKX1VbiObxmrZuLMbH8xSyc9BUrzO'),
(28, 'kwskwkks', 'azsss@gmail.com', '$2y$10$U3pMIndqBSm2RaHQr6p4H.XJQn5YlxXcWhsf6/RYhPHdqF8ylRp/q'),
(29, 'anjay', 'anjay@gmail.com', '$2y$10$aQwl6Eq5ztqCw2L0sMQZgeemEHZ/v0m6le4zuDR4FYeb7zdqkGWb6'),
(30, 'memang', 'memangbeda28@gmail.com', '$2y$10$8L14/Q0JHrsG6leJEkr.fOh5OFrTGcQXJLSdBlQjy3zDS4Vpj.bP.'),
(33, 'testing', 'anakpolos@gmail.com', '$2y$10$nLb.jjue3ADAAPZ8wCW4E.3g5kO.cTHAecQKaqyGHF5G4e5hQEldu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
