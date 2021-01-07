package chat.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class ServerLogger {

      java.util.logging.Logger logger = java.util.logging.Logger.getLogger("");
      FileHandler fh;

      public ServerLogger(String path){
          try {
              fh = new FileHandler(path);
          } catch (IOException e) {
              e.printStackTrace();
          }

          logger.addHandler(fh);
          SimpleFormatter formatter = new SimpleFormatter();
          fh.setFormatter(formatter);
      }

      public void writeInfoLog(String log){
          logger.info(log);
      }

      public void writeSevereLog(String log){
        logger.severe(log);
      }

      public void writeWarningLog(String log){
        logger.warning(log);
    }







}
