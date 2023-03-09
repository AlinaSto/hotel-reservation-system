package spring.bookingApp.configurari;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.bookingApp.model.Reservation;
import spring.bookingApp.model.User;
import spring.bookingApp.service.MailService;
import spring.bookingApp.service.ReservationService;

import javax.mail.MessagingException;
import java.util.Map;

@Component
@EnableScheduling
public class ReviewScheduler {


    private ReservationService reservationService;

    private MailService mailService;

    @Autowired
    public ReviewScheduler (ReservationService reservationService, MailService mailService){
        this.reservationService = reservationService;
        this.mailService = mailService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void scheduleTaskUsingCronExpression() throws MessagingException {


        Map<User, Reservation> reviewUsers = reservationService.checkEndDateUsersReservations();

        for (User user: reviewUsers.keySet()){
            mailService.sendRateReviewMessage(user.getEmail(), reservationService.getReservationHotel(reviewUsers.get(user)));
        }
        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "schedule tasks using cron jobs - " + now);
    }
}
