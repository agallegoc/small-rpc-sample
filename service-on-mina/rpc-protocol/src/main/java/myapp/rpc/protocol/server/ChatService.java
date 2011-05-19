package myapp.rpc.protocol.server;

import com.lemoulinstudio.small.apt.model.Caller;
import com.lemoulinstudio.small.apt.model.Log;
import com.lemoulinstudio.small.apt.model.Service;

/**
 *
 * @author Vincent Cantin
 */
@Log
@Service
public interface ChatService {
  public void requestNickname(Caller caller, String nickname);
  public void requestNicknameList(Caller caller);
  public void sendMessage(Caller caller, String message);
  public void sendPrivateMessage(Caller caller, String nickname, String message);
}
