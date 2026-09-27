package implementacao;

import java.util.List;

/**
 * Concrete Implementor: simula a exportacao de um relatorio para o formato
 * Excel (XLSX).
 */
public class ExportadorExcel implements FormatoExportacao {

    @Override
    public void desenharCabecalho(String titulo) {
        System.out.println("[XLSX] Criando planilha. Titulo na celula A1: \"" + titulo + "\"");
    }

    @Override
    public void desenharCorpo(List<String> dados) {
        System.out.println("[XLSX] Preenchendo linhas da planilha:");
        int linha = 2;
        for (String dado : dados) {
            System.out.println("       Linha " + linha + " -> " + dado);
            linha++;
        }
    }

    @Override
    public void finalizarArquivo() {
        System.out.println("[XLSX] Planilha finalizada e salva como relatorio.xlsx");
    }
}
