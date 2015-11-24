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

   private static final boolean DEBUG = true;

   /**
    * Returns the entity mapping class for this dao
    * @return class this dao maps entities to
    */
   protected abstract Class<T> getEntityClass();

   /**
    * Inserts one item into the database
    * @param item item to insert
    *
      pre: item != null
    * 
      post: this.fetchAll().contains(item)
    *
    */
   public void insert(T item) {
      insert(Collections.singleton(item));
   }

   /**
    * Inserts items into database specified by entity annotations on class T
    * @param items items to insert
    *
      pre: items != null
    *
      post: forall (T item_other; items.contains(item_other); this.fetchAll()'.contains(item_other))
    */
   public void insert(Collection<T> items) {
      log.debug("Inserting {} items into {}'s database", items.size(), getEntityClass().getSimpleName());
      if (DEBUG) {
         return;
      }
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
      log.debug("Fetching all items from {}'s database", getEntityClass().getSimpleName());
      if (DEBUG) {
         return Collections.emptyList();
      }
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
