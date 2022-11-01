package Command;

import Node.Node;
import Node.TitleNode;

public class AddCommand extends Command{
    Node addNode;
    TitleNode rootNode;
    String destination;

    TitleNode destinationNode;

    int siblingIndex;

    boolean executed;

    public AddCommand(Node addNode, TitleNode rootNode, String destination){
        this.addNode = addNode;
        this.rootNode = rootNode;
        this.destination = destination;
        this.executed = false;
    }

    public void execute(){
        destinationNode = rootNode.findFirst(destination);
        if(destinationNode == null){
            System.out.println("Add failed! No such a destination named " + destination + " found.");
            return;
        }
        destinationNode.addChild(addNode);
        siblingIndex = destinationNode.getChildren().size()-1;
        executed = true;
    }

    public void unexecute(){
        if(executed) {
            destinationNode.removeChild(siblingIndex);
            executed = false;
        }
    }
}

