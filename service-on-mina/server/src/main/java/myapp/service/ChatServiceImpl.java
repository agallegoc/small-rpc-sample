package myapp.service;

import java.util.ArrayList;
import java.util.List;
import myapp.User;
import myapp.server.rpc.local.ChatService;

/**
 *
 * @author Vincent Cantin
 */
public class ChatServiceImpl implements ChatService {
  
  int id = 0;

  @Override
  public String setNickname(User caller, String nickname) {
    caller.nickname = nickname + id++;
    return caller.nickname;
  }
  
  @Override
  public List<String> getNicknameList() {
    List<String> nicknames = new ArrayList<String>(User.getUsers().size());
    for (User user : User.getUsers())
      nicknames.add(user.nickname);
    return nicknames;
  }

  @Override
  public void sendMessage(User caller, String message) {
    for (User user : User.getUsers())
      user.chatParticipantProxy.notifyMessage(caller.nickname, message);
  }

  @Override
  public void sendPrivateMessage(User caller, String nickname, String message) {
    for (User user : User.getUsers())
      if (user.nickname.equals(nickname)) {
        user.chatParticipantProxy.notifyMessage(caller.nickname, message);
        break;
      }
  }

}
