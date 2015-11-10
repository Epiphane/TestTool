package com.teampc.dao;

import com.teampc.utils.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by adufrene on 11/9/15.
 */
@Slf4j
/**
 * Abstract class for database operations
 * @param T class mapped to database table
 */
public abstract class AbstractDAO<T> {

   /**
    * Returns the entity mapping class for this dao
    * @return class this dao maps entities to
    */
   protected abstract Class<T> getEntityClass();

   /**
    * <pre>
    *     pre: item != null
    * </pre>
    *
    * <pre>
    *     post: this.fetchAll()'.contains(item)
    * </pre>
    *
    * Inserts one item into the database
    * @param item item to insert
    */
   public void insert(T item) {
      insert(Collections.singleton(item));
   }

   /**
    * <pre>
    *     pre: items != null
    * </pre>
    *
    * <pre>
    *     post: forall (T item_other; items.contains(item_other); this.fetchAll()'.contains(item_other))
    * </pre>
    *
    *
    * Inserts items into database specified by entity annotations on class T
    * @param items items to insert
    */
   public void insert(Collection<T> items) {
      Session session = HibernateUtils.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();

      try {
         items.forEach(session::save);
         session.flush();
         transaction.commit();
      } catch (Exception e) {
         log.error("Error during insert, rolling back");
         transaction.rollback();
         throw e;
      } finally {
         session.close();
      }
   }

   /**
    * Fetches all items in the database for this object's table
    * @return List of items in this object's table
    */
   public List<T> fetchAll() {
      Session session = HibernateUtils.getSessionFactory().openSession();
      try {
         Criteria criteria = session.createCriteria(getEntityClass());

         @SuppressWarnings("unchecked")
         List<T> items = criteria.list();

         return items;
      } finally {
         session.close();
      }

   }

}
