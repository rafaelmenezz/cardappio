package br.com.senac.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public interface BaseDao <T, ID> {

   void saveOrUpdate(T entidade, Session session) throws HibernateException;

   void delete(T entidade, Session session) throws HibernateException;

   T searchId(Long id, Session session) throws HibernateException;

   
}
