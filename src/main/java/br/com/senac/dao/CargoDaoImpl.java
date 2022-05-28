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
   public List<Cargo> searchForCargo(String cargo, Session session) throws HibernateException {
      Query<Cargo> consulta = session.createQuery("FROM Cargo c WHERE c.cargo LIKE :cargoSearch ", Cargo.class);
      consulta.setParameter("cargoSearch", "%" + cargo + "%");

      return consulta.getResultList();
   }

   @Override
   public List<Cargo> fetchLCargos(Session session) throws HibernateException {
      return session.createQuery("FROM Cargo c join fetch c.funcionarios f", Cargo.class).list();
   }

   @Override
   public List<Cargo> listFuncionarios(Cargo cargo, Session session) throws HibernateException {
      Query<Cargo> consulta = session.createQuery("from Cargo c join fetch c.funcionarios f where c.cargo like :searchCargo", Cargo.class);
      consulta.setParameter("searchCargo", "%" + cargo.getCargo() + "%");
      return consulta.list();
   }

}
