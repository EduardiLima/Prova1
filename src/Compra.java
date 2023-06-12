import java.util.Date;

public class Compra {
    private Date dataCompra;
    private Produto produto;
    private int quantidadeComprada;

    public Compra(Date dataCompra, Produto produto, int quantidadeComprada) {

    }
    public Date getDataCompra() {
        return dataCompra;
    }
    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public int getQuantidadeComprada() {
        return quantidadeComprada;
    }
    public void setQuantidadeComprada(int quantidadeComprada) {
        this.quantidadeComprada = quantidadeComprada;
    }
    @Override
    public String toString() {
        return dataCompra + " | " + produto.getCodigo() + " | " + produto.getNome() + " | " + quantidadeComprada;
    }
    
}
