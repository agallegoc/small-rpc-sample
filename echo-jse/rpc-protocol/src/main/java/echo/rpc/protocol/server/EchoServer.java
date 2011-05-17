package echo.rpc.protocol.server;

import com.lemoulinstudio.small.apt.model.Log;
import com.lemoulinstudio.small.apt.model.Service;

/**
 *
 * @author Vincent Cantin
 */
@Log
@Service
public interface EchoServer {
  public void requestSaySomethingToEcho(String message);
}
