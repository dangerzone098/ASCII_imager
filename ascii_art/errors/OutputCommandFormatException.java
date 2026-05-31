package ascii_art.errors;

public class OutputCommandFormatException extends Exception{

    public OutputCommandFormatException(){super();}

    public OutputCommandFormatException(String message){
        super(message);
    }
}
