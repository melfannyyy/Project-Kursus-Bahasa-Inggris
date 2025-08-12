-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 03 Des 2024 pada 10.01
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kursus`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `enrollment`
--

CREATE TABLE `enrollment` (
  `id` int(11) NOT NULL,
  `active_status` bit(1) DEFAULT NULL,
  `tanggal` date DEFAULT NULL,
  `jadwal_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `enrollment`
--

INSERT INTO `enrollment` (`id`, `active_status`, `tanggal`, `jadwal_id`, `user_id`) VALUES
(1, b'0', '2024-10-01', 1, 2),
(2, b'1', '2024-10-01', 1, 3),
(3, b'1', '2024-10-12', 2, 4),
(4, b'1', '2024-10-10', 2, 5),
(5, b'1', '2024-10-09', 2, 6),
(6, b'1', '2024-10-08', 3, 7),
(7, b'1', '2024-10-05', 4, 8),
(8, b'1', '2024-10-05', 4, 9),
(9, b'1', '2024-10-05', 5, 10),
(10, b'1', '2024-10-05', 6, 11),
(11, b'0', '2024-11-28', 2, 2),
(12, b'0', '2024-11-28', 2, 2),
(13, b'1', '2024-11-28', 2, 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `instructor`
--

CREATE TABLE `instructor` (
  `id` int(11) NOT NULL,
  `active_status` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `instructor`
--

INSERT INTO `instructor` (`id`, `active_status`, `name`) VALUES
(1, b'1', 'Dr. John Doe, M.Ed.'),
(2, b'1', 'Jisoo (Kim Ji-soo)'),
(3, b'1', 'Jennie (Jennie Kim)'),
(4, b'1', 'Rosé (Park Chae-young)'),
(5, b'1', 'Lisa (Lalisa Manoban)');

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwal`
--

CREATE TABLE `jadwal` (
  `id` int(11) NOT NULL,
  `active_status` bit(1) DEFAULT NULL,
  `day` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  `instructor_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `jadwal`
--

INSERT INTO `jadwal` (`id`, `active_status`, `day`, `grade`, `time`, `instructor_id`) VALUES
(1, b'1', 'Senin,Selasa', 'Menengah', '10:00 - 12:00', 4),
(2, b'1', 'Rabu,Kamis', 'Menengah', '10:00 - 12:00', 5),
(3, b'1', 'Senin,Selasa', 'Pemula', '08:00 - 10:00', 2),
(4, b'1', 'Rabu,Kamis', 'Pemula', '08:00 - 10:00', 2),
(5, b'1', 'Jumat,Sabtu', 'Pemula', '08:00 - 10:00', 3),
(6, b'1', 'Sabtu', 'Lanjutan', '13:00 - 15:00', 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `dtype` varchar(31) NOT NULL,
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `access_level` int(11) DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`dtype`, `id`, `email`, `password`, `role`, `username`, `access_level`, `birthdate`, `firstname`, `grade`, `lastname`) VALUES
('Admin', 1, 'admin@mail.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'ADMIN', 'admin', NULL, '2002-01-01', 'firstname', NULL, 'lastname'),
('Member', 2, 'user1@gmail.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user1', NULL, '2004-05-05', 'First', 'Menengah', 'User'),
('Member', 3, 'user3@gmail.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user2', NULL, '2008-06-12', 'Bryan', 'Menengah', 'Johnson'),
('Member', 4, 'michael.johnson@example.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user3', NULL, '2009-12-12', 'Michael', 'Menengah', 'Johnson'),
('Member', 5, 'emily.davis@example.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user4', NULL, '2001-09-20', 'Emily', 'Menengah', 'Davis'),
('Member', 6, 'robert.brown@example.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user5', NULL, '2007-06-22', 'Robert', 'Menengah', 'Brown'),
('Member', 7, 'sarah.miller@example.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user6', NULL, '2002-08-09', 'Sarah', 'Pemula', 'Miller'),
('Member', 8, 'david.wilson@example.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user7', NULL, '2008-02-17', 'David', 'Pemula', 'Wilson'),
('Member', 9, 'laura.taylor@example.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user8', NULL, '2010-10-19', 'Laura', 'Pemula', 'Taylor'),
('Member', 10, 'daniel.moore@example.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user9', NULL, '2008-09-21', 'Daniel', 'Pemula', 'Moore'),
('Member', 11, 'sophia.anderson@example.com', '$2a$10$/jB7izz8v/7JTkp7hVRkS.yPxWSI3eKi3uw.ztIkbsErHIIqweNCu', 'USER', 'user10', NULL, '2005-03-15', 'Sophia', 'Lanjutan', 'Anderson');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `enrollment`
--
ALTER TABLE `enrollment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpfa8w15g2pd7glgsfcldxf9tb` (`jadwal_id`),
  ADD KEY `FKgpuyid9pbfpxghv9vyhb0ictd` (`user_id`);

--
-- Indeks untuk tabel `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `jadwal`
--
ALTER TABLE `jadwal`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKtg3wghs11fxtccn7p978rubdx` (`instructor_id`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `enrollment`
--
ALTER TABLE `enrollment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT untuk tabel `instructor`
--
ALTER TABLE `instructor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `jadwal`
--
ALTER TABLE `jadwal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `enrollment`
--
ALTER TABLE `enrollment`
  ADD CONSTRAINT `FKgpuyid9pbfpxghv9vyhb0ictd` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKpfa8w15g2pd7glgsfcldxf9tb` FOREIGN KEY (`jadwal_id`) REFERENCES `jadwal` (`id`);

--
-- Ketidakleluasaan untuk tabel `jadwal`
--
ALTER TABLE `jadwal`
  ADD CONSTRAINT `FKtg3wghs11fxtccn7p978rubdx` FOREIGN KEY (`instructor_id`) REFERENCES `instructor` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
