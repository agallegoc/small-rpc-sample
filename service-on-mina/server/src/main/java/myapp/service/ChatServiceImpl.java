package myapp.service;

import java.util.Arrays;
import java.util.List;
import myapp.User;
import myapp.server.rpc.local.ChatService;

/**
 *
 * @author Vincent Cantin
 */
public class ChatServiceImpl implements ChatService {

  @Override
  public String setNickname(User caller, String nickname) {
    return "user-" + nickname;
  }
  
  @Override
  public List<String> getNicknameList(User caller) {
    return Arrays.asList("Alice", "Bob", "Cindy", "Dan");
  }

  @Override
  public void sendMessage(User caller, String message) {
  }

  @Override
  public void sendPrivateMessage(User caller, String nickname, String message) {
  }

}
