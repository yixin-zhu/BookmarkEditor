package Adapter;

import Decorator.BookmarkNodeDecorator;
import Node.Node;
import Node.TitleNode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectorAdaptor extends TitleNode {
    File file;

    public DirectorAdaptor(String path) {
        file = new File(path);
    }

    public DirectorAdaptor(File f) {
        file = f;
    }

    public String getName() {
        return file.getName();
    }

    @Override
    public List<Node> getChildren() {
        List<Node> ans = new ArrayList<>();
        File[] childrenFiles = this.file.listFiles();
        for(File f : childrenFiles) {
            if(f.isDirectory()) {
                ans.add(new DirectorAdaptor(f));
            } else if (f.isFile()) {
                ans.add(new FileAdaptor(f));
            }
        }
        return ans;
    }
}
