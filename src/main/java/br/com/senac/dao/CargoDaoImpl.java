package br.com.senac.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import br.com.senac.entidades.Cargo;

public class CargoDaoImpl extends BaseDaoImpl<Cargo, Long> implements CargoDao {

   @Override
   public Cargo searchId(Long id, Session session) throws HibernateException {
      return session.get(Cargo.class, id);
   }

   @Override
   public List<Cargo> searchForDescricao(String descricao, Session session) throws HibernateException {
      Query<Cargo> consulta = session.createQuery("FROM Cargo c WHERE c.descricao LIKE :descricaoSearch ", Cargo.class);
      consulta.setParameter("descricaoSearch", "%" + descricao + "%");

      return consulta.getResultList();
   }

   @Override
   public List<Cargo> fetchLCargos(Session session) throws HibernateException {
      return  session.createQuery("FROM Cargo c", Cargo.class).getResultList();
   }
   
}
