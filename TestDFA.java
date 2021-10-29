// javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar TestDFA.java
// java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar junit.textui.TestRunner TestDFA

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import junit.framework.TestCase;
import java.util.Set;
import java.util.HashSet;


public class TestDFA extends TestCase {

    @Test
    public void testAddOneTransitions() {
        Set<INode> nodes = new HashSet<INode>();
        Node b = new Node("B", false);
        Node c = new Node("C", true);
        Node a = new Node("A", false);
        Node e = new Node("E",false);

        nodes.add(b);
        nodes.add(c);
        nodes.add(a);
        nodes.add(e);
        
        Set<IEdge> edges = new HashSet<IEdge>();
        edges.add(new Edge(c, '0', c));
        edges.add(new Edge(c, '1', b));
        edges.add(new Edge(c, '1', b));
        edges.add(new Edge(b, '1', a));
        edges.add(new Edge(b, '0', e));


        DFA dfa = new DFA(nodes, c, edges);
        
        assertEquals("C", c.getNext('0').getName());
        assertEquals("B", c.getNext('1').getName());
        assertEquals("A", b.getNext('1').getName());
        assertEquals("E", b.getNext('0').getName());
    }
    
    @Test
    public void testAddTwoTransitions() {
        Set<INode> nodes = new HashSet<INode>();
        Node b = new Node("B", false);
        Node c = new Node("C", true);
        nodes.add(b);
        nodes.add(c);
        
        Set<IEdge> edges = new HashSet<IEdge>();
        edges.add(new Edge(c, '0', c));
        DFA dfa = new DFA(nodes, c, edges);
        
        assertEquals("C", c.getNext('0').getName());
        assertEquals(null, c.getNext('1'));
    }
    
    @Test
    public void test01() {
        Set<INode> nodes = new HashSet<INode>();
        Node a = new Node("A", false);
        Node b = new Node("B", false);
        Node c = new Node("C", true);
        Node d = new Node("D", false);
        Node e = new Node("E", false);
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        
        Set<IEdge> edges = new HashSet<IEdge>();
        edges.add(new Edge(b, '0', e));
        edges.add(new Edge(b, '1', a));
        edges.add(new Edge(c, '0', c));
        edges.add(new Edge(c, '1', b));

        edges.add(new Edge(a, '0', b));
        edges.add(new Edge(a, '1', e));
        edges.add(new Edge(d, '0', a));
        edges.add(new Edge(d, '1', d));
        edges.add(new Edge(e, '0', d));
        edges.add(new Edge(e, '1', c));
        DFA dfa = new DFA(nodes, c, edges);
        //System.out.println(ViewUtil.toDot(dfa));
    //    assertEquals("CBEDDA ablehnen", dfa.accept("10010", dfa.getStart()));
     //   assertEquals("CBABEC akzeptieren", dfa.accept("11001", dfa.getStart()));
      //  assertEquals("CB ablehnen", dfa.accept( "1", dfa.getStart()));
        assertEquals("CCCCCCC akzeptieren", dfa.accept( "000000", dfa.getStart()));
     //   assertEquals("CBE ablehnen", dfa.accept( "10", dfa.getStart()));
        
    //    assertEquals("CBECB ablehnen", dfa.accept( "1011", dfa.getStart()));
        
    //    assertEquals("CBECB ablehnen", dfa.accept( "1011", dfa.getStart()));
      //  assertEquals("CBEDDABECB ablehnen", dfa.accept("100100011", dfa.getStart()));
        
        assertEquals("CBABEC akzeptieren", dfa.accept( "11001", c));
        assertEquals("CBABECC akzeptieren", dfa.accept("110010", dfa.getStart()));
        assertEquals("CBABECCC akzeptieren", dfa.accept("1100100", dfa.getStart()));
        assertEquals("CBABECCCC akzeptieren", dfa.accept("11001000", dfa.getStart()));
        assertEquals("CBEC akzeptieren", dfa.accept("101", dfa.getStart()));
        assertEquals("CBEDDABEC akzeptieren", dfa.accept("10010001", dfa.getStart()));
        assertEquals("CBEDDDDABEC akzeptieren", dfa.accept("1001110001", dfa.getStart()));
        assertEquals("CBEDDDDDDABEC akzeptieren", dfa.accept("100111110001", dfa.getStart()));
    }
    
    @Test
    public void test02() {
        Set<INode> nodes = new HashSet<INode>();
        Node a = new Node("A", false);
        Node b = new Node("B", true);
        Node c = new Node("C", false);
        Node d = new Node("D", true);
        Node e = new Node("E", false);
        nodes.add(a);
        nodes.add(b);
        nodes.add(c);
        nodes.add(d);
        nodes.add(e);
        
        Set<IEdge> edges = new HashSet<IEdge>();
        edges.add(new Edge(a, 'a', b));
        edges.add(new Edge(a, 'b', c));
        edges.add(new Edge(b, 'a', d));
        edges.add(new Edge(b, 'b', e));
        edges.add(new Edge(c, 'b', b));
        edges.add(new Edge(d, 'b', b));
        edges.add(new Edge(e, 'a', d));
        
        DFA dfa = new DFA(nodes, a, edges);
        //System.out.println(ViewUtil.toDot(dfa));
        assertEquals("AB akzeptieren", dfa.accept("a", dfa.getStart()));
        assertEquals("ABD akzeptieren", dfa.accept("aa", dfa.getStart()));
        assertEquals("ACBDB akzeptieren", dfa.accept("bbab", dfa.getStart()));
        
        assertEquals("ABE ablehnen", dfa.accept("ab", dfa.getStart()));
        assertEquals("ABDBE ablehnen", dfa.accept("aabb", dfa.getStart()));
        assertEquals("ACBE ablehnen", dfa.accept("bbb", dfa.getStart()));
    }

}