package implementacao;

import java.util.List;

/**
 * Concrete Implementor: simula a exportacao de um relatorio para o formato HTML.
 */
public class ExportadorHTML implements FormatoExportacao {

    @Override
    public void desenharCabecalho(String titulo) {
        System.out.println("[HTML] <h1>" + titulo + "</h1>");
    }

    @Override
    public void desenharCorpo(List<String> dados) {
        System.out.println("[HTML] <ul>");
        for (String linha : dados) {
            System.out.println("         <li>" + linha + "</li>");
        }
        System.out.println("[HTML] </ul>");
    }

    @Override
    public void finalizarArquivo() {
        System.out.println("[HTML] Documento finalizado e salvo como relatorio.html");
    }
}
