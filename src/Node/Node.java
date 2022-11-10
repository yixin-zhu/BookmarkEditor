package Node;

import Visitor.NodeVisitor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class Node {
    public String name;

    protected void output(BufferedWriter bw) throws IOException {
    }

    public void accept(NodeVisitor nv) throws Exception {

    }
}
