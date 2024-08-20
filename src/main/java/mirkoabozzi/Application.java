package mirkoabozzi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mirkoabozzi.dao.EventDAO;
import mirkoabozzi.entities.Event;
import mirkoabozzi.entities.EventType;
import mirkoabozzi.exceptions.NotFoundException;

import java.time.LocalDate;

public class Application {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4s3l2");

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();

        EntityManager em = emf.createEntityManager();
        EventDAO ed = new EventDAO(em);

        Event summerFest = new Event("summer fest", today, "festa estiva", EventType.PUBBLIC, 50);
        Event matrimonio = new Event("matrimonio", today.plusMonths(2), "matrimonio mario", EventType.PUBBLIC, 200);
        Event barbiere = new Event("taglio capelli", today.plusDays(5), "taglio capelli", EventType.PRIVATE, 1);
        Event piscina = new Event("nuoto", today.plusYears(1), "200 metri", EventType.PRIVATE, 2);
        Event tracking = new Event("scalata", today.plusDays(20), "scalata in montagna", EventType.PUBBLIC, 100);

//        ed.save(summerFest);
//        ed.save(matrimonio);
//        ed.save(barbiere);
//        ed.save(piscina);
//        ed.save(tracking);


        try {
            Event event = ed.getById(52);
            System.out.println(event);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
