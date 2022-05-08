package br.com.senac.dao;

import org.junit.Test;

import br.com.senac.entidades.Cargo;
import br.com.senac.util.GeradorUtil;

import static org.junit.Assert.*;

import org.hibernate.Session;


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


   
}
