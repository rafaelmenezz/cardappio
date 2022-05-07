package br.com.senac.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.entidades.Cliente;
import java.util.List;
import org.hibernate.query.Query;

public class ClienteDaoImpl extends BaseDaoImpl<Cliente, Long> implements ClienteDao {

    @Override
    public Cliente searchId(Long id, Session session) throws HibernateException {

        return session.get(Cliente.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Cliente> searchForName(String name, Session session) throws HibernateException {
        Query<Cliente> consulta = session.createQuery("FROM Cliente c WHERE c.nome LIKE :nomeSearch ");
        consulta.setParameter("nomeSearch", "%" + name + "%");

        return consulta.getResultList();

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Cliente> searchForPhone(String phone, Session session) throws HibernateException {
        Query<Cliente> consulta = session.createQuery("FROM Cliente c WHERE c.telefone LIKE :phoneSearch ");
        consulta.setParameter("phoneSearch", "%" + phone + "%");

        return consulta.getResultList();

    }

}
