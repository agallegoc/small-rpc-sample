package echo;

import com.lemoulinstudio.small.jse.MessageSender;
import echo.client.GameClient;
import echo.server.ServerUserSession;
import java.nio.ByteBuffer;

/**
 *
 * @author Vincent Cantin
 */
public class Main {

  public static void main(String[] args) {
    // Create a client.
    final GameClient gameClient = new GameClient();

    // Simulate a connected server user session.
    final ServerUserSession serverUserSession = new ServerUserSession();

    /*
     * Simulated a binary message transmission between the client and
     * the server user session: We just connect the output of the client
     * SmallSession to the input of the server SmallSession and vice-versa.
     */

    // Connection: client output -> server input.
    gameClient.smallSession.setMessageSender(new MessageSender() {
      @Override
      public void sendMessage(ByteBuffer binaryMessage) {
        // Direct delivery to the server side, simulated socket.
        try {serverUserSession.smallSession.decodeAndExecute(binaryMessage);}
        catch (Exception e) {}
      }
    });

    // Connection: server output -> client input.
    serverUserSession.smallSession.setMessageSender(new MessageSender() {
      @Override
      public void sendMessage(ByteBuffer binaryMessage) {
        // Direct delivery to the server side, simulated socket.
        try {gameClient.smallSession.decodeAndExecute(binaryMessage);}
        catch (Exception e) {}
      }
    });

    // Let the end user play with the UI of the client.
    gameClient.echoFrame.setVisible(true);
  }

}
