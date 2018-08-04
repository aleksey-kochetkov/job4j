package email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(
                             Runtime.getRuntime().availableProcessors());
    private Emailer emailer = new Emailer();

    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s",
                                    user.getUsername(), user.getEmail());
        String body = String.format(
                            "Add a new event to %s", user.getUsername());
        this.pool.submit(new Task(
                          this.emailer, subject, body, user.getEmail()));
    }

    public void shutdown() {
        this.pool.shutdown();
        while (!this.pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }
}
