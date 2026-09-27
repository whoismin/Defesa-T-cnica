package implementacao;

import java.util.List;

/**
 * Concrete Implementor: simula a exportacao de um relatorio para o formato PDF.
 */
public class ExportadorPDF implements FormatoExportacao {

    @Override
    public void desenharCabecalho(String titulo) {
        System.out.println("[PDF] Iniciando documento. Cabecalho: \"" + titulo + "\"");
    }

    @Override
    public void desenharCorpo(List<String> dados) {
        System.out.println("[PDF] Renderizando corpo do relatorio:");
        for (String linha : dados) {
            System.out.println("       * " + linha);
        }
    }

    @Override
    public void finalizarArquivo() {
        System.out.println("[PDF] Documento finalizado e salvo como relatorio.pdf");
    }
}
