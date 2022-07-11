-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 11, 2022 at 05:54 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.29

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
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `name` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `number` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `date`, `name`, `email`, `number`, `password`) VALUES
(6, '2022-05-20 06:01:48', 'andrei', 'andrei@gmail.com', '0982315115231', '$2y$10$/JgjZIFHfO0ELvVKdM8yDeYXZVhBt6pv205D8p1FyuAi.KojJP4qu'),
(7, '2022-05-20 06:04:04', 'Christian Lupian', 'christian@gmail.com', '09083578076', '$2y$10$yjh23zvqDmXpKJRasFIRE.e6r4NGrLis.tO/GSwhn/xUjyLdJWzqq'),
(8, '2022-05-20 06:28:49', 'kerr sano', 'kerr@gmail.com', '0908357956312', '$2y$10$HFCwVM2WdhHdtK6P7XKlp.0ikYsGXiQAjWkFpw.3MTW.qyunvGfrS'),
(9, '2022-06-29 06:53:45', 'fred', 'fred@gmail.com', '0912314612552', '$2y$10$qrHKdRzh1VJA3gc3457sXuoO5LP6MtlWFa0HSpvDiZm.1aKIeE3Bi');

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL,
  `service_id` int(11) DEFAULT NULL,
  `mechanic_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `vehicle_id` int(11) DEFAULT NULL,
  `booking_status` varchar(50) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `booking_date` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`booking_id`, `service_id`, `mechanic_id`, `user_id`, `vehicle_id`, `booking_status`, `longitude`, `latitude`, `booking_date`) VALUES
(1, 1, 1, 1, 1, 'complete', '111111', '22222', '2022-05-20 22:51:14'),
(2, 1, 1, 1, 1, NULL, NULL, NULL, '2022-07-11 15:34:59'),
(3, 1, 1, 1, 1, 'pending', NULL, NULL, '2022-07-11 15:35:54'),
(4, 1, 1, 1, 1, 'pending', NULL, NULL, '2022-07-11 15:41:01'),
(5, 1, 1, 1, 1, 'pending', '0.00', '0.00', '2022-07-11 15:44:27'),
(6, 1, 1, 1, 1, 'pending', '123.94340240000001', '10.303975099999999', '2022-07-11 15:50:00');

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
(1, 'good job', 4, '2022-05-15', 1, 1),
(2, 'excellent', 5, '2022-05-15', 1, 23);

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
(1, 'Potot', 'Val', 'val@gmail.com', '09083451441123', '12345', 'Locatha, Looc LLC', '2 Wheels'),
(2, 'Builder', 'Bob', 'bob@gmail.com', '0954434623', 'bob123', 'Mandaue City', '4 Wheels'),
(3, 'Monkey', 'Dragon', 'dragon@gmail.com', '0934264262452', 'dragon456', 'Cebu City', '4 wheels & 2 wheels'),
(4, 'Monkey', 'Garp', 'garp@gmail.com', '09434623423623', 'garp789', 'Navy headquarters', '4 wheels'),
(5, 'Redhair', 'Shanks', 'shanks@gmail.com', '09123456667', 'shanks123', 'East Blue', '2 Wheels');

-- --------------------------------------------------------

--
-- Table structure for table `mechanic_location`
--

CREATE TABLE `mechanic_location` (
  `location_id` int(11) NOT NULL,
  `latitude` varchar(500) NOT NULL,
  `longitude` varchar(500) NOT NULL,
  `address` varchar(500) NOT NULL,
  `mechanic_id` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mechanic_location`
--

INSERT INTO `mechanic_location` (`location_id`, `latitude`, `longitude`, `address`, `mechanic_id`) VALUES
(1, '10.2637428', '123.8382068', '7R7Q G8Q, Antuwanga, Talisay, Cebu, Philippines', '23'),
(21, '10.2637428', '123.8382068', '7R7Q G8Q, Antuwanga, Talisay, Cebu, Philippines', ''),
(22, '10.2637428', '123.8382068', '7R7Q G8Q, Antuwanga, Talisay, Cebu, Philippines', ''),
(23, '10.2637428', '123.8382068', '7R7Q G8Q, Antuwanga, Talisay, Cebu, Philippines', ''),
(24, '10.2637428', '123.8382068', '7R7Q G8Q, Antuwanga, Talisay, Cebu, Philippines', ''),
(25, '10.303970399999999', '123.94338680000001', '19 Locata Rd, Lapu-Lapu City, Cebu, Philippines', '1'),
(30, '10.1111122', '123.6605643', 'Perrelos, Carcar City, Cebu', '23');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `service_id` int(11) NOT NULL,
  `service_type` varchar(50) NOT NULL,
  `service_name` varchar(50) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `service_cost` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`service_id`, `service_type`, `service_name`, `description`, `service_cost`) VALUES
