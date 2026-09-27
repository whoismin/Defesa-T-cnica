package implementacao;

import java.util.List;

/**
 * Implementor do padrao Bridge.
 * <p>
 * Declara as operacoes primitivas que qualquer formato de exportacao deve
 * oferecer. A classe {@code Relatorio} (Abstracao) conhece apenas esta
 * interface, nunca uma implementacao concreta, o que permite acrescentar
 * novos formatos sem alterar a hierarquia de relatorios (OCP).
 */
public interface FormatoExportacao {

    void desenharCabecalho(String titulo);

    void desenharCorpo(List<String> dados);

    void finalizarArquivo();
}
