package mirkoabozzi.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import mirkoabozzi.entities.Event;
import mirkoabozzi.exceptions.NotFoundException;

public class EventDAO {

    private final EntityManager em;

    public EventDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Event event) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(event);
        transaction.commit();
        System.out.println("Evento " + event.getDescription() + " creato");
    }


    public Event getById(long eventId) {
        Event eventFound = em.find(Event.class, eventId);
        if (eventFound == null) throw new NotFoundException(eventId);
        else return eventFound;
    }

    public void delete(long eventId) {
        Event eventFound = this.getById(eventId);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(eventFound);
        transaction.commit();

        System.out.println("Evento " + eventFound.getTitle() + " eliminato");
    }
}