(1, 'Two Wheels', 'Change oil & filter', '100', 'blah blah blah'),
(2, 'Four Wheels', 'Brake work', '200', 'blah blah blah'),
(3, 'Four Wheels', 'Battery replacement', '300', 'blah blah blah'),
(4, 'Four Wheels', 'Engine Tune-up', '500', 'blah blah blah'),
(5, 'Two Wheels', 'Spark plugs check', '100', 'blah blah blah');

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
(4, '2022-04-08', 'BATTERY REPLACEMENT', 400, 'CASH', 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `Firstname` varchar(50) DEFAULT NULL,
  `Lastname` varchar(50) DEFAULT NULL,
  `address` varchar(5000) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone_num` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `Firstname`, `Lastname`, `address`, `email`, `phone_num`, `password`) VALUES
(1, 'Christian', 'Lupian', 'Looc, Locatha LLC', 'lupian@gmail.com', '09083578076', '311297'),
(2, 'Alago', 'Frederick', 'Talisay City', 'alago@gmail.com', '0954434623', 'alago123'),
(3, 'Andrei', 'Origenes', 'Mandaue City', 'Andrei@gmail.com', '09123456667', 'andrei123'),
(4, 'Kerr', 'sano', 'Mandaue City', 'kerr@gmail.com', '00953423624442', 'kerr456'),
(5, 'Sain', 'Fuellas', 'Sambag 2', 'sain@gmail.com', '094346423243', 'sain123');

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

--
-- Dumping data for table `user_location`
--

INSERT INTO `user_location` (`location_id`, `latitude`, `longitude`, `address`, `user_id`) VALUES
(1, '10.26427842824107', '123.8384620855812', '7R7Q+M9W, Rafael Rabaya Rd, Talisay, 6045 Cebu', 1);

-- --------------------------------------------------------

--
-- Table structure for table `vehicle`
--

CREATE TABLE `vehicle` (
  `vehicle_id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `user_id` int(11) NOT NULL,
  `vehicle_image` varchar(50) NOT NULL,
  `vehicle_brand` varchar(50) DEFAULT NULL,
  `vehicle_type` varchar(50) NOT NULL,
  `vehicle_model` varchar(30) DEFAULT NULL,
  `fuel_type` varchar(30) DEFAULT NULL,
  `vehicle_year` varchar(50) NOT NULL,
  `plate_num` varchar(50) NOT NULL,
  `chassis_num` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `vehicle`
--

INSERT INTO `vehicle` (`vehicle_id`, `date`, `user_id`, `vehicle_image`, `vehicle_brand`, `vehicle_type`, `vehicle_model`, `fuel_type`, `vehicle_year`, `plate_num`, `chassis_num`) VALUES
(4, '2022-05-21 00:04:28', 2, '', 'Yamaha', '', 'Nmax 155', 'Diesel', '2022', 'XXX-9995', '');

-- --------------------------------------------------------

--
-- Table structure for table `vehicle_transactions`
--

CREATE TABLE `vehicle_transactions` (
  `transaction_id` int(11) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `service_name` varchar(50) NOT NULL,
  `service_cost` varchar(50) NOT NULL
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
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`booking_id`);

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
  ADD PRIMARY KEY (`vehicle_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `vehicle_transactions`
--
ALTER TABLE `vehicle_transactions`
  ADD PRIMARY KEY (`transaction_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mechanic`
--
ALTER TABLE `mechanic`
  MODIFY `mechanic_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `mechanic_location`
--
ALTER TABLE `mechanic_location`
  MODIFY `location_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `service_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user_location`
--
ALTER TABLE `user_location`
  MODIFY `location_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `vehicle`
--
ALTER TABLE `vehicle`
  MODIFY `vehicle_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `vehicle_transactions`
--
ALTER TABLE `vehicle_transactions`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `vehicle`
--
ALTER TABLE `vehicle`
  ADD CONSTRAINT `vehicle_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
