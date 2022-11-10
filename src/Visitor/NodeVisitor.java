package Visitor;

import Node.BookmarkNode;
import Node.TitleNode;

import java.io.IOException;

public abstract class NodeVisitor {
    public NodeVisitor(){

    }

    public abstract void visitTitleNode(TitleNode tn) throws Exception;

    public abstract void visitBookmarkNode(BookmarkNode bn) throws Exception;

}
