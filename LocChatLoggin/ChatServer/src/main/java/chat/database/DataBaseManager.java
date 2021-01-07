package chat.database;

import java.sql.*;
import java.util.Random;

public class DataBaseManager {

    private static Connection connection;
    private static Statement stmt;
    private static ResultSet rs;

    public void connection() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:usersDataBase");
    //    connection = DriverManager.getConnection("jdbc:sqlite:src/main/resourses/usersDataBase");
        // jdbc:sqlite:src/main/resourses/usersDataBase
        stmt = connection.createStatement();
        System.out.println("connection()");
    }


    public void createTable(String tableName ,String id, String login, String password, String nickname) throws SQLException {
        String table = String.format( new String("CREATE TABLE IF NOT EXISTS %s("
                + "%s INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "%s TEXT NOT NULL,"
                + "%s TEXT NOT NULL,"
                + "%s TEXT NOT NULL"
                + ")"), tableName, id, login, password, nickname);
        stmt.execute(table);
        System.out.println("createTable()");
    }

    public void clearTable(String tableName ) throws SQLException {
        if(findTable(tableName)){
            stmt.execute("DELETE FROM \n" +
                    tableName);
            System.out.println("Таблица " + tableName + " очищена");
        } else{
            System.out.println("Таблица " + tableName + " не найдена");
        }
    }

    public void insertTable(String tableName ) throws SQLException {
        if (findTable(tableName)){
            connection.setAutoCommit(false);
            String insertUSer1InTable ="INSERT INTO \n"
                    + tableName + "\n (client_name, client_login, client_nickname)" +
                    " VALUES ('user1', '1111', 'Борис_Николаевич')";
            String insertUSer2InTable ="INSERT INTO \n"
                    + tableName + "\n (client_name, client_login, client_nickname)" +
                    " VALUES ('user2', '2222', 'Мартин_Некотов')";
            String insertUSer3InTable ="INSERT INTO \n"
                    + tableName + "\n (client_name, client_login, client_nickname)" +
                    " VALUES ('user3', '3333', 'Гендальф_Серый')";
            stmt.addBatch(insertUSer1InTable);
            stmt.addBatch(insertUSer2InTable);
            stmt.addBatch(insertUSer3InTable);
            stmt.executeBatch();
            connection.setAutoCommit(true);
            System.out.println("Таблица " + tableName + " заполнена");
        } else{
            System.out.println("Таблица " + tableName + " не найдена");
        }
    }

    private boolean findUser(String login, String password) throws SQLException {
        rs = stmt.executeQuery(String.format("SELECT password FROM " +
                "clients WHERE login = '%s'", login));
        String passDB = rs.getString("password");
        boolean isExhists = false;
        if (passDB != null) {
            isExhists = (passDB.equals(password)) ? true : false;
        }
        return isExhists;
    }

    public void printUserFound(String login, String password) throws SQLException {
        System.out.println("User exhists " + findUser(login, password));

    }

    public void printTable(String tableName) throws SQLException {
        System.out.println("Table exists " + tableName + " " + findTable(tableName));
    }

    private boolean findTable(String tableName ) throws SQLException {
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
            if (tableName.equals(rs.getString(3))){
                return true;
            }
        }
        return false;
    }

}
