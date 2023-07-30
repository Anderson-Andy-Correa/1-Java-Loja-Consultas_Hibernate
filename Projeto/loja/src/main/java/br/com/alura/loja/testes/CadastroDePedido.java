package br.com.alura.loja.testes;

import br.com.alura.loja.Util.JPAUtil;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.*;
import br.com.alura.loja.vo.RelatorioDeVendasVO;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDePedido {
    public static void main(String[] args) {
        populatBancoDados();

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        ClienteDao clienteDao = new ClienteDao(em);

        Produto produto = produtoDao.buscarPorId(1l);
        Cliente cliente = clienteDao.buscarPorId(1l);

        em.getTransaction().begin();

        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(10, produto, pedido));

        PedidoDao pedidoDao = new PedidoDao(em);
        pedidoDao.cadastrar(pedido);

        em.getTransaction().commit();

        BigDecimal totalVendido = pedidoDao.valorTotalVendido();
        System.out.println("Valor Total Vendido: " + totalVendido);

        List<RelatorioDeVendasVO> relatorio = pedidoDao.relatorioVendas();
        relatorio.forEach(System.out::println);

    }

    private static void populatBancoDados() {
        CadastroDeProduto.main(null);
        Cliente cliente = new Cliente("Alexia", "12345678901");

        EntityManager em = JPAUtil.getEntityManager();
        ClienteDao clienteDao = new ClienteDao(em);

        em.getTransaction().begin();
        clienteDao.cadastrar(cliente);
        em.getTransaction().commit();
        em.close();
    }
}
