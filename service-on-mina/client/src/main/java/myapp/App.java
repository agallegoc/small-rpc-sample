package myapp;

import com.lemoulinstudio.small.SmallSession;
import com.lemoulinstudio.small.mina.SmallRpcCodecFactory;
import com.lemoulinstudio.small.mina.SmallRpcHandler;
import com.lemoulinstudio.small.mina.SmallRpcSessionListener;
import com.lemoulinstudio.small.mina.SmallRpcSessionListenerAdapter;
import java.net.InetSocketAddress;
import java.util.List;
import myapp.client.rpc.Configuration;
import myapp.client.rpc.local.ChatParticipant;
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
    final ChatParticipantImpl chatParticipant = new ChatParticipantImpl();
    
    SmallRpcSessionListener sessionListener = new SmallRpcSessionListenerAdapter() {
      @Override
      public void sessionCreated(SmallSession smallSession) {
        smallSession.bind(chatParticipant, ChatParticipant.class);
      }
      
      @Override
      public void sessionOpened(SmallSession smallSession) {
        ChatService chatServiceProxy = smallSession.createProxy(ChatService.class);
        String nickname = chatServiceProxy.setNickname("Alice");
        System.out.println("nickname = " + nickname);
        List<String> nicks = chatServiceProxy.getNicknameList();
        System.out.println("nicks = " + nicks);
      }
    };
    
    NioSocketConnector connector = new NioSocketConnector();
    connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new SmallRpcCodecFactory()));
    connector.setHandler(new SmallRpcHandler(new Configuration(), sessionListener));
    connector.connect(new InetSocketAddress(8080));
  }
  
}
