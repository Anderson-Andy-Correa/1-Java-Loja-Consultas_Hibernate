package br.com.alura.loja.testes;

import br.com.alura.loja.Util.JPAUtil;
import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.CategoriaId;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CadastroDeProduto {
    public static void main(String[] args) {
        cadastrarProduto();
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);

        Produto produto = produtoDao.buscarPorId(1l);
        System.out.println(produto.getPreco());

        List<Produto> lista = produtoDao.buscarPorNomeCategoria("CELULAREs");
        lista.forEach(p -> System.out.println(p.getCategoria().getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");
        System.out.println("Preco do Produto: " + precoDoProduto);
        em.close();
    }

    private static void cadastrarProduto() {
        Categoria celulares = new Categoria("CELULARES", "XPTOa");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal",
                new BigDecimal("800"), celulares);

        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDao categoriaDao = new CategoriaDao(em);

        em.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        em.find(Categoria.class, new CategoriaId("CELULARES", "XPTO"));

        em.getTransaction().commit();

        em.close();
    }
}
