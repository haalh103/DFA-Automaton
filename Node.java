import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Node implements INode{

  String name;
  boolean isFinal;
  private Map<INode, Character> transitions;

  public Node(String name , boolean isFinal){
    this.name=name;
    this.isFinal=isFinal;
    this.transitions = new HashMap();
  }

  @Override
  public void addTransition(INode dest, char c) {
    this.transitions.put(dest,c);
  //  System.out.println(transitions.keySet().toArray().length + name);
  }
  public Map<INode, Character> getTransitions(){
    return transitions;

  }

  @Override
  public INode getNext(char c) throws NullPointerException{
    for (Map.Entry<INode, Character> pair : transitions.entrySet()) {
      if(pair.getValue().equals(c)){
        return pair.getKey();
      }
    }
    return null;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public boolean isFinal() {
    return this.isFinal;
  }

  @Override
  public Iterable<? extends IEdge> getEdges() {
    return null;
  }
}