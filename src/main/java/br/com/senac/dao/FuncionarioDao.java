package br.com.senac.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.entidades.Funcionario;

public interface FuncionarioDao extends BaseDao<Funcionario, Long> {

   List<Funcionario> searchForNameOrPhone(String search, Session session) throws HibernateException;

   Funcionario login(String email, String senha, Session session) throws HibernateException;
   
}
