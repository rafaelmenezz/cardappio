package br.com.senac.dao;

import br.com.senac.entidades.Cliente;
import br.com.senac.util.GeradorUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteDaoImplTest {

    private Cliente cliente;
    private ClienteDao clienteDao;
    private Session session;

    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

    // @Test
    public void testSave() {
        System.out.println("Teste salvar");
        cliente = new Cliente(GeradorUtil.gerarNome(), GeradorUtil.gerarCelular());

        session = HibernateUtil.abrirConexao();
        clienteDao.saveOrUpdate(cliente, session);
        session.close();

        assertNotNull(cliente);

    }

    //@Test
    public void testUpdate() {
        System.out.println("Teste Update");

        getClienteBD();

        cliente.setNome("Alterado: " + GeradorUtil.gerarNome());
        cliente.setTelefone("Alterado: " + GeradorUtil.gerarCelular());

        session = HibernateUtil.abrirConexao();
        clienteDao.saveOrUpdate(cliente, session);
        session.close();

        session = HibernateUtil.abrirConexao();
        Cliente clienteAlterado = clienteDao.searchId(cliente.getId(), session);
        session.close();

        assertEquals(cliente.getNome(), clienteAlterado.getNome());
        assertEquals(cliente.getTelefone(), clienteAlterado.getTelefone());

    }

   // @Test
    public void testSearchName() {
        System.out.println("Teste pesquisa por nome");

        getClienteBD();

        session = HibernateUtil.abrirConexao();
        List<Cliente> clientes = clienteDao.searchForName(cliente.getNome(), session);
        session.close();

        assertTrue(!clientes.isEmpty());
    }
    
    // @Test
    public void testSearchPhone() {
        System.out.println("Teste pesquisa por telefone");

        getClienteBD();

        session = HibernateUtil.abrirConexao();
        List<Cliente> clientes = clienteDao.searchForPhone(cliente.getTelefone(), session);
        session.close();

        assertTrue(!clientes.isEmpty());
    }
    
    @Test
    public void testDelete(){
        System.out.println("Teste excluir");
        
        getClienteBD();
        session = HibernateUtil.abrirConexao();
        clienteDao.delete(cliente, session);

        Cliente clienteExcluido = clienteDao.searchId(cliente.getId(), session);

        assertNull(clienteExcluido);
        
        
    }

    public Cliente getClienteBD() {

        session = HibernateUtil.abrirConexao();
        Query<Cliente> consulta = session.createQuery("From Cliente c");
        List<Cliente> clientes = consulta.getResultList();
        session.close();

        if (clientes.isEmpty()) {
            testSave();
        } else {
            cliente = clientes.get(0);
        }

        return cliente;
    }
}
