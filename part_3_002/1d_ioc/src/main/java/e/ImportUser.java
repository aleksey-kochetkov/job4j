package e;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ImportUser {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: ImportUser login1 [login2]");
        } else {
            new ImportUser().run(args);
        }
    }

    public void run(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("spring-context.xml");
        Storage storage = context.getBean(JdbcStorage.class);
        try {
            for (String login : args) {
                storage.createUser(new User(login));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        storage.close();
    }
}
