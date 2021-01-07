import chat.MyServer;
import chat.database.DataBaseManager;
import chat.logging.ServerLogger;

/*
1. Добавить на серверную сторону чата логирование,
с выводом информации о действиях на сервере(запущен, произошла ошибка,
клиент подключился, клиент прислал сообщение/команду).
4. *Добавить на серверную сторону сетевого чата логирование событий.
 */

import java.io.IOException;
import java.sql.SQLException;

class ServerApp {

    private static final int DEFAULT_PORT = 8189;
    // Если будет вот этот path, то вылетает ошибка: java.nio.file.NoSuchFileException
  //  private static final String path = "src/main/resources/MyLogFile.log";

  //  private static final String path = "MyLogFile.log";


    private static final String path =  "C:/Учёба/GeekBrains/JavaCore. Продвинутый уровень/LocalChat/LocChatLoggin/ChatServer/src/main/resources/MyLogFile.log";
    private static ServerLogger serverLogger = new ServerLogger(path);
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
            getServerLogger().writeSevereLog("Ошибка");
            System.exit(1);
        }
    }

    public static ServerLogger getServerLogger() {
        return serverLogger;
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
