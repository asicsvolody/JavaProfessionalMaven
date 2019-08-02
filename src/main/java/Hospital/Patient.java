package Hospital;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Patient {

    private void goToDoctor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        Doctor doctor = context.getBean("doctor",Doctor.class);
        doctor.inspection();

    }

    public static void main(String[] args) {
        new Patient().goToDoctor();
    }
}
