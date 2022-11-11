import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean testMode = false;
        if(args.length > 0){
            testMode = true;
        }
        BookmarkTree bt = new BookmarkTree(testMode);
        bt.test();
    }
}