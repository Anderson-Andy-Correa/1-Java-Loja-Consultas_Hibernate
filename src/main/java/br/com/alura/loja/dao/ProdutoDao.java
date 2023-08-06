package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {

    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar (Produto produto){
        this.em.persist(produto);
    }

    public void atualizar (Produto produto) { this.em.merge(produto); }

    public void remover (Produto produto) {
        produto = this.em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(Long id) { return this.em.find(Produto.class, id); }

    public List<Produto> buscarTodos() {
        //return this.em.createQuery("SELECT p FROM Produto p").getResultList();
        return this.em.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome (String nome) {
        return this.em.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public List<Produto> buscarPorNomeCategoria (String nome) {
        return this.em.createNamedQuery("Produtos.produtosPorCategoria", Produto.class)
                .setParameter("nome", nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("nome", nome)
                .getResultList().get(0);
    }
}
