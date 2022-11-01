package Command;

import Node.Node;
import Node.ParentInfo;
import Node.TitleNode;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends Command{
    private String deleteName;
    private TitleNode root;
    List<ParentInfo> parentInfos;

    List<List<Node> > deletedChildren;

    public DeleteCommand(String deleteName, TitleNode root){
        this.deleteName = deleteName;
        this.root = root;
        this.parentInfos = new ArrayList<>();
        this.deletedChildren = new ArrayList<>();
    }
    public void execute(){
        parentInfos = root.findParentInfoByChildren(deleteName, new ArrayList<>());
        for(ParentInfo pInfo : parentInfos){
            int m = pInfo.getIndex().size();
            List<Node> tempChildren = new ArrayList<>();
            for(int j = m-1; j>=0; j--){
                tempChildren.add(0, pInfo.getParent().removeChild(pInfo.getIndex().get(j)));
            }
            deletedChildren.add(tempChildren);
        }
    }

    public void unexecute(){
        int n = parentInfos.size();
        for(int i=0; i<n; i++){
            int m = deletedChildren.get(i).size();
            for(int j=0; j<m; j++){
                parentInfos.get(i).getParent().insertChild(parentInfos.get(i).getIndex().get(j), deletedChildren.get(i).get(j));
            }
        }
    }
}
