package myapp;

import com.lemoulinstudio.small.SmallSession;
import com.lemoulinstudio.small.mina.MessageCodecFactory;
import com.lemoulinstudio.small.mina.SmallIoHandler;
import com.lemoulinstudio.small.mina.SmallIoHandlerListener;
import com.lemoulinstudio.small.mina.SmallIoHandlerListenerAdapter;
import java.net.InetSocketAddress;
import myapp.client.rpc.Configuration;
import myapp.client.rpc.local.ContactListClient;
import myapp.client.rpc.remote.ContactListService;
import myapp.client.rpc.vo.ContactVO;
import myapp.rpc.protocol.vo.ContactStatus;
import myapp.service.ContactListClientImpl;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 *
 * @author Vincent Cantin
 */
public class App {
  
  public static void main(String[] args) throws Exception {
    final ContactListClientImpl contactListClient = new ContactListClientImpl();
    
    SmallIoHandlerListener sessionListener = new SmallIoHandlerListenerAdapter() {
      @Override
      public void sessionCreated(SmallSession smallSession) {
        smallSession.bind(contactListClient, ContactListClient.class);
      }
      
      @Override
      public void sessionOpened(SmallSession smallSession) {
        ContactListService contactListServiceProxy = smallSession.createProxy(ContactListService.class);
        System.out.println("contacts = " + contactListServiceProxy.getContacts());
        contactListServiceProxy.setMyData(new ContactVO(-1, "sdf", ContactStatus.Online));
        System.out.println("contacts = " + contactListServiceProxy.getContacts());
      }
    };
    
    NioSocketConnector connector = new NioSocketConnector();
    connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MessageCodecFactory()));
    connector.setHandler(new SmallIoHandler(new Configuration(), sessionListener));
    connector.connect(new InetSocketAddress(8080));
  }
  
}
