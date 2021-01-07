import chat.MyServer;
import chat.database.DataBaseManager;

/*
1. Добавить в сетевой чат авторизацию через базу данных SQLite.
2.*Добавить в сетевой чат возможность смены ника.
 */

import java.io.IOException;
import java.sql.SQLException;

class ServerApp {

    private static final int DEFAULT_PORT = 8189;

    public static void main(String[] args) {
     //   dataBaseProcess();
        int port = DEFAULT_PORT;

        if (args.length != 0) {
            port = Integer.parseInt(args[0]);
        }
        try {
            new MyServer(port).start();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка!");
            System.exit(1);
        }
    }

    private static void dataBaseProcess(){
        try {
            DataBaseManager dataBaseManager = new DataBaseManager();
            dataBaseManager.connection();
            dataBaseManager.createTable("clients", "client_id",
                    "client_name", "client_login", "client_nickname");
            dataBaseManager.printTable("goods");
            dataBaseManager.printTable("clients");
            dataBaseManager.clearTable("clients");
            dataBaseManager.insertTable("clients");
            dataBaseManager.printUserFound("user1", "1111");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
