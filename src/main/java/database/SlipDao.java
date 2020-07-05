package database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

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
}
