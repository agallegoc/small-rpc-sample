package echo.client;

import com.lemoulinstudio.small.jse.SmallSession;
import com.lemoulinstudio.small.jse.SmallSessionImpl;
import echo.client.ui.EchoFrame;
import echo.rpc.client.Configuration;
import echo.rpc.client.local.EchoClient;
import echo.rpc.client.remote.EchoServer;

/**
 *
 * @author Vincent Cantin
 */
public class GameClient {

  public SmallSession smallSession;
  public EchoClient echoClient;
  public EchoServer echoServerProxy;

  public EchoFrame echoFrame;
  
  public GameClient() {
    // Create the Small session.
    smallSession = new SmallSessionImpl(new Configuration());

    // Create the object the server will talk to.
    echoClient = new EchoClientImpl(this);
    
    // Bind some local objects to the small session, this exposes them to the remote host.
    smallSession.bind(echoClient, EchoClient.class);

    // Create the proxy to talk to the remote host.
    echoServerProxy = smallSession.createProxy(EchoServer.class);

    // Create the UI.
    echoFrame = new EchoFrame(this);
  }

}
