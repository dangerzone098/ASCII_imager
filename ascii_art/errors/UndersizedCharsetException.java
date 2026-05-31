package ascii_art.errors;

public class UndersizedCharsetException extends Exception{

    public UndersizedCharsetException(){super();}

    public UndersizedCharsetException(String message){
        super(message);
    }
}
