import Cliente.Cliente;
import Dao.ClienteDAO;
import Dao.ClienteDaoMock;
import Dao.IClienteDAO;
import Service.ClienteService;
import Service.IClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteTest {

    private IClienteService clienteService;

    private Cliente cliente;

    public ClienteTest() {

        IClienteDAO dao = new ClienteDaoMock();
        clienteService = new ClienteService(dao);
    }
    @Before
    public void init() {
        cliente = new Cliente();
        cliente.setCpf(12345678910L);
        cliente.setNome("Queiroga");
        cliente.setCidade("Barueri");
        cliente.setEnd("Av Tucunare");
        cliente.setEstado("SP");
        cliente.setNumero(1000);
        cliente.setTel(11972378888L);
        clienteService.salvar(cliente);

    }

    @Test
    public void pesquisarCliente() {

        Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());

        Assert.assertNotNull(clienteConsultado);

    }

}