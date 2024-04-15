import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StoreDepartment {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3306/departments";
        String dbUsername = "userdb";
        String dbPassword = "1234";

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            Statement dbStatement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS department (id INT PRIMARY KEY, name VARCHAR(100))";
            dbStatement.executeUpdate(createTableQuery);

            insertDepartmentRecord(dbStatement, new Department(1, "CSE"));
            insertDepartmentRecord(dbStatement, new Department(2, "AIDS"));
            insertDepartmentRecord(dbStatement, new Department(3, "IT"));

            System.out.println("Department records insertion successful.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertDepartmentRecord(Statement dbStatement, Department department) throws SQLException {
        String insertQuery = String.format("INSERT INTO department VALUES (%d, '%s')",
                department.getId(), department.getName());
        dbStatement.executeUpdate(insertQuery);
    }
}
