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
        EntityTransaction transaction = em.getTransaction(); //richiedo la transazione
        transaction.begin(); // avvia la transazione
        em.persist(event); // aggiunge l'evento al persistence context
        transaction.commit(); // inviamo l'evento al database
        System.out.println("Evento " + event.getTitle() + " creato"); // stampo il titolo dell'evento in console per conferma
    }


    public Event getById(long eventId) {
        Event eventFound = em.find(Event.class, eventId);
        if (eventFound == null)
            throw new NotFoundException(eventId); // se non trovo nessun evento con l'id passato al metodo lancio una exeption
        else return eventFound; //se lo trovo ritorno l'evento
    }

    public void delete(long eventId) {
        Event eventFound = this.getById(eventId); // sfrutto il metodo getById() creato sopra
        EntityTransaction transaction = em.getTransaction(); // richiedo la transazione
        transaction.begin(); // avvio la transazione
        em.remove(eventFound); // rimuovo l'evento trovato tramite id dal persistence context
        transaction.commit(); // rimuovo l'evento dal database

        System.out.println("Evento " + eventFound.getTitle() + " eliminato"); // stampo il titolo dell'evento per conferma
    }
}
