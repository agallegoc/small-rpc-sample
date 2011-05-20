package myapp.rpc.protocol.server;

import com.lemoulinstudio.small.apt.model.Caller;
import com.lemoulinstudio.small.apt.model.Log;
import com.lemoulinstudio.small.apt.model.Service;
import java.util.List;

/**
 *
 * @author Vincent Cantin
 */
@Log
@Service
public interface ChatService {
  public String setNickname(Caller caller, String nickname);
  public List<String> getNicknameList(Caller caller);
  public void sendMessage(Caller caller, String message);
  public void sendPrivateMessage(Caller caller, String nickname, String message);
}
