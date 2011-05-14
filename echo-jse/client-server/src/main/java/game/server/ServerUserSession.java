package game.server;

import com.lemoulinstudio.small.jse.SmallSession;
import com.lemoulinstudio.small.jse.SmallSessionImpl;
import game.network.server.Configuration;
import game.network.server.Root;
import game.network.server.local.EchoServer;

/**
 * This class is for 'per-user' connections on the server side.
 *
 * @author Vincent Cantin
 */
public class ServerUserSession {

  public SmallSession smallSession;
  public EchoServer echoServer;
  public Root rootProxy;

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
    rootProxy = smallSession.createProxy(Root.class);
  }

}
