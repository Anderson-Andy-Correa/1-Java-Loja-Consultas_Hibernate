package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Itens_Pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    public ItemPedido(Integer quantidade, Produto produto, Pedido pedido) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.pedido = pedido;
        this.valorUnitario = produto.getPreco();
    }

    public ItemPedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getValor() {
        return this.valorUnitario.multiply(new BigDecimal(this.quantidade));
    }
}
