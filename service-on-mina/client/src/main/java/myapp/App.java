package myapp;

import com.lemoulinstudio.small.jse.SmallSession;
import com.lemoulinstudio.small.mina.SmallRpcCodecFactory;
import com.lemoulinstudio.small.mina.SmallRpcHandler;
import java.net.InetSocketAddress;
import myapp.client.rpc.Configuration;
import myapp.client.rpc.remote.ChatService;
import myapp.service.ChatParticipantImpl;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 *
 * @author Vincent Cantin
 */
public class App {
  
  public static void main(String[] args) throws Exception {
    NioSocketConnector connector = new NioSocketConnector();
    
    connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new SmallRpcCodecFactory()));
    
    connector.setHandler(new SmallRpcHandler(new Configuration(), new ChatParticipantImpl()) {
      @Override
      public void sessionOpened(IoSession ioSession) throws Exception {
        SmallSession smallSession = getSmallSession(ioSession);
        
        ChatService chatServiceProxy = smallSession.createProxy(ChatService.class);
        chatServiceProxy.requestNickname("Vincent");
      }
    });
    
    connector.connect(new InetSocketAddress(8080));
  }
  
}
