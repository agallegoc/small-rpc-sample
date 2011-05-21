package myapp.rpc.protocol.client;

import com.lemoulinstudio.small.apt.model.Log;
import com.lemoulinstudio.small.apt.model.Service;

/**
 *
 * @author Vincent Cantin
 */
@Log
@Service
public interface ChatParticipant {
  public void notifyMessage(String fromUser, String message);
  public void notifyPrivateMessage(String fromUser, String message);
}
