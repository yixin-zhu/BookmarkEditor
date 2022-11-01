import Adapter.DirectorAdaptor;
import Command.AddCommand;
import Command.DeleteCommand;
import Command.Command;
import Decorator.BookmarkNodeDecorator;
import Node.BookmarkNode;
import Node.ParentInfo;
import Node.TitleNode;
import Util.CommandParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class BookmarkTree {
    TitleNode root;
    String workingBookmarkFileName;

    Stack<Command> usedCommand;

    Stack<Command> futureCommand;

    CommandParser cp;

    public BookmarkTree(){
        this.root = null;
        this.workingBookmarkFileName = "";
        this.usedCommand = new Stack<>();
        this.futureCommand = new Stack<>();
        this.cp = new CommandParser();
    }

    public void print(){
        List<Boolean> active = new ArrayList<>();
        root.printTree(active, 0, true);
    }

    public void save(String fileName) throws IOException {
        FileWriter fw = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fw);
        root.output(bw, 0);
        bw.close();
        fw.close();
    }

    public void dealWithCommand(String command) throws Exception {
        // System.out.println(command);
        String[] splitCommand = command.split(" ");
        switch (splitCommand[0]){
            case "bookmark" :
                workingBookmarkFileName = cp.getFileNameFromBookmarkCommand(command);
                this.open(workingBookmarkFileName);
                break;
            case "open" :
                workingBookmarkFileName = cp.getFileNameFromOpenCommand(command);
                this.open(workingBookmarkFileName);
                break;
            case "save" :
                this.save(workingBookmarkFileName);
                break;
            case "add-title" :
                String addName = cp.getTitleNameFromAddTitleCommand(command);
                String destinationName = cp.getDestinationNameFromAddBookmarkCommand(command);
                TitleNode addNode = new TitleNode(addName);
                AddCommand ac = new AddCommand(addNode, root, destinationName);
                ac.execute();
                futureCommand.clear();
                usedCommand.add(ac);
                break;
            case "add-bookmark" :
                String name = cp.getBookmarkNameFromAddBookmarkCommand(command);
                String url = cp.getURLFromAddBookmarkCommand(command);
                String destination = cp.getDestinationNameFromAddBookmarkCommand(command);
                BookmarkNode bookmarkNode = new BookmarkNode(name, url);
                AddCommand addBookmarkCommand = new AddCommand(bookmarkNode, root, destination);
                addBookmarkCommand.execute();
                futureCommand.clear();
                usedCommand.add(addBookmarkCommand);
                break;
            case "delete-title" :
                String delteTitleName = cp.getTitleNameFromDeleteTitleCommand(command);
                DeleteCommand deleteTitleCommand = new DeleteCommand(delteTitleName, root);
                deleteTitleCommand.execute();
                futureCommand.clear();
                usedCommand.add(deleteTitleCommand);
                break;
            case "delete-bookmark" :
                String delteBookmarkName = cp.getBookmarkNameFromDeleteBookmarkCommand(command);
                DeleteCommand deleteBookmarkCommand = new DeleteCommand(delteBookmarkName, root);
                deleteBookmarkCommand.execute();
                futureCommand.clear();
                usedCommand.add(deleteBookmarkCommand);
                break;
            case "read-bookmark" :
                System.out.println("read-bookmark in execute");
                String readBookmarkName = cp.getBookmarkNameFromReadBookmarkCommand(command);
                System.out.println(readBookmarkName);
                List<ParentInfo> parentInfos = root.findParentInfoByBookmark(readBookmarkName, new ArrayList<>());
                for(ParentInfo parentInfo : parentInfos) {
                    TitleNode parent = parentInfo.getParent();
                    for(int index : parentInfo.getIndex()) {
                        BookmarkNodeDecorator deco = new BookmarkNodeDecorator((BookmarkNode) parent.getChildren().get(index));
                        parent.modifyChild(deco, index);
                    }
                }
                break;
            case "show-tree" :
                this.print();
                break;
            case "ls-tree" :
                File tempFile = new File("");
                File currentFile = new File(tempFile.getAbsolutePath());
                this.root = new DirectorAdaptor(currentFile);
                this.print();
                break;
            case "undo" :
                if(usedCommand.size() > 0) {
                    usedCommand.peek().unexecute();
                    futureCommand.add(usedCommand.pop());
                } else {
                    System.out.println("Undo failed. Nothing to undo.");
                }
                break;
            case "redo" :
                if(futureCommand.size() > 0){
                    futureCommand.peek().execute();
                    usedCommand.add(futureCommand.pop());
                } else {
                    System.out.println("Redo failed. Nothing to redo.");
                }
                break;
        }

    }

    public void runFromFile(String fileName) throws Exception {
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String command = "";
        while((command=br.readLine())!=null){
            dealWithCommand(command);
        }
        br.close();
        fr.close();
    }

    public void open(String fileName) throws Exception {
        File myfile = new File(fileName);
        if(!myfile.exists()){
            myfile.createNewFile();
        }
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        Stack<TitleNode> s = new Stack<>();
        root = new TitleNode("root");
        s.push(root);
        while((line=br.readLine())!=null){
            covert(line, s);
        }
        br.close();
        fr.close();
    }

    public void covert(String line, Stack<TitleNode> s) {
        if(line.startsWith("#")){
            covertTitleNode(line, s);
        } else {
            covertBookmarkNode(line, s);
        }
    }

    public void covertTitleNode(String line, Stack<TitleNode> s) {
        String[] splitLine = line.split(" ");
        int n = splitLine[0].length();
        while(s.size() > n){
            s.pop();
        }
        TitleNode addTitleNode = new TitleNode(line.substring(n+1));
        s.peek().addChild(addTitleNode);
        s.push(addTitleNode);
    }

    public void covertBookmarkNode(String line, Stack<TitleNode> s) {
        int indexOfEndOfName = line.indexOf(']');
        int indexOfBeginOfURL = line.indexOf('(') + 1;
        int indexOfEndOfURL = line.indexOf(')');
        String name = line.substring(1, indexOfEndOfName);
        String url = line.substring(indexOfBeginOfURL, indexOfEndOfURL);
        BookmarkNode addBookmarkNode = new BookmarkNode(name, url);
        s.peek().addChild(addBookmarkNode);
    }
    public void test() throws Exception {
        root = new TitleNode("root");
        runFromFile("data/testcase/testcase4.txt");
        /*
        runFromFile("data/testcase/testcase1.txt");
        runFromFile("data/testcase/testcase2.txt");
        runFromFile("data/testcase/testcase3.txt");
        runFromFile("data/testcase/testcase5.txt");


         */
        /*
        System.out.println("Please enter your command!");
        while(true){
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            dealWithCommand(command);
            print();
        }*/
    }
}
