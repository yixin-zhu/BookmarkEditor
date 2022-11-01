package Node;

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

    public String toString(){
        return ("[" + name + "](" + url + ")");
    }

    public void output(BufferedWriter bw) throws IOException {
        bw.write(this.toString());
        bw.newLine();
    }
    public void printTree(List<Boolean> active, int level, Boolean isLastSibling){
        for(int i=1; i<level; i++){
            if(active.get(i)){
                System.out.print("│   ");
            } else {
                System.out.print("    ");
            }
        }
        if(isLastSibling){
            System.out.print("└── ");
        } else {
            System.out.print("├── ");
        }
        System.out.println("\"" + this.getName() + "\"");
    }
}
