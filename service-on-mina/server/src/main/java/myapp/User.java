package myapp;

import java.util.HashSet;
import java.util.Set;
import myapp.server.rpc.remote.ChatParticipant;

/**
 *
 * @author Vincent Cantin
 */
public class User {
  
  private static Set<User> users = new HashSet<User>();

  public static Set<User> getUsers() {
    return users;
  }
  
  public final ChatParticipant chatParticipantProxy;
  
  public String nickname;

  public User(ChatParticipant chatParticipantProxy) {
    this.chatParticipantProxy = chatParticipantProxy;
    this.nickname = "";
  }
  
}
