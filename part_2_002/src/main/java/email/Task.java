package email;

public class Task implements Runnable {
    private Emailer emailer;
    private String subject;
    private String body;
    private String email;

    public Task(Emailer emailer, String subject, String body, String email) {
        this.emailer = emailer;
        this.subject = subject;
        this.body = body;
        this.email = email;
    }

    @Override
    public void run() {
        this.emailer.send(this.subject, this.body, this.email);
    }
}
