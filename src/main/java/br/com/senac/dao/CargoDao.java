package br.com.senac.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.entidades.Cargo;



public interface CargoDao extends BaseDao<Cargo, Long> {

   List<Cargo> searchForCargo(String cargo, Session session) throws HibernateException;
   
   List<Cargo> fetchLCargos(Session session) throws HibernateException;

   List<Cargo> listFuncionarios(Cargo cargo, Session session) throws HibernateException;
   
}
