package com.teampc.dao;

import com.google.common.collect.Maps;
import com.teampc.model.Model;
import com.teampc.utils.HibernateUtils;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * Created by adufrene on 11/9/15.
 */
@Slf4j
/**
 * Abstract class for database operations
 * @param T a model class
 * @param D a database entity class
 */
public abstract class AbstractDAO<T extends Model, D extends DataDefinition<T>> {

   protected static final boolean DEBUG = false;

   protected static final AtomicInteger idCounter = new AtomicInteger(0);
   protected Map<Integer, T> fakeDB = Maps.newHashMap();

   /**
    * Returns the entity mapping class for this dao
    * @return class this dao maps entities to
    */
   protected abstract Class<D> getEntityClass();

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
      post: forall (Object item_other; items.contains(item_other); this.fetchAll().contains(item_other))
    */
   public void insert(Collection<T> items) {
      /*log.debug("Inserting {} items into {}'s database", items.size(), getEntityClass().getSimpleName());
      if (DEBUG) {
         items.stream().forEach(item ->  {
            Integer newId = idCounter.incrementAndGet();
            item.setId(newId);
            fakeDB.put(newId, item);
         });
         return;
      }*/
      Session session = HibernateUtils.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();

      Collection<D> dds = toDD(items);
      log.debug("number of dd objects to insert: " + dds.size());

      try {
         for (D data : dds) {
            data.save(session);
         }
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
         return fakeDB.values().stream().sorted((item1, item2) -> Integer.compare(item1.getId(), (item2.getId()))).collect(toList());
      }
      Session session = HibernateUtils.getSessionFactory().openSession();
      try {
         Criteria criteria = session.createCriteria(getEntityClass());

         @SuppressWarnings("unchecked")
         List<D> items = criteria.list();

         return toModel(items);
      } finally {
         session.close();
      }

   }

   /**
    * Deletes one item from databse
    * @param item item to delete
     */
   public void delete(T item) {
      delete(Collections.singleton(item));
   }

   /**
    * Deletes supplied items from database
    * @param items items to delete
     */
   public void delete(Collection<T> items) {
      log.debug("Deleting {} from {}'s database", Arrays.toString(items.toArray()), getEntityClass().getSimpleName());
      if (DEBUG) {
         items.stream().map(HasId::getId).forEach(fakeDB::remove);
         return;
      }
      Session session = HibernateUtils.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();
      try {
         for (D item : toDD(items)) {
            item.save(session);
         }
         session.flush();
         transaction.commit();
      } catch (Exception e) {
         log.error("Error deleting items", e);
         transaction.rollback();
      } finally {
         session.clear();
      }
   }

    /**
     * Updates one item in database
     * @param item item to update
     */
   public void update(T item) {
      update(Collections.singleton(item));
   }

   /**
    * Updates items in database, relies on id field of model T
    * @param items items to update
     */
   public void update(Collection<T> items) {
      log.debug("Updating {} in {}'s database", Arrays.toString(items.toArray()), getEntityClass().getSimpleName());
      if (DEBUG) {
         fakeDB.putAll(items.stream().collect(toMap(HasId::getId, identity())));
         return;
      }

      Session session = HibernateUtils.getSessionFactory().openSession();
      Transaction transaction = session.beginTransaction();
      try {
         items.forEach(session::update);
         session.flush();
         transaction.commit();
      } catch (Exception e) {
         log.error("Error updating items", e);
         transaction.rollback();
      } finally {
         session.close();
      }
   }

   /**
    * Get the records with IDs equal to the ones
    * @param ids a list of Integer ids for the data class.
    * @return a list of DD objects
    */
   public List<D> findByIds(List<Integer> ids) {
      Session session = HibernateUtils.getSessionFactory().openSession();
      Criteria criteria = session.createCriteria(getEntityClass());

      criteria.add(Restrictions.in("id", ids));
      List<D> items = criteria.list();

      return items;
   }

   public D findById(Integer id) {
      return findByIds(Collections.singletonList(id)).get(0);
   }


   private List<T> toModel(Collection<D> dds) {
      List<T> modelList = new ArrayList<>();

      for (D data : dds) {
         modelList.add(data.asModel());
      }

      return modelList;
   }

   private List<D> toDD(Collection<T> models) {
      List<D> ddList = new ArrayList<>();

      for (T model : models) {
         ddList.add((D)model.asEntity());
      }

      return ddList;
   }
}
