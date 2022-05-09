package br.com.senac.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import br.com.senac.entidades.Funcionario;

public class FuncionarioDaoImpl extends BaseDaoImpl<Funcionario, Long> implements FuncionarioDao, Serializable{

   @Override
   public Funcionario searchId(Long id, Session session) throws HibernateException {
      return session.get(Funcionario.class, id);
   }

   @Override
   public List<Funcionario> searchForNameOrPhone(String search, Session session) throws HibernateException {
      Query<Funcionario> consulta = session.createQuery("FROM Funcionario f WHERE f.nome OR f.telefone LIKE :search ", Funcionario.class);
      consulta.setParameter("search", "%" + search + "%");

      return consulta.getResultList();
   }
   
}
