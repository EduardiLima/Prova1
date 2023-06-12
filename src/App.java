import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class App {
    private static List<Produto> listaProdutos = new ArrayList<>();
    private static List<Venda> listaVendas = new ArrayList<>();
    private static List<Compra> listaCompras = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    incluirProduto(scanner);
                    break;
                case 2:
                    consultarProduto(scanner);
                    break;
                case 3:
                    listarProdutos();
                    break;
                case 4:
                    vendasPorPeriodoDetalhado(scanner);
                    break;
                case 5:
                    realizarVenda(scanner);
                    break;
                case 6:
                    realizarCompra(scanner);
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("------ Menu ------");
        System.out.println("1 - Incluir produto");
        System.out.println("2 - Consultar produto");
        System.out.println("3 - Listagem de produtos");
        System.out.println("4 - Vendas por período - detalhado");
        System.out.println("5 - Realizar venda");
        System.out.println("6 - Realizar compra");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void incluirProduto(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha

        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o valor unitário do produto: ");
        double valor = scanner.nextDouble();

        System.out.print("Digite a quantidade em estoque: ");
        int quantidadeEstoque = scanner.nextInt();

        Produto produto = new Produto(codigo, nome, valor, quantidadeEstoque);
        listaProdutos.add(produto);

        System.out.println("Produto adicionado com sucesso!");
    }

    private static void consultarProduto(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();

        for (Produto produto : listaProdutos) {
            if (produto.getCodigo() == codigo) {
                System.out.println("Detalhes do produto:");
                System.out.println("Código: " + produto.getCodigo());
                System.out.println("Nome: " + produto.getNome());
                System.out.println("Valor unitário: " + produto.getValor());
                System.out.println("Quantidade em estoque: " + produto.getQuantidadeEstoque());
                return;
            }
        }

        System.out.println("Produto não encontrado.");
    }

    private static void listarProdutos() {
        if (listaProdutos.isEmpty()) {
            System.out.println("Não há produtos cadastrados.");
            return;
        }

        System.out.println("------ Listagem de Produtos ------");
        System.out.println("Código | Nome | Valor Unitário | Quantidade em Estoque");
        for (Produto produto : listaProdutos) {
            System.out.println(produto);
        }
    }

    private static void vendasPorPeriodoDetalhado(Scanner scanner) {
        System.out.print("Digite a data de início (dd/mm/aaaa): ");
        String dataInicioStr = scanner.nextLine();

        System.out.print("Digite a data de fim (dd/mm/aaaa): ");
        String dataFimStr = scanner.nextLine();

        Date dataInicio = formatarData(dataInicioStr);
        Date dataFim = formatarData(dataFimStr);

        if (dataInicio == null || dataFim == null) {
            System.out.println("Datas inválidas. Verifique o formato.");
            return;
        }

        System.out.println("------ Vendas por Período (Detalhado) ------");
        System.out.println("Data da Venda | Código do Produto | Nome do Produto | Quantidade Vendida");

        for (Venda venda : listaVendas) {
            Date dataVenda = venda.getDataVenda();
            if (dataVenda.after(dataInicio) && dataVenda.before(dataFim)) {
                Produto produto = venda.getProduto();
                System.out.println(dataVenda + " | " + produto.getCodigo() + " | " + produto.getNome() + " | "
                        + venda.getQuantidadeVendida());
            }
        }
    }

    private static Date formatarData(String dataInicioStr) {
        return null;
    }

    private static void realizarVenda(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        int codigoProduto = scanner.nextInt();

        Produto produto = buscarProdutoPorCodigo(codigoProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite a quantidade vendida: ");
        int quantidadeVendida = scanner.nextInt();

        if (quantidadeVendida <= 0 || quantidadeVendida > produto.getQuantidadeEstoque()) {
            System.out.println("Quantidade inválida. Verifique o estoque do produto.");
            return;
        }

        Date dataVenda = new Date();
        Venda venda = new Venda(dataVenda, produto, quantidadeVendida);
        listaVendas.add(venda);

        // Atualizar quantidade em estoque
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidadeVendida);

        System.out.println("Venda realizada com sucesso!");
    }

    private static void realizarCompra(Scanner scanner) {
        System.out.print("Digite o código do produto: ");
        int codigoProduto = scanner.nextInt();

        Produto produto = buscarProdutoPorCodigo(codigoProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Digite a quantidade comprada: ");
        int quantidadeComprada = scanner.nextInt();

        if (quantidadeComprada <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        Date dataCompra = new Date();
        Compra compra = new Compra(dataCompra, produto, quantidadeComprada);
        listaCompras.add(compra);

        // Atualizar quantidade em estoque
        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidadeComprada);

        System.out.println("Compra realizada com sucesso!");
    }

    private static Produto buscarProdutoPorCodigo(int codigo) {
        for (Produto produto : listaProdutos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public Date converterStringParaData(String dataStr) throws ParseException {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        return formatoData.parse(dataStr);
    }

}

