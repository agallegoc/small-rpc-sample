package echo.server;

import com.lemoulinstudio.small.SmallSession;
import com.lemoulinstudio.small.SmallSessionImpl;
import echo.rpc.server.Configuration;
import echo.rpc.server.local.EchoServer;
import echo.rpc.server.remote.EchoClient;

/**
 * This class is a 'per-user' session on the server side.
 *
 * @author Vincent Cantin
 */
public class ServerUserSession {

  public SmallSession smallSession;
  public EchoServer echoServer;
  public EchoClient echoClientProxy;

  public ServerUserSession() {
    // Create the small session.
    smallSession = new SmallSessionImpl(new Configuration());

    // Create the object the client will talk to.
    echoServer = new EchoServerImpl(this);

    // Bind some local objects to the small session, this exposes them to the remote host.
    smallSession.bind(echoServer, EchoServer.class);

    /*
     * The setup here is a little special: it was built for scalability, so
     * here we only use 1 proxy per client and the call style is like C.
     * This feature will become optional in the next small release.
     */
    // Create the proxy to talk to the remote host.
    echoClientProxy = smallSession.createProxy(EchoClient.class);
  }

}
