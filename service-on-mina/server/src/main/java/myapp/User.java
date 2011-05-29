package myapp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import myapp.rpc.protocol.vo.ContactStatus;
import myapp.server.rpc.remote.ContactListClient;

/**
 *
 * @author Vincent Cantin
 */
public class User {
  
  private static Map<Integer, User> idToUser = new HashMap<Integer, User>();
  
  public static Collection<User> getUsers() {
    return idToUser.values();
  }

  public static User getUser(int id) {
    return idToUser.get(id);
  }
  
  public static void addUser(User user) {
    idToUser.put(user.id, user);
  }
  
  public final ContactListClient contactListClientProxy;
  public final int id;
  public String name;
  public ContactStatus status;

  public User(ContactListClient contactListClientProxy) {
    this.contactListClientProxy = contactListClientProxy;
    id = getUsers().size();
  }

}
