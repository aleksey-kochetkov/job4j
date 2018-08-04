package email;

import org.junit.Test;

public class EmailNotificationTest {
    @Test
    public void when() {
        EmailNotification notif = new EmailNotification();
        User[] users = new User[] {new User("One", "one@srv.dm"),
                             new User("Two", "two@srv.dm"),
                             new User("Three", "three@srv.dm"),
                             new User("Four", "four@srv.dm"),
                             new User("Five", "five@srv.dm"),
                             new User("Six", "six@srv.dm"),
                             new User("Seven", "seven@srv.dm"),
                             new User("Eight", "eight@srv.dm")};
        for (User u : users) {
            notif.emailTo(u);
        }
        notif.shutdown();
    }
}
