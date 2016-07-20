package it.disi.unitn.diversicon.exceptions;

/**
 * A runtime exception to raise when something is not found.
 * 
 * @since 0.1.0
 */
public class DivNotFoundException extends DivException {
    
    private static final long serialVersionUID = 1L;

    private DivNotFoundException(){
        super();
    }
    
    /**
     * Creates the NotFoundException using the provided throwable
     */
    public DivNotFoundException(Throwable tr) {
        super(tr);
    }

    /**
     * Creates the NotFoundException using the provided message and throwable
     */
    public DivNotFoundException(String msg, Throwable tr) {
        super(msg, tr);
    }

    /**
     * Creates the NotFoundException using the provided message
     */
    public DivNotFoundException(String msg) {
        super(msg);
    }
}