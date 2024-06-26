package rw.ac.rca.OnlineShop.exceptions;

public class UserAlreadyExists extends RuntimeException{
  public UserAlreadyExists(String errorMessage){
      super(errorMessage);
  }
}
