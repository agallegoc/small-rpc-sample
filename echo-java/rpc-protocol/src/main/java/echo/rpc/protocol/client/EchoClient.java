package echo.rpc.protocol.client;

import com.lemoulinstudio.small.apt.model.Log;
import com.lemoulinstudio.small.apt.model.Service;

/**
 *
 * @author Vincent Cantin
 */
@Log
@Service
public interface EchoClient {
  public void notifyEchoSaidSomething(String message);
}
