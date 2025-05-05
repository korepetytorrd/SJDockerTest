package org.example.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class KursantDAO {

    // Pobiera wszystkich kursantów z bazy danych
    public List<Kursant> pobierzKursantow() {
        List<Kursant> kursanci = new ArrayList<>();
        String query = """
                SELECT u.id, u.privilege, u.firstName, u.lastName, u.province, u.town, u.postCode, u.street,
                       u.houseNumber, u.flatNumber, u.phone, u.pesel, u.email, u.email_verified, u.password, 
                       u.created_at, k.pkkNumber
                FROM User u
                INNER JOIN Kursant k ON u.id = k.id
                """;
        /**
         * Podwójny znak "" dostepny od wersji java 11 pozawala na
         * wielolinijkowy zapis zapytania bez konieczności używania operatora
         * łączenia znaków +
         */

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                boolean emailVerified = resultSet.getObject("email_verified") != null && resultSet.getBoolean("email_verified");

                Kursant kursant = new Kursant(
                        resultSet.getInt("id"),
                        resultSet.getInt("privilege"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("province"),
                        resultSet.getString("town"),
                        resultSet.getString("postCode"),
                        resultSet.getString("street"),
                        resultSet.getString("houseNumber"),
                        resultSet.getString("flatNumber"),
                        resultSet.getString("phone"),
                        resultSet.getString("pesel"),
                        resultSet.getString("email"),
                        emailVerified,
                        resultSet.getString("password"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getString("pkkNumber")
                );
                kursanci.add(kursant);
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania kursantów: " + e.getMessage());
        }

        return kursanci;
    }

    // Pobiera jednego kursanta po ID
    public Kursant pobierzKursantaPoId(int id) {
        String query = """
                SELECT u.id, u.privilege, u.firstName, u.lastName, u.province, u.town, u.postCode, u.street,
                       u.houseNumber, u.flatNumber, u.phone, u.pesel, u.email, u.email_verified, u.password, 
                       u.created_at, k.pkkNumber
                FROM User u
                INNER JOIN Kursant k ON u.id = k.id
                WHERE u.id = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                boolean emailVerified = resultSet.getObject("email_verified") != null && resultSet.getBoolean("email_verified");

                return new Kursant(
                        resultSet.getInt("id"),
                        resultSet.getInt("privilege"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("province"),
                        resultSet.getString("town"),
                        resultSet.getString("postCode"),
                        resultSet.getString("street"),
                        resultSet.getString("houseNumber"),
                        resultSet.getString("flatNumber"),
                        resultSet.getString("phone"),
                        resultSet.getString("pesel"),
                        resultSet.getString("email"),
                        emailVerified,
                        resultSet.getString("password"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getString("pkkNumber")
                );
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania kursanta o ID " + id + ": " + e.getMessage());
        }

        return null;
    }

    // Dodaje nowego kursanta
    public void dodajKursanta(Kursant kursant) {
        String insertUserQuery = """
                INSERT INTO User (privilege, firstName, lastName, province, town, postCode, street, houseNumber, 
                                  flatNumber, phone, pesel, email, email_verified, password, created_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        String insertKursantQuery = "INSERT INTO Kursant (id, pkkNumber) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement userStmt = connection.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS); PreparedStatement kursantStmt = connection.prepareStatement(insertKursantQuery)) {

                userStmt.setInt(1, kursant.getPrivilege());
                userStmt.setString(2, kursant.getFirstName());
                userStmt.setString(3, kursant.getLastName());
                userStmt.setString(4, kursant.getProvince());
                userStmt.setString(5, kursant.getTown());
                userStmt.setString(6, kursant.getPostCode());
                userStmt.setString(7, kursant.getStreet());
                userStmt.setString(8, kursant.getHouseNumber());
                userStmt.setString(9, kursant.getFlatNumber());
                userStmt.setString(10, kursant.getPhone());
                userStmt.setString(11, kursant.getPesel());
                userStmt.setString(12, kursant.getEmail());
                userStmt.setBoolean(13, kursant.getEmail_ver());
                userStmt.setString(14, kursant.getPassword());
                userStmt.setTimestamp(15, new Timestamp(kursant.getCreated_at().getTime()));

                userStmt.executeUpdate();
                ResultSet generatedKeys = userStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);

                    kursantStmt.setInt(1, userId);
                    kursantStmt.setString(2, kursant.getPkkNumber());
                    kursantStmt.executeUpdate();
                }

                connection.commit();
                System.out.println("Dodano nowego kursanta.");

            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Błąd podczas dodawania kursanta: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Błąd połączenia z bazą: " + e.getMessage());
        }
    }

    // Aktualizuje dane kursanta
    public void aktualizujKursanta(int id, String nowyEmail) {
        String query = "UPDATE User SET email = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, nowyEmail);
            preparedStatement.setInt(2, id);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Zaktualizowano kursanta.");
            } else {
                System.out.println("Nie znaleziono kursanta o ID " + id);
            }

        } catch (SQLException e) {
            System.err.println("Błąd podczas aktualizacji kursanta: " + e.getMessage());
        }
    }

    // Usuwa kursanta
    public void usunKursanta(int id) {
        String deleteKursantQuery = "DELETE FROM Kursant WHERE id = ?";
        String deleteUserQuery = "DELETE FROM User WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement kursantStmt = connection.prepareStatement(deleteKursantQuery); PreparedStatement userStmt = connection.prepareStatement(deleteUserQuery)) {

                kursantStmt.setInt(1, id);
                kursantStmt.executeUpdate();

                userStmt.setInt(1, id);
                userStmt.executeUpdate();

                connection.commit();
                System.out.println("Usunięto kursanta.");

            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Błąd podczas usuwania kursanta: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Błąd połączenia z bazą: " + e.getMessage());
        }
    }
}
