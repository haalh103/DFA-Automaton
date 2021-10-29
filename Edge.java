public class Edge implements IEdge{

  private final INode start;
  private final INode end;
  private final char c;

  public Edge(INode start,char c,INode end){
    this.start = start;
    this.end = end;
    this.c = c;
  }

  @Override
  public INode getStart() {
    return this.start;
  }

  @Override
  public INode getEnd() {
    return this.end;
  }

  @Override
  public char getChar() {
    return this.c;
  }
}
