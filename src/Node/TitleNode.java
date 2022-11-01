package Node;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TitleNode extends Node {

    public TitleNode(){

    }

    public TitleNode(String titleName){
        this.name = titleName;
        this.children = new ArrayList<>();
    }

    private List<Node> children;

    public List<Node> getChildren() {
        return children;
    }

    public String getName(){
        return name;
    }

    public String toString(int level){
        String ans = "";
        for(int i=0; i<level; i++){
            ans = ans.concat("#");
        }
        ans = ans.concat(" " + name);
        return ans;
    }

    public void output(BufferedWriter bw, int level) throws IOException {
        if(level>0){
            bw.write(this.toString(level));
            bw.newLine();
        }
        for(Node child : children){
            if(child instanceof TitleNode){
                ((TitleNode) child).output(bw, level+1);
            } else {
                child.output(bw);
            }
        }

    }
    public TitleNode findFirst(String titleName){
        if(Objects.equals(this.name, titleName)){
            return this;
        } else {
            for(Node child : children){
                if(child instanceof TitleNode){
                    TitleNode findFromChild = ((TitleNode) child).findFirst(titleName);
                    if(findFromChild!=null){
                        return findFromChild;
                    }
                }
            }
        }
        return null;
    }

    public BookmarkNode findFirstBookmark(String titleName){
        for(Node child : children){
            if(child instanceof TitleNode){
                BookmarkNode findFromChild = ((TitleNode) child).findFirstBookmark(titleName);
                if(findFromChild!=null){
                    return findFromChild;
                }
            } else if (child instanceof BookmarkNode){
                if(Objects.equals(((BookmarkNode) child).getName(), titleName)) {
                    return (BookmarkNode) child;
                }
            }
        }
        return null;
    }

    public void addChild(Node child){
        children.add(child);
    }

    public Node removeChild(int index){
        return children.remove(index);
    }

    public void modifyChild(Node newChild, int index) {
        if(index >= 0 && index <= children.size()-1){
            children.set(index, newChild);
        } else {
            System.out.println("ModifyChild failed! No such a child.");
        }
    }

    public void insertChild(int index, Node child){
        children.add(index, child);
    }

    public void printTree(List<Boolean> active, int level, Boolean isLastSibling){
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
            System.out.println(this.getName());
        } else {
            active.add(false);
        }
        int n = this.getChildren().size();
        for(int i=0; i<n; i++){
            this.getChildren().get(i).printTree(active, level+1, i >= n - 1);
        }
        active.remove(active.size()-1);
    }

    public List<ParentInfo> findParentInfoByChildren(String childName, List<ParentInfo> ans) {
        int n = children.size();
        boolean flag = false;
        ParentInfo tempParentInfo = new ParentInfo(this);
        for(int i=0; i<n; i++){
            if(Objects.equals(children.get(i).name, childName)){
                tempParentInfo.addIndex(i);
                flag = true;
            } else if(children.get(i) instanceof TitleNode){
                ((TitleNode) children.get(i)).findParentInfoByChildren(childName, ans);
            }
        }
        if(flag){
            ans.add(tempParentInfo);
        }
        return ans;
    }

    public List<ParentInfo> findParentInfoByBookmark(String bookmarkName, List<ParentInfo> ans) {
        int n = children.size();
        boolean flag = false;
        ParentInfo tempParentInfo = new ParentInfo(this);
        for(int i=0; i<n; i++){
            if(children.get(i) instanceof BookmarkNode && Objects.equals(((BookmarkNode) children.get(i)).getName(), bookmarkName)){
                tempParentInfo.addIndex(i);
                flag = true;
            } else if(children.get(i) instanceof TitleNode){
                ((TitleNode) children.get(i)).findParentInfoByBookmark(bookmarkName, ans);
            }
        }
        if(flag){
            ans.add(tempParentInfo);
        }
        return ans;
    }
}
