package game;

import com.lemoulinstudio.small.common.MessageListener;
import game.client.GameClient;
import game.server.ServerUserSession;
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
    gameClient.smallSession.setMessageListener(new MessageListener() {
      @Override
      public void notifyMessage(ByteBuffer binaryMessage) {
        serverUserSession.smallSession.decodeAndExecute(binaryMessage);
      }
    });

    // Connection: server output -> client input.
    serverUserSession.smallSession.setMessageListener(new MessageListener() {
      @Override
      public void notifyMessage(ByteBuffer binaryMessage) {
        gameClient.smallSession.decodeAndExecute(binaryMessage);
      }
    });

    // Let the end user play with the UI of the client.
    gameClient.echoFrame.setVisible(true);
  }

}
