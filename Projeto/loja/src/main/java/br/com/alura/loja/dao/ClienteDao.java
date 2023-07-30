package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ClienteDao {

    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar (Cliente cliente){
        this.em.persist(cliente);
    }

    public void atualizar (Cliente cliente) { this.em.merge(cliente); }

    public void remover (Cliente cliente) {
        cliente = this.em.merge(cliente);
        this.em.remove(cliente);
    }

    public Cliente buscarPorId(Long id) { return this.em.find(Cliente.class, id); }

    public List<Cliente> buscarTodos() {
        return this.em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public List<Cliente> buscarPorNome (String nome) {
        return this.em.createQuery("SELECT c FROM Cliente c WHERE c.nome = :nome", Cliente.class)
                .setParameter("nome", nome)
                .getResultList();
    }

}
