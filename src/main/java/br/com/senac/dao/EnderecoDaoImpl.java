package br.com.senac.dao;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import br.com.senac.entidades.Endereco;

public class EnderecoDaoImpl extends BaseDaoImpl<Endereco, Long> implements EnderecoDao, Serializable{

   @Override
   public Endereco searchId(Long id, Session session) throws HibernateException {
      return session.find(Endereco.class, id);
   }
   
}
