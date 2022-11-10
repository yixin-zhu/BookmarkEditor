package Node;

import Visitor.NodeVisitor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public class BookmarkNode extends Node {
    private String url;

    public BookmarkNode(){

    }

    public BookmarkNode(String bookmarkName){
        this.name = bookmarkName;
        this.url = "";
    }

    public BookmarkNode(String bookmarkName, String urlName){
        this.name = bookmarkName;
        this.url = urlName;
    }

    public String getName() {
        return name;
    }

    public String getPrintName() {
        return ("\"" + this.getName() + "\"");
    }

    public String toString(){
        return ("[" + name + "](" + url + ")");
    }

    public void accept(NodeVisitor nv) throws Exception {
        nv.visitBookmarkNode(this);
    }

}
