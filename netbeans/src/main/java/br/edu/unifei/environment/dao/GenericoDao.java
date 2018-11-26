/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.unifei.environment.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author windows
 */
public abstract class GenericoDao<E, K> {

    protected EntityManager em;

    private Class<E> clazz = (Class<E>) ((ParameterizedType) this.getClass().
            getGenericSuperclass()).
            getActualTypeArguments()[0];

    public GenericoDao(EntityManager em) {
        this.em = em;
    }

    public void create(E entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
    }

    public void update(E entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    public void bulkUpdate(List<E> entities) {
        em.getTransaction().begin();
        entities.forEach(entity -> {
            em.merge(entity);
        });
        em.getTransaction().commit();
    }
    
    public void flush(){
        em.flush();
    }

    public E find(K key) {
        return em.find(clazz, key);
    }

    public void delete(E entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    public void deleteKey(K key) {
        E obj = this.find(key);
        if (obj != null) {
            this.delete(obj);
        }
    }

    public List<E> findAll() {
        return em.createQuery(
                "from " + clazz.getSimpleName()
        ).getResultList();
    }

    public int getCount() {
        return (Integer) em.createQuery(
                "select count(*) from "
                + clazz.getSimpleName()
        ).getSingleResult();
    }

}
