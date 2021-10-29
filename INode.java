public interface INode {
        public void addTransition(INode dest, char c);
        public INode getNext(char c);
        public String getName();
        public boolean isFinal();
        Iterable<? extends IEdge> getEdges();
}
