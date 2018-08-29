package urlparse;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.Scheduler;
import org.quartz.JobDetail;
import org.quartz.JobBuilder;
import org.quartz.TriggerBuilder;
import org.quartz.Trigger;
import org.quartz.CronScheduleBuilder;
import org.quartz.SchedulerException;
import org.apache.log4j.Logger;

public class URLParser {
    static String configuration;
    static Logger logger;

    public static void main(String[] args) throws SchedulerException {
        if (args.length > 0) {
            configuration = args[0];
        } else {
            configuration = "app.properties";
        }
        System.getProperties().setProperty("log4j.configuration",
                                String.format("file:%s", configuration));
        logger = Logger.getLogger(URLParser.class);
        String time = loadProperties().getProperty("cron.time");
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        JobDetail job = JobBuilder.newJob(URLParserJob.class)
                                          .withIdentity("job1", "group1")
                                          .build();
        Trigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("trigger1", "group1")
            .withSchedule(CronScheduleBuilder.cronSchedule(time))
            .forJob("job1", "group1")
            .build();
        sched.scheduleJob(job, trigger);
        sched.start();
    }

    static Properties loadProperties() {
        Properties result = new Properties();
        try {
            result.load(new FileReader(configuration));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return result;
    }
}
