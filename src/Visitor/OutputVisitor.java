package Visitor;

import Node.Node;
import Node.TitleNode;
import Node.BookmarkNode;
import java.io.BufferedWriter;
import java.io.IOException;

public class OutputVisitor extends NodeVisitor{
    BufferedWriter bw;
    int level;

    public OutputVisitor(BufferedWriter bw, int level){
        this.bw = bw;
        this.level = level;
    }

    @Override
    public void visitTitleNode(TitleNode tn) throws Exception {
        if(level>0){
            bw.write(tn.toString(level));
            bw.newLine();
        }
        for(Node child : tn.getChildren()){
            OutputVisitor ov = new OutputVisitor(bw, level+1);
            child.accept(ov);
        }
    }
    @Override
    public void visitBookmarkNode(BookmarkNode bn) throws Exception {
        bw.write(bn.toString());
        bw.newLine();
    }
}
