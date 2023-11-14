package christmas.exception;

public class OrderException extends IllegalArgumentException{
    private OrderException(
            ErrorMessage errorMessage,
            Exception exception
    ){
        super(errorMessage.getMessage(), exception);
    }

    private OrderException(ErrorMessage errorMessage){
        super(errorMessage.getMessage());
    }

    public static OrderException of(
            ErrorMessage errorMessage,
            Exception exception
    ){
        return new OrderException(errorMessage, exception);
    }

    public static OrderException from(ErrorMessage errorMessage){
        return new OrderException(errorMessage);
    }
}
