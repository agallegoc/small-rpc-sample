package myapp;

import com.lemoulinstudio.small.mina.SmallRpcCodecFactory;
import com.lemoulinstudio.small.mina.SmallRpcHandler;
import java.net.InetSocketAddress;
import myapp.server.rpc.Configuration;
import myapp.service.ChatServiceImpl;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

/**
 *
 * @author Vincent Cantin
 */
public class App {
  
  public static void main(String[] args) throws Exception {
    NioSocketAcceptor acceptor = new NioSocketAcceptor();
    acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new SmallRpcCodecFactory()));
    acceptor.setHandler(new SmallRpcHandler(new Configuration(), new ChatServiceImpl()));
    acceptor.bind(new InetSocketAddress(8080));
  }
  
}
