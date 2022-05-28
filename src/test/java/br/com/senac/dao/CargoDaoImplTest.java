package br.com.senac.dao;

import org.junit.Test;

import br.com.senac.entidades.Cargo;
import br.com.senac.entidades.Funcionario;
import br.com.senac.util.GeradorUtil;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;


public class CargoDaoImplTest {

   private Cargo cargo;
   private CargoDao cargoDao;
   private Session session;

   public CargoDaoImplTest(){
      cargoDao = new CargoDaoImpl();
   }

   @Test
   public void testSave(){
      System.out.println("Teste Salvar");

      session = HibernateUtil.abrirConexao();
      cargo = new Cargo(GeradorUtil.gerarCargo());
      cargoDao.saveOrUpdate(cargo, session);
      session.close();

      assertNotNull(cargo);

   }

   @Test
   public void testUpdate(){
      busCargoBd();

      cargo.setCargo("Alt: "+GeradorUtil.gerarCargo());

      session = HibernateUtil.abrirConexao();
      cargoDao.saveOrUpdate(cargo, session);
      session.close();

      session =HibernateUtil.abrirConexao();
      Cargo cargoAlterado = cargoDao.searchId(cargo.getId(), session);
      session.close();

      assertEquals(cargo.getCargo(), cargoAlterado.getCargo());

   }

   @Test
   public void testExcluir(){
      System.out.println("Teste Excluir");

      busCargoBd();
      session = HibernateUtil.abrirConexao();
      cargoDao.delete(cargo, session);
      session.close();

      session = HibernateUtil.abrirConexao();
      Cargo excluido = cargoDao.searchId(cargo.getId(), session);


      assertNull(excluido);
   }

   @Test
   public void testSearchForCargo(){
      busCargoBd();

      session = HibernateUtil.abrirConexao();
      List<Cargo> search = cargoDao.searchForCargo(cargo.getCargo(), session);
      session.close();

      assertEquals(cargo.getCargo(), search.get(0).getCargo());
   }

   @Test
   public void testFetchCargos(){
      session = HibernateUtil.abrirConexao();
      List<Cargo> cargos = cargoDao.fetchLCargos(session);
      session.close();

      mostrar(cargos);

      assertTrue(!cargos.isEmpty());
   }

   @Test
   public void testListarFuncionarios(){
      
      busCargoBd();

      session = HibernateUtil.abrirConexao();

      List<Cargo> cargos = cargoDao.listFuncionarios(cargo, session);
      session.close();

      assertTrue(!cargos.isEmpty());
      
   }

   public Cargo busCargoBd(){
      System.out.println("Buscar um cargo no base de dados");

      session = HibernateUtil.abrirConexao();
      Query<Cargo> consulta = session.createQuery("from Cargo c", Cargo.class); 
      List<Cargo> cargos = consulta.list();
      session.close();

      if (cargos.isEmpty()) {
         testSave(); 
      } else {
         int indice = (int) (Math.random() * cargos.size());
         cargo = cargos.get(indice);
      }

      return cargo;
   }

   private void mostrar(List<Cargo> cargos){
      System.out.println("Tamanho dos cargos " + cargos.size());
      System.out.println("");
      for (Cargo car : cargos) {
          System.out.println("Id Cargo " + car.getId());
          System.out.println("Cargo " + car.getCargo());
          System.out.println("Funcionarios");
          System.out.println("");
          if(car.getFuncionarios() != null){
            for (Funcionario funcionario : car.getFuncionarios()) {
               System.out.println("Nome " + funcionario.getNome());
               System.out.println("Email " + funcionario.getEmail());
               System.out.println("Telefone " + funcionario.getTelefone());
               System.out.println("");
           }
          }
        
          System.out.println("");
     }    
 } 
   
}
