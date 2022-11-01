package Adapter;

import Node.BookmarkNode;

import java.io.File;

public class FileAdaptor extends BookmarkNode {
    File file;

    public FileAdaptor(String path) {
        file = new File(path);
    }

    public FileAdaptor(File f) {
        file = f;
    }

    public String getName() {
        return file.getName();
    }

    @Override
    public String getPrintName() {
        return ("\"" + this.getName() + "\"");
    }
}
