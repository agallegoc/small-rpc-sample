package game.networkmodel;

import com.lemoulinstudio.small.apt.model.HostKind;
import com.lemoulinstudio.small.apt.model.Log;
import com.lemoulinstudio.small.apt.model.NetworkInterface;
import com.lemoulinstudio.small.apt.model.Singleton;

/**
 *
 * @author Vincent Cantin
 */
@Log
@Singleton
@NetworkInterface(HostKind.Server)
public interface EchoServer {
  public void requestSaySomethingToEcho(String message);
}
