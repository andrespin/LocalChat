package chat.auth;



import chat.User;
import chat.MyServer;
import chat.database.DataBaseManager;

import java.sql.SQLException;
import java.util.List;

public class BaseAuthService implements chat.auth.AuthService {

 //   private DataBaseManager dataBaseManager;

    MyServer myServer;

    private static final List<User> clients = List.of(
            new User("user1", "1111", "Борис_Николаевич"),
            new User("user2", "2222", "Мартин_Некотов"),
            new User("user3", "3333", "Гендальф_Серый")
    );

    public BaseAuthService(MyServer myServer){
        this.myServer = myServer;
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
        myServer.getServerLogger().writeInfoLog("Сервис аутентификации запущен");
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
    //    dataBaseProcess();
        for (User client : clients) {
            if(client.getLogin().equals(login) && client.getPassword().equals(password)) {
                return client.getUsername();
            }
        }
        return null;
    }

    @Override
    public void close() {
        System.out.println("Сервис аутентификации завершен");
        myServer.getServerLogger().writeInfoLog("Сервис аутентификации завершен");
    }

    private void dataBaseProcess(){
        try {
            DataBaseManager dataBaseManager = new DataBaseManager();
            dataBaseManager.connection();
            dataBaseManager.createTable("clients", "client_id",
                    "client_name", "client_login", "client_nickname");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
