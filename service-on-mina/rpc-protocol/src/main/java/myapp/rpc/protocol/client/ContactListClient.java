package myapp.rpc.protocol.client;

import com.lemoulinstudio.small.apt.model.Log;
import com.lemoulinstudio.small.apt.model.Service;
import myapp.rpc.protocol.vo.ContactVO;

/**
 *
 * @author Vincent Cantin
 */
@Log
@Service
public interface ContactListClient {
  public void notifyContactUpdated(ContactVO contact);
}
