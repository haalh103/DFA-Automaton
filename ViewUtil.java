import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.AbstractMap.SimpleEntry;

public class ViewUtil {
    static int i=0;
    
    public static String toDot(DFA automaton) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("digraph G {\n");
        
        HashMap<INode,Integer> ids = new HashMap<INode,Integer>();
        
        for(INode node: automaton.getNodes())
            ids.put(node,i++);
        
        
        for(INode node: automaton.getNodes())
        {
            sb.append("node");
            sb.append(ids.get(node));
            sb.append(" [label=\"");
            sb.append(node.getName());
            sb.append("\"");
            if(node.isFinal())
                sb.append(" shape=doublecircle");
            sb.append("]\n");
            if(automaton.getStart()==node)
            {
                sb.append("start [style=invisible]\n");
                sb.append("start -> node");
                sb.append(ids.get(node));
                sb.append("\n");
            }
            for(IEdge edge: node.getEdges())
            {
                sb.append("node");
                sb.append(ids.get(node));
                sb.append("->");
                sb.append("node");
                sb.append(ids.get(edge.getEnd()));
                sb.append(" [label=\" ");
                if(edge.getChar()=='\0')
                    sb.append("&epsilon;");
                else
                    sb.append(edge.getChar());
                sb.append("\"]\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }
    
    
}
