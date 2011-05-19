package myapp.service;

import myapp.User;
import myapp.server.rpc.local.ChatService;

/**
 *
 * @author Vincent Cantin
 */
public class ChatServiceImpl implements ChatService {

  @Override
  public void requestNickname(User caller, String nickname) {
  }

  @Override
  public void requestNicknameList(User caller) {
  }

  @Override
  public void sendMessage(User caller, String message) {
  }

  @Override
  public void sendPrivateMessage(User caller, String nickname, String message) {
  }
  
}
