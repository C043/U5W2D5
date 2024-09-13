package fragnito.U5W2D5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("Nessun elemento con id: " + id + " è stato trovato");
    }
}
