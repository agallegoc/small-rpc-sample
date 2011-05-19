package myapp.rpc.protocol.client;

import com.lemoulinstudio.small.apt.model.Log;
import com.lemoulinstudio.small.apt.model.Service;
import java.util.List;

/**
 *
 * @author Vincent Cantin
 */
@Log
@Service
public interface ChatParticipant {
  // TODO: to replace by an answer from the chat service.
  public void notifyYourNickname(String nickname);
  public void notifyNicknameList(List<String> nicknameList);
  
  public void notifyMessage(String nickname, String message);
}
