package database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SlipDao<T> {
    public void insertOrUpdate(Slip slip) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.saveOrUpdate(slip);
            transaction.commit();

        } catch (IllegalStateException | RollbackException ise) {
            System.err.println("Błąd wstawiania rekordu.");
            ise.printStackTrace();

            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    public List<Slip> getAll() {
        List<Slip> list = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Slip> query = cb.createQuery(Slip.class);

            Root<Slip> table = query.from(Slip.class);


            query.select(table);

            List<Slip> results = session.createQuery(query).list();
            list.addAll(results);

        } catch (HibernateException he) {
            System.err.println("Listing error.");
            he.printStackTrace();
        }
        return list;
    }

    public Optional<Slip> findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Slip> query = cb.createQuery(Slip.class);
            Root<Slip> table = query.from(Slip.class);
            // poniżej zapytanie o studenta o id = 1
            query.select(table).where(cb.equal(table.get("slipId"), id));
            Slip slip = session.createQuery(query).getSingleResult();
            // wynik może być nullem, więc może zostać nie odnaleziony, w tej sytuacji
            // metoda ofNullable automatycznie zwróci empty
            return Optional.ofNullable(slip);
        } catch (PersistenceException he) {
            System.err.println("Listing error.");
            he.printStackTrace();
        }
        return Optional.empty();
    }

    // Mechanizm usuwania wykona następujące czynności:
    // 1. podajemy id
    // 2. szukamy obiektu o podanym id
    //    a. jeśli znajdziemy to znaleziony obiekt usuwamy (możemy obiekt wysłać do metody delete) (przekazujemy do usunięcia)
    //    b. jeśli nie znajdziemy, wypisujemy komunikat
    public boolean deleteID(int id) {
        // 1. podajemy id
        // 2. szukamy obiektu o podanym id
        Optional<Slip> optionalStudent = findById((long) id);
        if (optionalStudent.isPresent()) {
            //    a. jeśli znajdziemy to znaleziony obiekt usuwamy (możemy obiekt wysłać do metody delete)
            Slip slip = optionalStudent.get();
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.delete(slip);                                                //(przekazujemy do usunięcia)
                transaction.commit();
                return true;
            } catch (IllegalStateException | RollbackException ise) {
                System.err.println("Error");
                ise.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        } else {
            //    b. jeśli nie znajdziemy, wypisujemy komunikat
            System.err.println("Cannot find ID");

        }
        return false;
    }
}
