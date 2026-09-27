package cliente;

import abstracao.Relatorio;
import abstracao.RelatorioRH;
import abstracao.RelatorioVendas;
import implementacao.ExportadorExcel;
import implementacao.ExportadorHTML;
import implementacao.ExportadorPDF;
import implementacao.FormatoExportacao;

import java.util.Arrays;
import java.util.List;

/**
 * Classe cliente. E o UNICO ponto do sistema que conhece e instancia
 * exportadores concretos ({@code new ExportadorX()}); a injecao no
 * construtor / setter de {@code Relatorio} e o que caracteriza o Bridge.
 * <p>
 * Executa a rotina de validacao exigida na Fase 2:
 * 1) Relatorio de Vendas em PDF
 * 2) O MESMO relatorio de Vendas trocado dinamicamente para Excel
 * 3) Relatorio de RH em HTML
 */
public class Main {

    public static void main(String[] args) {
        List<String> dadosVendas = Arrays.asList(
                "Pedido #1021 - Cliente: Loja Ipe Norte - R$ 4.590,00",
                "Pedido #1022 - Cliente: Distribuidora Sul - R$ 1.230,00",
                "Pedido #1023 - Cliente: Mercado Central - R$ 8.760,00"
        );

        List<String> dadosRH = Arrays.asList(
                "Colaborador: Ana Silva - Departamento: TI - Desempenho: 92%",
                "Colaborador: Bruno Costa - Departamento: Vendas - Desempenho: 87%",
                "Colaborador: Carla Souza - Departamento: RH - Desempenho: 95%"
        );

        System.out.println("======================================================");
        System.out.println(" 1) Geracao do Relatorio de Vendas em PDF");
        System.out.println("======================================================");
        FormatoExportacao exportadorPDF = new ExportadorPDF();
        Relatorio relatorioVendas = new RelatorioVendas(exportadorPDF, dadosVendas);
        relatorioVendas.gerarRelatorio();

        System.out.println();
        System.out.println("======================================================");
        System.out.println(" 2) MESMO objeto RelatorioVendas, exportador trocado");
        System.out.println("    dinamicamente em tempo de execucao para Excel");
        System.out.println("======================================================");
        FormatoExportacao exportadorExcel = new ExportadorExcel();
        relatorioVendas.setExportador(exportadorExcel);
        relatorioVendas.gerarRelatorio();

        System.out.println();
        System.out.println("======================================================");
        System.out.println(" 3) Geracao do Relatorio de Desempenho de RH em HTML");
        System.out.println("======================================================");
        FormatoExportacao exportadorHTML = new ExportadorHTML();
        Relatorio relatorioRH = new RelatorioRH(exportadorHTML, dadosRH);
        relatorioRH.gerarRelatorio();

        System.out.println();
        System.out.println("Desacoplamento validado: nenhuma classe de abstracao/");
        System.out.println("relatorio precisou ser alterada para suportar o novo");
        System.out.println("relatorio (RH) nem o novo formato de saida (Excel/HTML).");
    }
}
