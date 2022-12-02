package org.ada.farmacia.exceptions;

public class ResourceNotDeletedException extends RuntimeException{

    public static final String MESSAGE = "No se puede eliminar el recurso porque tiene campos asociados.";

    public ResourceNotDeletedException() {
        super(MESSAGE);
    }

    public ResourceNotDeletedException(String message) {
        super(message);
    }
}
