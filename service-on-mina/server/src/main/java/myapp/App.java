package myapp;

import com.lemoulinstudio.small.SmallSession;
import com.lemoulinstudio.small.mina.MessageCodecFactory;
import com.lemoulinstudio.small.mina.SmallIoHandler;
import com.lemoulinstudio.small.mina.SmallIoHandlerListener;
import com.lemoulinstudio.small.mina.SmallIoHandlerListenerAdapter;
import java.net.InetSocketAddress;
import myapp.server.rpc.Configuration;
import myapp.server.rpc.local.ContactListService;
import myapp.server.rpc.remote.ContactListClient;
import myapp.service.ContactListServiceImpl;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 *
 * @author Vincent Cantin
 */
public class App {
  
  public static void main(String[] args) throws Exception {
    final ContactListServiceImpl contactListService = new ContactListServiceImpl();
    
    SmallIoHandlerListener sessionListener = new SmallIoHandlerListenerAdapter() {
      @Override
      public void sessionCreated(SmallSession smallSession) {
        smallSession.bind(contactListService, ContactListService.class);
        
        ContactListClient contactListClientProxy = smallSession.createProxy(ContactListClient.class);
        
        User user = new User(contactListClientProxy);
        User.addUser(user);
        smallSession.setCallerObject(user);
      }

      @Override
      public void sessionClosed(SmallSession smallSession) {
        User.getUsers().remove((User) smallSession.getCallerObject());
      }
    };
    
    NioSocketAcceptor acceptor = new NioSocketAcceptor();
    acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory()));
    acceptor.setHandler(new SmallIoHandler(new Configuration(), sessionListener));
    acceptor.bind(new InetSocketAddress(8080));
  }
  
}
