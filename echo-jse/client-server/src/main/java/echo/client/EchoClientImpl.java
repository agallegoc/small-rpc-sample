package echo.client;

import echo.rpc.client.local.EchoClient;

/**
 *
 * @author Vincent Cantin
 */
public class EchoClientImpl implements EchoClient {

  private GameClient gameClient;

  public EchoClientImpl(GameClient gameClient) {
    this.gameClient = gameClient;
  }

  @Override
  public void notifyEchoSaidSomething(String message) {
    gameClient.echoFrame.notifyEchoSaidSomething(message);
  }

}
