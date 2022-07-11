-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 13, 2022 at 04:52 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mangayo_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `email`, `password`) VALUES
(1, 'admin@gmail.com', '12345'),
(3, 'sample@gmail.com', '311297');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL,
  `feedback_description` varchar(500) NOT NULL,
  `feedback_score` int(1) NOT NULL,
  `feedback_date` date NOT NULL,
  `user_id` int(11) NOT NULL,
  `mechanic_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedback_id`, `feedback_description`, `feedback_score`, `feedback_date`, `user_id`, `mechanic_id`) VALUES
(1, '', 0, '0000-00-00', 0, 0),
(2, 'asdasd', 0, '2020-05-23', 1, 1),
(3, 'null', 0, '2022-05-08', 1, 1),
(4, 'null', 0, '2022-05-08', 1, 1),
(5, 'null', 0, '2022-05-10', 1, 1),
(6, 'null', 0, '2022-05-10', 1, 23);

-- --------------------------------------------------------

--
-- Table structure for table `mechanic`
--

CREATE TABLE `mechanic` (
  `mechanic_id` int(11) NOT NULL,
  `Lname` varchar(50) DEFAULT NULL,
  `Fname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_num` varchar(50) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `specialty` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mechanic`
--

INSERT INTO `mechanic` (`mechanic_id`, `Lname`, `Fname`, `email`, `phone_num`, `password`, `address`, `specialty`) VALUES
(1, 'Amadeo ', 'Sabino', 'sample@gmail.com', '09231231245123', '123456', 'Cebu City', '4 Wheels Specialty'),
(23, 'Alago', 'Frederick', 'qwerty@gmail.com', '9203300852', '111111', 'yayauw whwywyw wgaywta agaywta', '4 Wheels');

-- --------------------------------------------------------

--
-- Table structure for table `mechanic_location`
--

CREATE TABLE `mechanic_location` (
  `location_id` int(11) NOT NULL,
  `latitude` varchar(500) NOT NULL,
  `longitude` varchar(500) NOT NULL,
  `address` varchar(500) NOT NULL,
  `mechanic_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mechanic_location`
--

INSERT INTO `mechanic_location` (`location_id`, `latitude`, `longitude`, `address`, `mechanic_id`) VALUES
(1, '10.263549538210192', '123.83520781781216', 'Harmonis Residences\r\n', 23),
(2, '10.262907544208444', '123.83539368541481', 'Sr. San Roque\r\n', 23),
(3, '10.264843577948032', '123.83727191232921 ', 'Rapide Talisay Cebu - Auto Service, Car Repair, Toledo - Tabunok Road, A-1, Talisay, Cebu\r\nAndrei\r\n', 23),
(4, '10.257370283599048', '123.83980903297707', 'San Isidro Road, Talisay, Cebu\r\n', 23);

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `service_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `service_name` varchar(50) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `service_cost` varchar(25) DEFAULT NULL,
  `images` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`service_id`, `user_id`, `service_name`, `description`, `service_cost`, `images`) VALUES
(75, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(76, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(77, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(78, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(79, 0, 'ENGINE REPAIR', 'Desription here', '200.00', ''),
(80, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(81, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(82, 0, 'BATTERY REPLACEMENT', 'Desription here', '400.00', ''),
(83, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(84, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(85, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(86, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(87, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(88, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', ''),
(89, 0, 'TIRE REPLACEMENT', 'Desription here', '100.00', '');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `date_service` date NOT NULL,
  `service_type` varchar(250) NOT NULL,
  `service_cost` int(25) NOT NULL,
  `payment_type` varchar(250) NOT NULL,
  `user_id` int(11) NOT NULL,
  `mechanic_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`transaction_id`, `date_service`, `service_type`, `service_cost`, `payment_type`, `user_id`, `mechanic_id`) VALUES
(1, '2022-04-08', 'BATTERY REPLACEMENT', 400, 'CASH', 1, 1),
(2, '2022-04-08', 'BATTERY REPLACEMENT', 400, 'CASH', 1, 1),
(3, '2022-04-08', 'BATTERY REPLACEMENT', 400, 'CASH', 1, 1),
(4, '2022-04-08', 'BATTERY REPLACEMENT', 400, 'CASH', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `Fname` varchar(50) DEFAULT NULL,
  `Lname` varchar(50) DEFAULT NULL,
  `address` varchar(5000) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_num` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `Fname`, `Lname`, `address`, `email`, `phone_num`, `password`) VALUES
(1, 'Fred', 'Alago', 'Tabunok Antuwanga, Talisay, Cebu', 'fred@gmail.com', '+63-933-555-1256', '111111');

-- --------------------------------------------------------

--
-- Table structure for table `user_location`
--

CREATE TABLE `user_location` (
  `location_id` int(11) NOT NULL,
  `latitude` varchar(500) NOT NULL,
  `longitude` varchar(500) NOT NULL,
  `address` varchar(500) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `vehicle_id` int(11) NOT NULL,
  `vehicle_image` varchar(50) NOT NULL,
  `vehicle_brand` varchar(50) DEFAULT NULL,
  `vehicle_model` varchar(30) DEFAULT NULL,
  `fuel_type` varchar(30) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`);

--
-- Indexes for table `mechanic`
--
ALTER TABLE `mechanic`
  ADD PRIMARY KEY (`mechanic_id`);

--
-- Indexes for table `mechanic_location`
--
ALTER TABLE `mechanic_location`
  ADD PRIMARY KEY (`location_id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`service_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_location`
--
ALTER TABLE `user_location`
  ADD PRIMARY KEY (`location_id`);

--
-- Indexes for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD PRIMARY KEY (`vehicle_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `mechanic`
--
ALTER TABLE `mechanic`
  MODIFY `mechanic_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT for table `mechanic_location`
--
ALTER TABLE `mechanic_location`
  MODIFY `location_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_location`
--
ALTER TABLE `user_location`
  MODIFY `location_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `vehicle_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
