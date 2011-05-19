package myapp.service;

import java.util.List;
import myapp.User;
import myapp.client.rpc.local.ChatParticipant;

/**
 *
 * @author Vincent Cantin
 */
public class ChatParticipantImpl implements ChatParticipant {

  @Override
  public void notifyYourNickname(String nickname) {
  }

  @Override
  public void notifyNicknameList(List<String> nicknameList) {
  }

  @Override
  public void notifyMessage(String nickname, String message) {
  }
  
}
