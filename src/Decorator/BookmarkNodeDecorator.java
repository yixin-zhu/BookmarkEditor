package Decorator;

import Node.BookmarkNode;

public class BookmarkNodeDecorator extends BookmarkNode {
    private boolean visited;
    BookmarkNode bookmarkNode;
    // private int visitTimes;
    // private boolean editing;

    public BookmarkNodeDecorator(BookmarkNode b){
        this.visited = true;
        this.bookmarkNode = b;
    }

    public String getName(){
        String ans = "";
        if(visited && (!(this.bookmarkNode instanceof BookmarkNodeDecorator))) {
            ans = ans + "*";
        }
        ans = ans + bookmarkNode.getName();
        return ans;
    }

    public String toString(){
        return ("[" + bookmarkNode.getName() + "](" + bookmarkNode.getUrl() + ")");
    }

    public String getUrl() {return bookmarkNode.getUrl();}

    public String getPrintName() {
        return ("\"" + this.getName() + "\"");
    }

}
