-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2022 at 04:54 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 7.4.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid`
--

-- --------------------------------------------------------


--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(5) NOT NULL,
  `accountNo` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `NIC` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `accountNo`, `name`, `address`, `NIC`, `email`, `phone`, `username`, `password`) VALUES
(28, '1234565555', 'Homagama', 'Amandi', '923454312V', 'amanda20@gmail.com', '0711234567', 'amanda123', 'amanda@12345'),
(56, '2147483647', 'tharaki', 'colombo', '543454534V', 'kavi@gmail.com', '0718099026', 'kaviiiindi', 'kavi1256@'),
(85, '1234564641', 'tharu', 'watareka', '999211111V', 'kavi@gmail.com', '0718099026', 'kaviiiindi\n', 'kavi@1234'),
(1003, '4455611111', 'Anusha', 'Colombo', '672343242V', 'anu@gmail.com', '0718888888', 'anuusha123\n', 'anu@1232');

-- --------------------------------------------------------


--
-- Table structure for table `billing`
--

CREATE TABLE `billing` (
  `ID` int(10) NOT NULL,
  `Account_No` varchar(20) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `From_Date` date NOT NULL,
  `Previous_Reading` int(15) NOT NULL,
  `To_Date` date NOT NULL,
  `Current_Reading` int(15) NOT NULL,
  `Units` int(11) NOT NULL,
  `Current_amount` decimal(7,2) NOT NULL,
  `Previous_amount` decimal(7,2) NOT NULL,
  `Total_amount` decimal(7,2) NOT NULL,
  `Status` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `billing`
--

INSERT INTO `billing` (`ID`, `Account_No`, `Name`, `Address`, `From_Date`, `Previous_Reading`, `To_Date`, `Current_Reading`, `Units`, `Current_amount`, `Previous_amount`, `Total_amount`, `Status`) VALUES
(93, '111111111111', 'nimal', 'matara', '2022-03-16', 0, '2022-04-14', 210, 210, '4918.50', '0.00', '10713.00', 'Cancel'),
(95, '111111111111', 'nimal', 'matara', '2022-03-16', 380, '2022-04-14', 400, 20, '157.00', '10713.00', '392.50', 'Cancel'),
(96, '111111111111', 'nimal', 'matara', '2022-03-16', 340, '2022-04-14', 360, 20, '157.00', '0.00', '157.00', 'Cancel'),
(97, '111111111111', 'nimal', 'matara', '2022-03-16', 360, '2022-04-14', 380, 20, '157.00', '0.00', '157.00', 'Cancel'),
(98, '111111111111', 'nimal', 'matara', '2022-03-16', 380, '2022-04-14', 440, 60, '471.00', '157.00', '1491.50', 'Cancel'),
(99, '111111111111', 'nimal', 'matara', '2022-03-16', 480, '2022-04-14', 490, 10, '78.50', '1491.50', '2394.25', 'Cancel'),
(100, '111111111111', 'nimal', 'matara', '2022-03-16', 480, '2022-04-14', 485, 5, '39.25', '2276.50', '2315.75', 'Cancel'),
(101, '111111111111', 'nimal', 'matara', '2022-03-16', 485, '2022-04-14', 495, 10, '78.50', '2315.75', '2394.25', 'Cancel'),
(102, '111111111111', 'nimal', 'matara', '2022-03-16', 495, '2022-04-14', 530, 35, '274.75', '2394.25', '2983.00', 'Cancel'),
(103, '111111111111', 'nimal', 'matara', '2022-03-16', 530, '2022-04-14', 560, 30, '235.50', '3218.50', '3454.00', 'Pending'),
(105, '111111111166', 'isini', 'thihagoda', '2022-03-16', 0, '2022-04-14', 540, 540, '19768.50', '0.00', '19768.50', 'Pending'),
(107, '2147483647', 'tharaki', 'colombo', '2022-03-16', 0, '2022-04-16', 120, 120, '1603.50', '1603.50', '3207.00', 'Done');


-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PaymentID` int(11) NOT NULL,
  `CardType` varchar(20) NOT NULL,
  `CardNumber` varchar(30) NOT NULL,
  `CardHolderName` varchar(50) NOT NULL,
  `CVC` varchar(20) NOT NULL,
  `CardExpireDate` varchar(30) NOT NULL,
  `Status` varchar(50) NOT NULL,
  `TaxAmount` float NOT NULL,
  `TotalAmount` double NOT NULL,
  `PaymentDate` varchar(30) NOT NULL,
  `BillID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`PaymentID`, `CardType`, `CardNumber`, `CardHolderName`, `CVC`, `CardExpireDate`, `Status`, `TaxAmount`, `TotalAmount`, `PaymentDate`, `BillID`) VALUES
(110, 'visa', '2432456443454564', 'Karuna', '786', '09/05/2026', 'pending', 500, 20500, '02/02/2022', 1001),
(112, 'master', '3432456443454561', 'Ravindu', '765', '09/05/2026', 'pending', 225, 10225, '07/02/2022', 1000),
(120, 'master', '1234123412341234', 'Ruwan', '399', '07/09/2026', 'pending', 950, 15950, '02/07/2023', 1002),
(124, 'visa', '2343232343432345', 'hetti', '689', '08/09/2026', 'pending', 950, 15950, '08/07/2023', 1002);

-- --------------------------------------------------------


--
-- Table structure for table `complaint`
--

CREATE TABLE `complaint` (
  `complaintid` int(11) NOT NULL,
  `customername` varchar(25) NOT NULL,
  `date` varchar(25) NOT NULL,
  `location` varchar(25) NOT NULL,
  `problem` varchar(25) NOT NULL,
  `problemstatus` varchar(25) NOT NULL,
  `phonenumber` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `complaint`
--

INSERT INTO `complaint` (`complaintid`, `customername`, `date`, `location`, `problem`, `problemstatus`, `phonenumber`) VALUES
(4, 'sjoijdhd', 'dfdfdf', 'dd', 'dndfj', 'dfdf', '5556699999'),
(5, 'sjoijdhd', 'dfdfdf', 'dd', 'dndfj', 'dfdf', '55566'),
(6, 'sjoijdhd', 'dfdfdf', 'dd', 'dndfj', 'dfdf', 'ddd'),
(7, 'sjoijdhd', 'dfdfdf', 'dd', 'dndfj', 'dfdf', '1234567898'),
(8, 'asinibovindya', '2000-7-9', 'dd', 'dndfj', 'dfdf', '1234567898'),
(9, 'asinibovindya', '2000-7-9', 'dd', 'dndfj', 'dfdf', '1234567898');

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `employeeid` int(10) NOT NULL,
  `employeename` varchar(20) NOT NULL,
  `employeedob` varchar(15) NOT NULL,
  `employeeaddress` varchar(50) NOT NULL,
  `employeegender` varchar(10) NOT NULL,
  `employeesalary` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



--
-- Indexes for dumped tables
--

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005;
COMMIT;


-- --------------------------------------------------------

--
-- Indexes for table `billing`
--
ALTER TABLE `billing`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `billing`
--
ALTER TABLE `billing`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;
COMMIT;

-- --------------------------------------------------------

--
-- Indexes for dumped tables
--

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentID`),
  ADD KEY `fk_billid` (`BillID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--


--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `PaymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=125;

-- --------------------------------------------------------

--
-- Indexes for dumped tables
--

--
-- Indexes for table `complaint`
--
ALTER TABLE `complaint`
  ADD PRIMARY KEY (`complaintid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `complaint`
--
ALTER TABLE `complaint`
  MODIFY `complaintid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

-- --------------------------------------------------------

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employeeid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `employeeid` int(10) NOT NULL AUTO_INCREMENT;
COMMIT;

-- --------------------------------------------------------

--
-- Constraints for dumped tables
--


--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_billid` FOREIGN KEY (`BillID`) REFERENCES `billing` (`BillID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
