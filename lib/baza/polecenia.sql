-- Wyłączanie ograniczeń kluczy obcych
SET REFERENTIAL_INTEGRITY FALSE;

-- Usuwanie tabel
DROP TABLE IF EXISTS JazdyPraktyczne;
DROP TABLE IF EXISTS Kursant;
DROP TABLE IF EXISTS Instruktor;
DROP TABLE IF EXISTS Admin;
DROP TABLE IF EXISTS "User";
-----------------------------------------------------------------------
-- Włączanie ograniczeń kluczy obcych
SET REFERENTIAL_INTEGRITY TRUE;

--przykład robienia savepoint'a i rollback'a
BEGIN TRANSACTION;

-- Tworzenie savepointa
SAVEPOINT start;

-- Wykonanie operacji
INSERT INTO "User" (privilege, firstName, lastName, province, town, postCode, street, houseNumber, flatNumber, phone, pesel, email, email_verified, password, created_at, updated_at)
VALUES (1, 'Jan', 'Testowy', 'Mazowieckie', 'Warszawa', '00-001', 'Krucza', '12', '5', '123456789', '12345678901', 'jan.testowy@example.com', FALSE, 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Cofnięcie do savepointa
ROLLBACK TO SAVEPOINT start;

-- Zakończenie transakcji
COMMIT;
-----------------------------------------------------------------------

-- Tworzenie tabel
CREATE TABLE "User" (
  id INT PRIMARY KEY AUTO_INCREMENT,
  privilege INT,
  firstName VARCHAR(255),
  lastName VARCHAR(255),
  province VARCHAR(255),
  town VARCHAR(255),
  postCode VARCHAR(255),
  street VARCHAR(255),
  houseNumber VARCHAR(255),
  flatNumber VARCHAR(255),
  phone VARCHAR(255),
  pesel VARCHAR(255),
  email VARCHAR(255) UNIQUE,
  email_verified BOOLEAN,
  password VARCHAR(255),
  created_at DATETIME,
  updated_at DATETIME
);

CREATE TABLE Kursant (
  id INT PRIMARY KEY,
  pkkNumber VARCHAR(255),
  FOREIGN KEY (id) REFERENCES "User" (id)
);

CREATE TABLE Instruktor (
  id INT PRIMARY KEY,
  licenseNumber VARCHAR(255),
  FOREIGN KEY (id) REFERENCES "User" (id)
);

CREATE TABLE Admin (
  id INT PRIMARY KEY,
  nickname VARCHAR(255),
  FOREIGN KEY (id) REFERENCES "User" (id)
);

CREATE TABLE JazdyPraktyczne (
  id INT PRIMARY KEY AUTO_INCREMENT,
  kursant_id INT,
  instruktor_id INT,
  start_time DATETIME,
  end_time DATETIME,
  is_free BOOLEAN,
  FOREIGN KEY (kursant_id) REFERENCES Kursant (id),
  FOREIGN KEY (instruktor_id) REFERENCES Instruktor (id)
);

-----------------------------------------------------------------
-- Dodawanie użytkowników
INSERT INTO "User" (privilege, firstName, lastName, province, town, postCode, street, houseNumber, flatNumber, phone, pesel, email, email_verified, password, created_at, updated_at)
VALUES 
(1, 'Jan', 'Kowalski', 'Mazowieckie', 'Warszawa', '00-001', 'Krucza', '45', '12', '123456789', '89010112345', 'jan.kowalski@example.com', FALSE, 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Anna', 'Nowak', 'Pomorskie', 'Gdańsk', '80-001', 'Długa', '10', '8', '987654321', '91020223456', 'anna.nowak@example.com', FALSE, 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Piotr', 'Zielinski', 'Małopolskie', 'Kraków', '30-001', 'Karmelicka', '5', '20', '564738291', '92030334567', 'piotr.zielinski@example.com', FALSE, 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Katarzyna', 'Wiśniewska', 'Mazowieckie', 'Warszawa', '00-002', 'Marszałkowska', '33', '5', '456789123', '72040512345', 'katarzyna.wisniewska@example.com', FALSE, 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Marek', 'Lewandowski', 'Pomorskie', 'Gdynia', '81-001', 'Świętojańska', '50', '3', '678912345', '68060123456', 'marek.lewandowski@example.com', FALSE, 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Zofia', 'Szymańska', 'Małopolskie', 'Kraków', '30-002', 'Grodzka', '8', '2', '432198765', '75080987654', 'zofia.szymanska@example.com', FALSE, 'password123', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Powiązanie użytkowników z tabelą Kursant
INSERT INTO Kursant (id, pkkNumber)
VALUES 
(1, 'PKK12345678901234567'),
(2, 'PKK98765432109876543');

-- Powiązanie użytkowników z tabelą Instruktor
INSERT INTO Instruktor (id, licenseNumber)
VALUES 
(3, 'LIC123456789'),
(4, 'LIC987654321');

-- Powiązanie użytkowników z tabelą Admin
INSERT INTO Admin (id, nickname)
VALUES 
(5, 'ADMIN001'),
(6, 'ADMIN002');

-- Przykładowe jazdy praktyczne
INSERT INTO JazdyPraktyczne (kursant_id, instruktor_id, start_time, end_time, is_free)
VALUES 
(1, 4, '2025-01-20 09:00:00', '2025-01-20 10:30:00', FALSE),
(2, 5, '2025-01-21 14:00:00', '2025-01-21 15:30:00', TRUE),
(3, 6, '2025-01-22 16:00:00', '2025-01-22 17:30:00', TRUE);
