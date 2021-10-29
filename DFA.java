import java.util.Locale;
import java.util.Set;

public class DFA {
  private Set<INode> nodes;
  private Set<IEdge> edges;
  private INode start;

  public DFA(Set<INode> nodes,INode start,Set<IEdge> edges){
    this.nodes = nodes;
    this.start = start;
    this.edges = edges;
    implement(nodes,edges,start);
  }

  static void implement(Set<INode> nodes, Set<IEdge> edges, INode start){
    for(IEdge edge : edges){
      for(INode node : nodes){
        if(node.getName().equals(edge.getStart().getName())){
          node.addTransition(edge.getEnd(), edge.getChar());
        }
      }
    }
  //  System.out.println(nodes.toString());
  }

  public INode getStart(){
    return this.start;
  }

  public String accept(String word,INode current){
    INode node2 = current;
    StringBuilder zustaende = new StringBuilder();
    zustaende.append(current.getName().toUpperCase(Locale.ROOT));
    char[] input = word.toCharArray();
    for(int i=0; i<input.length;i++){
      INode next = current.getNext(input[i]);
      current = next;
      zustaende.append(next.getName().toUpperCase(Locale.ROOT));
      node2 = next;
    }
    if(node2.isFinal()){
      return zustaende + " akzeptieren";
    }
    return zustaende + " ablehnen";
  }

  public Set<INode> getNodes() {
    return this.nodes;
  }
}
