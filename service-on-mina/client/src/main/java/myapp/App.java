package myapp;

import com.lemoulinstudio.small.SmallSession;
import com.lemoulinstudio.small.mina.SmallRpcCodecFactory;
import com.lemoulinstudio.small.mina.SmallRpcHandler;
import com.lemoulinstudio.small.mina.SmallRpcSessionListener;
import java.net.InetSocketAddress;
import myapp.client.rpc.Configuration;
import myapp.client.rpc.remote.ChatService;
import myapp.service.ChatParticipantImpl;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 *
 * @author Vincent Cantin
 */
public class App {
  
  public static void main(String[] args) throws Exception {
    SmallRpcSessionListener sessionListener = new SmallRpcSessionListener() {

      @Override
      public void sessionCreated(SmallSession smallSession) {
      }

      @Override
      public void sessionOpened(SmallSession smallSession) {
        ChatService chatServiceProxy = smallSession.createProxy(ChatService.class);
        String nickname = chatServiceProxy.setNickname("Vincent");
        System.out.println("nickname = " + nickname);
      }

      @Override
      public void sessionClosed(SmallSession smallSession) {
      }
    };
    
    
    NioSocketConnector connector = new NioSocketConnector();
    connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new SmallRpcCodecFactory()));
    connector.setHandler(new SmallRpcHandler(new Configuration(), sessionListener, new ChatParticipantImpl()));
    connector.connect(new InetSocketAddress(8080));
  }
  
}
