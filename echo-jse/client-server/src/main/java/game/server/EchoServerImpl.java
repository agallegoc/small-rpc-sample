package game.server;

import game.network.server.local.EchoServer;

/**
 *
 * @author Vincent Cantin
 */
public class EchoServerImpl implements EchoServer {

  private ServerUserSession userSession;

  public EchoServerImpl(ServerUserSession userSession) {
    this.userSession = userSession;
  }

  @Override
  public void requestSaySomethingToEcho(String message) {
    // We just return the message to the user connected to this session.
    userSession.echoClientProxy.notifyEchoSaidSomething(message);
  }

}
