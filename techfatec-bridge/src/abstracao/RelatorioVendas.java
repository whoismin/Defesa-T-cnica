package abstracao;

import implementacao.FormatoExportacao;

import java.util.List;

/**
 * Refined Abstraction: relatorio de vendas. Conhece apenas os proprios dados
 * de negocio; delega toda a formatacao de saida ao {@link FormatoExportacao}
 * injetado.
 */
public class RelatorioVendas extends Relatorio {

    private final List<String> dadosVendas;

    public RelatorioVendas(FormatoExportacao exportador, List<String> dadosVendas) {
        super(exportador);
        this.dadosVendas = dadosVendas;
    }

    @Override
    public void gerarRelatorio() {
        exportador.desenharCabecalho("Relatorio de Vendas");
        exportador.desenharCorpo(dadosVendas);
        exportador.finalizarArquivo();
    }
}
