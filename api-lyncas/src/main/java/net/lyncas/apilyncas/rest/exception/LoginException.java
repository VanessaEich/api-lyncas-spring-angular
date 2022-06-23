package net.lyncas.apilyncas.rest.exception;

/**
 * @author Vanessa Eich
 * @created 13/05/2022
 */
public class LoginException extends RuntimeException {

    public LoginException(){
        super("E-mail já cadastrado, utilize outro e-mail!");
    }
}
