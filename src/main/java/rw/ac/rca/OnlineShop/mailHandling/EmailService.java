package rw.ac.rca.OnlineShop.mailHandling;

public interface EmailService {
    public void sendMessage(String to,String subject,String text);
}
