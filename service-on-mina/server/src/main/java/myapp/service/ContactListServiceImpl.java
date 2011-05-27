package myapp.service;

import com.lemoulinstudio.small.SmallSessionImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import myapp.User;
import myapp.server.rpc.local.ContactListService;
import myapp.server.rpc.vo.ContactVO;

/**
 *
 * @author Vincent Cantin
 */
public class ContactListServiceImpl implements ContactListService {

  @Override
  public void setMyData(ContactVO contact) {
    User callingUser = (User) SmallSessionImpl.getCurrentSession().getCallerObject();
    callingUser.name = contact.name;
    callingUser.status = contact.status;
    
    contact.id = callingUser.id;
    for (User user : User.getUsers())
      user.contactListClientProxy.notifyContactUpdated(contact);
  }

  @Override
  public Collection<ContactVO> getContacts() {
    List<ContactVO> vos = new ArrayList<ContactVO>();
    
    for (User user : User.getUsers()) {
      ContactVO vo = new ContactVO();
      vo.id = user.id;
      vo.name = user.name;
      vo.status = user.status;
      vos.add(vo);
    }
    
    return vos;
  }
  
}
