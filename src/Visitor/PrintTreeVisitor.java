package Visitor;

import Node.BookmarkNode;
import Node.TitleNode;

import java.util.List;

public class PrintTreeVisitor extends NodeVisitor{
    List<Boolean> active;
    int level;
    Boolean isLastSibling;

    public PrintTreeVisitor(List<Boolean> active, int level, Boolean isLastSibling){
        this.active = active;
        this.level = level;
        this.isLastSibling = isLastSibling;
    }

    @Override
    public void visitTitleNode(TitleNode tn) throws Exception {
        if(level > 0){
            for(int i=1; i<level; i++){
                if(active.get(i)){
                    System.out.print("│   ");
                } else {
                    System.out.print("    ");
                }
            }
            if(isLastSibling){
                System.out.print("└── ");
                active.add(false);
            } else {
                System.out.print("├── ");
                active.add(true);
            }
            System.out.println(tn.getName());
        } else {
            active.add(false);
        }
        int n = tn.getChildren().size();
        for(int i=0; i<n; i++){
            PrintTreeVisitor next = new PrintTreeVisitor(active, level+1, i >= n - 1);
            tn.getChildren().get(i).accept(next);
        }
        active.remove(active.size()-1);
    }

    @Override
    public void visitBookmarkNode(BookmarkNode bn) throws Exception{
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
        System.out.println(bn.getPrintName());
    }
}
