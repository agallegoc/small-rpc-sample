package myapp;

import com.lemoulinstudio.small.SmallSession;
import com.lemoulinstudio.small.mina.SmallRpcCodecFactory;
import com.lemoulinstudio.small.mina.SmallRpcHandler;
import com.lemoulinstudio.small.mina.SmallRpcSessionListener;
import com.lemoulinstudio.small.mina.SmallRpcSessionListenerAdapter;
import java.net.InetSocketAddress;
import myapp.server.rpc.Configuration;
import myapp.server.rpc.local.ChatService;
import myapp.server.rpc.remote.ChatParticipant;
import myapp.service.ChatServiceImpl;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 *
 * @author Vincent Cantin
 */
public class App {
  
  public static void main(String[] args) throws Exception {
    final ChatServiceImpl chatService = new ChatServiceImpl();
    
    SmallRpcSessionListener sessionListener = new SmallRpcSessionListenerAdapter() {
      @Override
      public void sessionCreated(SmallSession smallSession) {
        smallSession.bind(chatService, ChatService.class);
        
        ChatParticipant chatParticipantProxy = smallSession.createProxy(ChatParticipant.class);
        
        User user = new User(chatParticipantProxy);
        User.getUsers().add(user);
        smallSession.setCallerObject(user);
      }

      @Override
      public void sessionClosed(SmallSession smallSession) {
        User.getUsers().remove((User) smallSession.getCallerObject());
      }
    };
    
    NioSocketAcceptor acceptor = new NioSocketAcceptor();
    acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new SmallRpcCodecFactory()));
    acceptor.setHandler(new SmallRpcHandler(new Configuration(), sessionListener));
    acceptor.bind(new InetSocketAddress(8080));
  }
  
}
