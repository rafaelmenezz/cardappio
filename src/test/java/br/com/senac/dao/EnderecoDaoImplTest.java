package br.com.senac.dao;

import br.com.senac.entidades.Endereco;
import br.com.senac.util.GeradorUtil;
import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnderecoDaoImplTest {
   
   private Endereco endereco;
   private EnderecoDao enderecoDao;
   private Session session;

   public EnderecoDaoImplTest(){
      enderecoDao = new EnderecoDaoImpl();
   }

   @Test
   public void testSave(){
      endereco = new Endereco(GeradorUtil.gerarCep(), GeradorUtil.gerarNome(), "complemento",
                               GeradorUtil.gerarCidade(), GeradorUtil.gerarUf(), "pontoReferencia");

      session = HibernateUtil.abrirConexao();
      enderecoDao.saveOrUpdate(endereco, session);
      session.close();

      assertNotNull(endereco.getId());
   }

   @Test
   public void testUpdate(){
      buscarEnderecoBD();

      endereco.setCep(GeradorUtil.gerarCep());
      endereco.setLogradouro(GeradorUtil.gerarNome());
      endereco.setComplemento("Alterado");
      endereco.setCidade(GeradorUtil.gerarCidade());
      endereco.setUf(GeradorUtil.gerarUf());
      endereco.setPontoReferencia("Alterado");

      session = HibernateUtil.abrirConexao();
      enderecoDao.saveOrUpdate(endereco, session);
      session.close();

      session = HibernateUtil.abrirConexao();
      Endereco enderecoAlterado = enderecoDao.searchId(endereco.getId(), session);
      session.close();

      assertEquals(endereco.getId(), enderecoAlterado.getId());
      assertEquals(endereco.getLogradouro(), enderecoAlterado.getLogradouro());

   }

   @Test
   public void testExcluir(){
      buscarEnderecoBD();

      session = HibernateUtil.abrirConexao();
      enderecoDao.delete(endereco, session);

      Endereco excluido = enderecoDao.searchId(endereco.getId(), session);
      session.close();

      assertNull(excluido);

   }

   public Endereco buscarEnderecoBD(){
      session = HibernateUtil.abrirConexao();
      List<Endereco> enderecos = session.createQuery("FROM Endereco e", Endereco.class).getResultList();
      session.close();

      if (enderecos.isEmpty()) {
         testSave();
      } else {
         endereco = enderecos.get(0);         
      }

      return endereco;
   }
}
