package br.com.senac.dao;

import br.com.senac.entidades.Cliente;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public interface ClienteDao extends BaseDao<Cliente, Long> {
   
     List<Cliente> searchForName(String name, Session session) throws HibernateException;
     
     List<Cliente> searchForPhone(String phone, Session session) throws HibernateException;
}
