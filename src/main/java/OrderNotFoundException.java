public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String id) {
        super ("Order mit id " + id + " nicht gefunden.");
    }
}
