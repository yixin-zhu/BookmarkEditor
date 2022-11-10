package Node;

import Visitor.NodeVisitor;
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

    public void accept(NodeVisitor nv) throws Exception {
        nv.visitTitleNode(this);
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
