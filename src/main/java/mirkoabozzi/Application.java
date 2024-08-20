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

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("u4s3l2"); //verifico connessione con database

    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager(); // creo EntityManager da EntityManagerFactory
        EventDAO ed = new EventDAO(em);  // creo un nuovo EventDAO e gli passo come parametro l'EntityManager appena creato in modo da poterlo poi utilizzare nella classe EventDAO

        Event summerFest = new Event("summer fest", LocalDate.of(2024, 8, 20), "festa estiva", EventType.PUBBLIC, 50);
        Event matrimonio = new Event("matrimonio", LocalDate.of(2024, 10, 20), "matrimonio mario", EventType.PUBBLIC, 200);
        Event barbiere = new Event("taglio capelli", LocalDate.of(2025, 1, 1), "taglio capelli", EventType.PRIVATE, 1);
        Event piscina = new Event("nuoto", LocalDate.of(2024, 7, 25), "200 metri", EventType.PRIVATE, 2);
        Event tracking = new Event("scalata", LocalDate.of(2024, 11, 13), "scalata in montagna", EventType.PUBBLIC, 100);

        ed.save(summerFest);
        ed.save(matrimonio);
        ed.save(barbiere);
        ed.save(piscina);
        ed.save(tracking);


        try {
            Event event = ed.getById(52);
            System.out.println(event);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }


        try {
            ed.delete(54);
        } catch (NotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        em.close();
        emf.close();
    }
}
