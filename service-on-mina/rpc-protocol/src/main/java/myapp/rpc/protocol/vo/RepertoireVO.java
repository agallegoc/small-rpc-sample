package myapp.rpc.protocol.vo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author vincent
 */
public class RepertoireVO {
  public ContactVO contact;
  public ContactVO[] contacts;
  public ContactVO[][] contactss;
  public List<ContactVO> a;
  public Set<ContactVO> b;
  public Map<ContactStatus, ContactVO> c;
  public Collection<ContactVO> d;
  public ContactStatus e;
}
