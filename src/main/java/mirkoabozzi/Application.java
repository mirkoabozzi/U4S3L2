package mirkoabozzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mirkoabozzi.dao.EventDAO;
import mirkoabozzi.entities.Event;
import mirkoabozzi.entities.EventType;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4s3l2");

    public static void main(String[] args) {


        LocalDate today = LocalDate.now();

        EntityManager em = emf.createEntityManager();
        EventDAO ed = new EventDAO(em);

        Event summerFest = new Event("summer fest", today, "festa estiva", EventType.PUBBLIC, 50);

        ed.save(summerFest);
    }
}
