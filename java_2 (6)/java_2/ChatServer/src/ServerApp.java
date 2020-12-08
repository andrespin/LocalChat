import chat.MyServer;

/*
Добавить отключение неавторизованных пользователей по таймауту
(120 сек. ждем после подключения клиента.
Если он не авторизовался за это время, закрываем соединение).
*/


import java.io.IOException;

   class ServerApp {

    private static final int DEFAULT_PORT = 8189;

    public static void main(String[] args) {

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

}