-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2024 at 03:41 PM
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
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE Database library;

CREATE TABLE `admin` (
  `USER_ID` varchar(50) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `CONTACT` varchar(10) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`USER_ID`, `NAME`, `PASSWORD`, `CONTACT`, `gender`) VALUES
('AAR003', 'Aaron', 'aaron789', '083456789', 'Male'),
('LEO001', 'Leonel', 'leo123', '081234567', 'Male'),
('RIZ002', 'Rizal', 'rizal456', '082345678', 'Male'),
('TAM004', 'Tama', 'tama012', '084567890', 'Male');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `quantity` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `title`, `author`, `quantity`) VALUES
(2, '1984', 'George Orwell', 10),
(3, 'Moby Dick', 'Herman Melville', 2),
(4, 'Java Programming for Beginners', 'John Doe', 10),
(5, 'Advanced Java Programming', 'Jane Smith', 14),
(6, 'Database Management Systems', 'Michael Johnson', 12),
(7, 'Introduction to Algorithms', 'Chris Lee', 8),
(8, 'Data Structures and Algorithms', 'Alice Brown', 20),
(9, 'Web Development with JavaScript', 'Robert White', 23),
(10, 'Mastering MySQL', 'Linda Green', 29),
(11, 'Python for Data Science', 'David Williams', 18),
(12, 'Machine Learning Basics', 'Sarah Thomas', 22),
(13, 'Deep Learning for Beginners', 'William Clark', 14),
(14, 'Data Science with R', 'Olivia Harris', 17),
(15, 'Big Data Analytics', 'Liam Lewis', 13),
(16, 'The Art of Computer Programming', 'James Walker', 9),
(17, 'Computer Networks', 'Benjamin Hall', 5),
(18, 'Operating Systems Concepts', 'Emily Young', 11),
(19, 'Software Engineering Principles', 'Daniel King', 8),
(20, 'Artificial Intelligence with Python', 'Sophia Allen', 19),
(21, 'Introduction to Statistics', 'Daniel Martin', 16),
(22, 'Advanced Database Management', 'Henry Scott', 23),
(23, 'Cloud Computing Basics', 'Isabella King', 27),
(24, 'Networking Essentials', 'Elijah Wright', 12),
(25, 'Cybersecurity Fundamentals', 'Charlotte Green', 14),
(26, 'JavaScript for Beginners', 'Mason Carter', 9),
(27, 'React.js for Web Development', 'Amelia Perez', 25),
(28, 'HTML & CSS for Beginners', 'Jackson Roberts', 30),
(29, 'Introduction to SQL', 'Lucas Mitchell', 13),
(30, 'Building Web Applications with PHP', 'Mia Davis', 16),
(31, 'The C Programming Language', 'Charlotte Evans', 22),
(32, 'Introduction to Machine Learning', 'Liam Gonzalez', 18),
(33, 'Java for Advanced Developers', 'Ethan Nelson', 20),
(34, 'AI and Robotics', 'Harper Thompson', 10),
(35, 'Cloud Security', 'Henry Ramirez', 21),
(36, 'Data Visualization with Python', 'Eva Cooper', 15),
(37, 'Networking with Python', 'Sebastian Perez', 17),
(38, 'Introduction to C++', 'David Carter', 9),
(39, 'C# for Beginners', 'Grace Adams', 12),
(40, 'Advanced HTML5 and CSS3', 'Oliver Morris', 16),
(41, 'Ruby on Rails for Web Development', 'Ella Lee', 14),
(42, 'Agile Software Development', 'Jackson Evans', 18),
(43, 'Android Development with Kotlin', 'Avery Turner', 11),
(44, 'React Native for Mobile Apps', 'Carter Clark', 20),
(45, 'Introduction to Blockchain', 'Sebastian Wright', 8),
(46, 'Digital Marketing with Python', 'Madeline Harris', 12),
(47, 'Mobile App Development with Flutter', 'Henry Scott', 15),
(48, 'Machine Learning with TensorFlow', 'Isabella Walker', 19),
(49, 'C Programming for Beginners', 'Levi Green', 22),
(50, 'Introduction to JavaScript', 'Landon Baker', 20),
(51, 'Linux Command Line Basics', 'Zoe Cooper', 20),
(52, 'Data Structures with Python', 'Hunter Moore', 23),
(53, 'Introduction to Web Development', 'Aiden Collins', 30),
(54, 'Android Studio for Beginners', 'Madeline Walker', 16),
(55, 'JavaScript for Web Development', 'Levi Hill', 11),
(56, 'Advanced Machine Learning', 'Riley Adams', 8),
(57, 'Docker Essentials', 'Maverick White', 14),
(58, 'Introduction to Swift Programming', 'Luke Nelson', 21),
(59, 'Advanced Python Programming', 'Peyton King', 19),
(60, 'C++ for Software Development', 'Lily Edwards', 12),
(61, 'Introduction to Data Analytics', 'Nora Young', 10),
(62, 'Deep Learning with TensorFlow', 'Benjamin Davis', 22),
(63, 'Kubernetes Essentials', 'Madeline Wright', 9),
(64, 'Introduction to Cloud Computing', 'Mason Harris', 25),
(65, 'Python Data Analysis', 'Dylan Johnson', 18),
(66, 'Ethical Hacking and Cybersecurity', 'Jack Martinez', 15),
(67, 'ReactJS: A Comprehensive Guide', 'Sophia Walker', 20),
(68, 'Internet of Things (IoT)', 'James Lee', 30),
(69, 'Game Development with Unity', 'Olivia Anderson', 10),
(70, 'Cloud Architecture with AWS', 'Emma Thompson', 17),
(71, 'Machine Learning with R', 'Mia White', 23),
(72, 'Mastering Git and GitHub', 'Benjamin Johnson', 19),
(73, 'Data Science with Pandas', 'Chloe Brown', 12),
(74, 'iOS Development with Swift', 'Isla Williams', 21),
(75, 'Full Stack Web Development', 'Samuel Turner', 14),
(76, 'Building a REST API with Node.js', 'Grace Lewis', 10),
(77, 'AI in Healthcare', 'Oliver Moore', 8),
(78, 'Programming in Go', 'Mila Brown', 16),
(79, 'Data Science with Hadoop', 'Lucas Adams', 18),
(80, 'DevOps Essentials', 'Scarlett Nelson', 20),
(81, 'Cybersecurity for Beginners', 'Victoria Hall', 14),
(82, 'The Complete Guide to PostgreSQL', 'Luna Wright', 13),
(83, 'Programming Principles in Java', 'Jaxon Green', 22),
(84, 'Design Patterns in Java', 'Luca Moore', 18),
(85, 'Advanced Database Systems', 'Gabriella Carter', 12),
(86, 'Machine Learning with Scikit-Learn', 'Harper Young', 19),
(87, 'Data Science with MATLAB', 'Amos Harris', 21),
(88, 'Deep Learning with PyTorch', 'Sophie Hall', 15),
(89, 'ReactJS for Mobile Apps', 'Chloe King', 16),
(90, 'Python for Artificial Intelligence', 'Ryan Adams', 20),
(91, 'JavaScript and Node.js', 'Carter Lee', 12),
(92, 'Building Modern Web Apps with React', 'Ava Cooper', 22),
(93, 'Mobile Development with React Native', 'Zoe Thompson', 10),
(94, 'Machine Learning Algorithms', 'Mason Edwards', 14),
(95, 'Deep Learning for Computer Vision', 'Sienna Nelson', 18),
(96, 'AI for Business', 'Daniel Young', 16),
(97, 'Data Structures with Java', 'Cora Green', 21),
(98, 'Database Security Fundamentals', 'Wyatt Allen', 13),
(99, 'Introduction to JavaFX', 'Katherine White', 19),
(102, 'The Great Gatsby', 'F. Scott Fitzgerald', 7),
(110, 'Laskar Pelangi', 'Andrea Hirata', 110);

