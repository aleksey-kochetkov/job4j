package email;

public class Emailer {
    public void send(String subject, String body, String email) {
        System.out.format("Thread:%s%n", Thread.currentThread());
        System.out.format("%s %s%n%s%n%n", email, subject, body);
    }
}
