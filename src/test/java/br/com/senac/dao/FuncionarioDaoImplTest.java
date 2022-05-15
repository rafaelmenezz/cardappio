package br.com.senac.dao;

import br.com.senac.entidades.Funcionario;
import br.com.senac.util.GeradorUtil;
//import java.util.List;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class FuncionarioDaoImplTest {

   private Funcionario funcionario;
   private FuncionarioDao funcionarioDao;
   private Session session;

   public FuncionarioDaoImplTest(){
      funcionarioDao = new FuncionarioDaoImpl();
   }

   @Test
   public void testSave(){
      System.out.println("Teste salvar Funcionario");

      funcionario = new Funcionario(GeradorUtil.gerarNome(), 
                                    GeradorUtil.gerarCpf(), 
                                    GeradorUtil.gerarCelular(), 
                                    GeradorUtil.gerarEmail(), 
                                    GeradorUtil.gerarSenha(8));

      funcionario.setCargo(new CargoDaoImplTest().busCargoBd());

      session = HibernateUtil.abrirConexao();
      funcionarioDao.saveOrUpdate(funcionario, session);
      session.close();

      assertNotNull(funcionario.getId());
   }

   @Test
   public void testUpdate(){
      buscarFuncionarioBD();

      funcionario.setNome(GeradorUtil.gerarNome());
      funcionario.setCpf(GeradorUtil.gerarCpf());
      funcionario.setEmail(GeradorUtil.gerarEmail());
      funcionario.setCargo(new CargoDaoImplTest().busCargoBd());

      session = HibernateUtil.abrirConexao();
      funcionarioDao.saveOrUpdate(funcionario, session);
      session.close();

      session = HibernateUtil.abrirConexao();
      Funcionario alterado = funcionarioDao.searchId(funcionario.getId(), session);
      session.close();

      assertEquals(funcionario.getId(), alterado.getId());

   }

   @Test
   public void testDelete(){
      buscarFuncionarioBD();

      session = HibernateUtil.abrirConexao();
      funcionarioDao.delete(funcionario, session);

      session = HibernateUtil.abrirConexao();
      Funcionario excluido = funcionarioDao.searchId(funcionario.getId(), session);
      session.close();

      assertNull(excluido);
   }

   @Test
   public void testLogin(){
      buscarFuncionarioBD();

      session = HibernateUtil.abrirConexao();
      Funcionario login = funcionarioDao.login(funcionario.getEmail(), funcionario.getSenha(), session);
      session.close();

      assertEquals(funcionario.getId(), login.getId());
   }

   @Test
   public void testPesquisaPorNomeOuTelefone(){
      buscarFuncionarioBD();

      session = HibernateUtil.abrirConexao();
      Funcionario forNome = funcionarioDao.searchForNameOrPhone(funcionario.getNome(), session).get(0);
      session.close();

      session = HibernateUtil.abrirConexao();
      Funcionario forTelefone = funcionarioDao.searchForNameOrPhone(funcionario.getTelefone(), session).get(0);
      session.close();

      assertEquals(forNome.getId(), forTelefone.getId());


   }

   public Funcionario buscarFuncionarioBD(){
      session = HibernateUtil.abrirConexao();
      List<Funcionario> funcionarios = session.createQuery("FROM Funcionario f", Funcionario.class).getResultList();
      session.close();

      if (funcionarios.isEmpty()) {
         testSave();
      } else {
         int indice = (int) (Math.random() * funcionarios.size());
         funcionario = funcionarios.get(indice);
      }

      return funcionario;
   }
}