-- --------------------------------------------------------

--
-- Table structure for table `borrow`
--

CREATE TABLE `borrow` (
  `borrow_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `member_name` varchar(100) DEFAULT NULL,
  `book_id` int(11) NOT NULL,
  `books_name` varchar(150) DEFAULT NULL,
  `borrow_date` date DEFAULT NULL,
  `status` enum('borrowed','returned') DEFAULT 'borrowed',
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrow`
--

INSERT INTO `borrow` (`borrow_id`, `member_id`, `member_name`, `book_id`, `books_name`, `borrow_date`, `status`, `created_at`, `updated_at`) VALUES
(2, 2, 'Jane Smith', 2, '1984', '2024-12-07', 'borrowed', '2024-12-10 04:40:57', '2024-12-10 04:40:57'),
(3, 3, 'Alice Johnson', 3, 'To Kill a Mockingbird', '2024-12-07', 'borrowed', '2024-12-10 04:40:57', '2024-12-10 04:40:57'),
(13, 4, 'Agustav', 45, 'Introduction to Blockchain', '2024-12-15', 'borrowed', '2024-12-15 13:15:53', '2024-12-15 14:34:05'),
(14, 4, 'Agustav', 5, 'Advanced Java Programming', '2024-12-15', 'borrowed', '2024-12-15 13:15:57', '2024-12-15 14:40:24'),
(15, 4, 'Agustav', 55, 'JavaScript for Web Development', '2024-12-15', 'borrowed', '2024-12-15 13:16:02', '2024-12-15 13:16:02'),
(16, 4, 'Agustav', 43, 'Android Development with Kotlin', '2024-12-15', 'borrowed', '2024-12-15 13:16:07', '2024-12-15 13:16:07'),
(17, 4, 'Agustav', 38, 'Introduction to C++', '2024-12-15', 'borrowed', '2024-12-15 13:16:13', '2024-12-15 13:16:13'),
(18, 4, 'Agustav', 89, 'ReactJS for Mobile Apps', '2024-12-15', 'borrowed', '2024-12-15 13:16:27', '2024-12-15 13:54:24'),
(19, 4, 'Agustav', 5, 'Advanced Java Programming', '2024-12-15', 'borrowed', '2024-12-15 13:27:15', '2024-12-15 13:27:15');

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `member_id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `address` text DEFAULT NULL,
  `registration_date` date DEFAULT curdate(),
  `password` varchar(255) NOT NULL,
  `gender` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`member_id`, `name`, `email`, `phone`, `address`, `registration_date`, `password`, `gender`) VALUES
(1, 'Jane Smith', 'janesmith@example.com', '081987654321', 'Jl. Kenari No. 2, Bandung', '2024-12-07', 'securepass123', 'Male'),
(2, 'Ahmad Zaky', 'ahmadzaky@example.com', '081234123456', 'Jl. Anggrek No. 5, Surabaya', '2024-12-07', 'mypassword456', 'Male'),
(3, 'Siti Aminah', 'sitiaminah@example.com', '081567890123', 'Jl. Cempaka No. 3, Medan', '2024-12-07', 'aminahsecure789', 'Female'),
(4, 'Agustav', 'marcelino@gmail.com', '0987654321', 'jalan tumbal negara 2', '2024-12-08', 'agus123', 'Male');

-- --------------------------------------------------------

--
-- Table structure for table `returns`
--

CREATE TABLE `returns` (
  `return_id` int(11) NOT NULL,
  `member_id` int(11) DEFAULT NULL,
  `member_name` varchar(255) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_name` varchar(255) DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `returns`
--

INSERT INTO `returns` (`return_id`, `member_id`, `member_name`, `book_id`, `book_name`, `return_date`, `status`) VALUES
(1, 4, 'Agustav', 96, 'AI for Business', '2024-12-19', 'returned');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staff_id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` varchar(25) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staff_id`, `name`, `password`, `role`, `gender`) VALUES
(1, 'Alice', 'password123', 'Librarian', 'Female'),
(2, 'Bob', 'securepass', 'Librarian', 'Male'),
(3, 'Charlie', 'assistantpass', 'Assistant', 'Male'),
(4, 'Diana', 'helpme123', 'Assistant', 'Female'),
(6, 'Frank', 'leadpass', 'Manager', 'Male');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`USER_ID`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `borrow`
--
ALTER TABLE `borrow`
  ADD PRIMARY KEY (`borrow_id`),
  ADD KEY `member_id` (`member_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`member_id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `returns`
--
ALTER TABLE `returns`
  ADD PRIMARY KEY (`return_id`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staff_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111;

--
-- AUTO_INCREMENT for table `borrow`
--
ALTER TABLE `borrow`
  MODIFY `borrow_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `returns`
--
ALTER TABLE `returns`
  MODIFY `return_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `staff`
--
ALTER TABLE `staff`
  MODIFY `staff_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrow`
--
ALTER TABLE `borrow`
  ADD CONSTRAINT `borrow_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `members` (`member_id`),
  ADD CONSTRAINT `borrow_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `books` (`book_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
