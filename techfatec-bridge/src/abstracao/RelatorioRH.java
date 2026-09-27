package abstracao;

import implementacao.FormatoExportacao;

import java.util.List;

/**
 * Refined Abstraction: relatorio de desempenho de RH. Assim como
 * {@link RelatorioVendas}, nao possui qualquer conhecimento sobre formatos de
 * arquivo -- apenas sobre seus proprios dados de negocio.
 */
public class RelatorioRH extends Relatorio {

    private final List<String> dadosDesempenho;

    public RelatorioRH(FormatoExportacao exportador, List<String> dadosDesempenho) {
        super(exportador);
        this.dadosDesempenho = dadosDesempenho;
    }

    @Override
    public void gerarRelatorio() {
        exportador.desenharCabecalho("Relatorio de Desempenho de RH");
        exportador.desenharCorpo(dadosDesempenho);
        exportador.finalizarArquivo();
    }
}
