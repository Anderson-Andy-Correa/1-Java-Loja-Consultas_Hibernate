package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Deprecated
    public List<Produto> buscarPorParametros(String nome,
                                             BigDecimal preco,
                                             LocalDate dataCadastro){
        String jpql = "SELECT p FROM Produto p WHERE 1 = 1";

        if (nome != null && !nome.trim().isEmpty()) {
            jpql.concat("AND p.nome = :nome");
        }
        if (preco != null) {
            jpql.concat("AND p.preco = :preco");
        }
        if (dataCadastro != null) {
            jpql.concat("AND p.dataCadastro = :dataCadastro");
        }
        TypedQuery<Produto> query = em.createQuery(jpql, Produto.class);

        if (nome != null && !nome.trim().isEmpty()) {
            //query.setParameter("nome", nome);
        }
        if (preco != null) {
            //query.setParameter("preco", preco);
        }
        if (dataCadastro != null) {
            //query.setParameter("dataCadastro", dataCadastro);
        }

        return query.getResultList();
    }

    public List<Produto> buscarPorParametrosComCriteria(String nome,
                                             BigDecimal preco,
                                             LocalDate dataCadastro){
        CriteriaBuilder buider = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = buider.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = buider.and();
        if (nome != null && !nome.trim().isEmpty()) {
            filtros = buider.and(filtros, buider.equal(from.get("nome"), nome));
        }
        if (preco != null) {
            filtros = buider.and(filtros, buider.equal(from.get("preco"), preco));
        }
        if (dataCadastro != null) {
            filtros = buider.and(filtros, buider.equal(from.get("dataCadastro"),dataCadastro));
        }
        query.where(filtros);

        return em.createQuery(query).getResultList();
    }
}
