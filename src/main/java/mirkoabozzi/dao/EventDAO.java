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
        Event found = em.find(Event.class, eventId);
        if (found == null) throw new NotFoundException(eventId);
        else return found;
    }
}
