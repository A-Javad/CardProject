package az.company.cardproject.model.exception;

public class CardNotValidException extends RuntimeException{
    public CardNotValidException(String message){
        super(message);
    }
}
