import java.util.Date;

public class Venda {
    private Date dataVenda;
    private Produto produto;
    private int quantidadeVendida;

    public Venda(Date dataVenda, Produto produto, int quantidadeVendida) {
        this.dataVenda = dataVenda;
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }
    public Date getDataVenda() {
        return dataVenda;
    }
    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }
    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    } 

    @Override
    public String toString() {
        return dataVenda + " | " + produto.getCodigo() + " | " + produto.getNome() + " | " + quantidadeVendida;  
    }

}
