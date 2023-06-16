package com.go.it.spring.configuration.hibernate;

import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.PersistentObjectException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.lang.String.format;
import static java.lang.String.join;

@Slf4j
public abstract class HibernateAbstractRepository<T, ID> {
    private final Datasource datasource;
    private final Class<T> entityType;
    private final Field idField;
    private final String selectTemplate;
    private final String deleteTemplate;
    private final String whereTemplate;

    @SuppressWarnings("unchecked")
    public HibernateAbstractRepository(Datasource datasource) {
        this.datasource = datasource;
        this.entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String entityName = entityType.getSimpleName();
        selectTemplate = format("select c from %s c", entityName);
        deleteTemplate = format("delete from %s c", entityName);
        idField = Arrays.stream(entityType.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow();
        idField.setAccessible(true);
        String name = idField.getName();
        whereTemplate = format("where c.%s=:id", name);
    }

    public List<T> findAll() {
        return dbCall(datasource, session -> session
                .createQuery(selectTemplate, entityType)
                .getResultList());
    }


    public T findById(ID id) {
        return dbCall(datasource, session -> {
            String queryString = join(" ", selectTemplate, whereTemplate);
            Query<T> query = session.createQuery(queryString, entityType);
            query.setParameter("id", id);
            T result;
            try {
                result = query.getSingleResult();
            } catch (Exception e) {
                log.warn("No results found", e);
                result = null;
            }
            return result;
        });
    }

    public TransactionalResult<T> findByIdTransactional(ID id) {
        return dbCall(datasource, session -> {
            String queryString = join(" ", selectTemplate, whereTemplate);
            Query<T> query = session.createQuery(queryString, entityType);
            query.setParameter("id", id);
            T result;
            try {
                result = query.getSingleResult();
            } catch (Exception e) {
                log.warn("No results found", e);
                result = null;
            }
            return TransactionalResult.of(session, session.getTransaction(), result);
        });
    }

    public T save(T entity) {
        return dbCall(datasource, session -> save(entity, session));
    }

    @SuppressWarnings("unchecked")
    public int delete(T entity) {
        try {
            ID id = (ID) idField.get(entity);
            return deleteById(id);
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public int deleteById(ID id) {
        return dbCall(datasource, session -> {
            String queryString = join(" ", deleteTemplate, whereTemplate);
            MutationQuery mutationQuery = session.createMutationQuery(queryString);
            mutationQuery.setParameter("id", id);
            return mutationQuery.executeUpdate();
        });
    }

    @SuppressWarnings("unchecked")
    private T save(T entity, Session session) {
        try {
            if (session.contains(entity)) {
                session.persist(entity);
                return entity;
            } else {
                return session.merge(entity);
            }
        } catch (PersistentObjectException e) {
            throw new RuntimeException(e);
        }
    }

    public static <R> R dbCall(Datasource datasource, Function<Session, R> function) {
        try {
            Session session = datasource.openSession();
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            session.close();
            return result;
        } catch (Exception e) {
            log.error("db execution failed", e);
            throw new RuntimeException(e);
        }
    }
}
