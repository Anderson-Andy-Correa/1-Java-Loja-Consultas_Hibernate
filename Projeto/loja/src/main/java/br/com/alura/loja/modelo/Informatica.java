package br.com.alura.loja.modelo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "informaticas")
public class Informatica extends Produto{

    private String marca;
    private String modelo;

    public Informatica(String nome, String descricao, BigDecimal preco, Categoria categoria, String autor, Integer numeroDePaginas) {
        super(nome, descricao, preco, categoria);
        this.marca = marca;
        this.modelo = modelo;
    }

    public Informatica() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
