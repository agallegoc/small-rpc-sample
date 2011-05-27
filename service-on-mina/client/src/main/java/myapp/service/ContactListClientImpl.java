package myapp.service;

import myapp.client.rpc.local.ContactListClient;
import myapp.client.rpc.vo.ContactVO;

/**
 *
 * @author Vincent Cantin
 */
public class ContactListClientImpl implements ContactListClient {

  @Override
  public void notifyContactUpdated(ContactVO contact) {
  }
  
}
