package Util;

public class CommandParser {

    public CommandParser(){

    }

    public String getFileNameFromBookmarkCommand(String command){
        int indexBeginOfName = command.indexOf('\"')+1;
        int indexEndOfName = command.lastIndexOf('\"');
        return command.substring(indexBeginOfName, indexEndOfName);
    }

    public String getTitleNameFromAddTitleCommand(String command){
        int indexBeginTitleName = command.indexOf('\"')+1;
        int indexEndTitleName = command.indexOf('\"', indexBeginTitleName);
        return command.substring(indexBeginTitleName, indexEndTitleName);
    }

    public String getDestinationNameFromAddTitleCommand(String command){
        int indexOfAt = command.indexOf("\" at \"");
        if(indexOfAt < 0 ){
            return "root";
        }
        int indexBeginDestinationName = command.indexOf('\"', indexOfAt+1)+1;
        int indexEndDestinationName = command.indexOf('\"', indexBeginDestinationName);
        return command.substring(indexBeginDestinationName, indexEndDestinationName);
    }

    public String getBookmarkNameFromAddBookmarkCommand(String command){
        int indexBeginBookmarkName = command.indexOf('\"')+1;
        int indexEndBookmarkName = command.indexOf('\"', indexBeginBookmarkName);
        return command.substring(indexBeginBookmarkName, indexEndBookmarkName);
    }

    public String getURLFromAddBookmarkCommand(String command) {
        int indexOfat = command.indexOf("@");
        int indexBeginURL = command.indexOf('\"', indexOfat)+1;
        int indexEndURL = command.indexOf('\"', indexBeginURL);
        return command.substring(indexBeginURL, indexEndURL);
    }

    public String getDestinationNameFromAddBookmarkCommand(String command) {
        int indexOfAt = command.indexOf("\" at \"");
        if(indexOfAt < 0 ){
            return "root";
        }
        int indexBeginDestinationName = command.indexOf('\"', indexOfAt+1)+1;
        int indexEndDestinationName = command.indexOf('\"', indexBeginDestinationName);
        return command.substring(indexBeginDestinationName, indexEndDestinationName);
    }

    public String getFileNameFromOpenCommand(String command) {
        int indexBeginOfName = command.indexOf('\"')+1;
        int indexEndOfName = command.lastIndexOf('\"');
        return command.substring(indexBeginOfName, indexEndOfName);
    }

    public String getTitleNameFromDeleteTitleCommand(String command) {
        int indexBeginOfName = command.indexOf('\"')+1;
        int indexEndOfName = command.lastIndexOf('\"');
        return command.substring(indexBeginOfName, indexEndOfName);
    }

    public String getBookmarkNameFromDeleteBookmarkCommand(String command) {
        int indexBeginOfName = command.indexOf('\"')+1;
        int indexEndOfName = command.lastIndexOf('\"');
        return command.substring(indexBeginOfName, indexEndOfName);
    }

    public String getBookmarkNameFromReadBookmarkCommand(String command){
        int indexBeginOfName = command.indexOf('\"')+1;
        int indexEndOfName = command.lastIndexOf('\"');
        return command.substring(indexBeginOfName, indexEndOfName);
    }

    public String getScriptNameFromRunCommand(String command){
        int indexBeginOfName = command.indexOf('\"')+1;
        int indexEndOfName = command.lastIndexOf('\"');
        return command.substring(indexBeginOfName, indexEndOfName);
    }
}
