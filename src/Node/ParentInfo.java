package Node;

import java.util.ArrayList;
import java.util.List;

public class ParentInfo {
    TitleNode parent;
    List<Integer> index;

    ParentInfo(TitleNode parent){
        this.parent = parent;
        this.index = new ArrayList<>();
    }

    public TitleNode getParent(){
        return parent;
    }

    public List<Integer> getIndex(){
        return index;
    }

    public void addIndex(int x){
        this.index.add(x);
    }

}
