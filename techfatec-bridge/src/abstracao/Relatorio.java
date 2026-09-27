package abstracao;

import implementacao.FormatoExportacao;

/**
 * Abstraction do padrao Bridge.
 * <p>
 * Representa o relatorio em alto nivel, independente de COMO ele sera
 * exportado. Mantem uma referencia (agregacao) a um {@link FormatoExportacao},
 * recebida obrigatoriamente por injecao de dependencia no construtor -- em
 * nenhum ponto desta hierarquia um exportador concreto e instanciado com
 * {@code new}.
 */
public abstract class Relatorio {

    protected FormatoExportacao exportador;

    protected Relatorio(FormatoExportacao exportador) {
        this.exportador = exportador;
    }

    /**
     * Permite trocar o exportador em tempo de execucao (setter injection),
     * demonstrando o desacoplamento entre a Abstracao e a Implementacao sem a
     * necessidade de recriar o objeto Relatorio.
     */
    public void setExportador(FormatoExportacao exportador) {
        this.exportador = exportador;
    }

    public abstract void gerarRelatorio();
}
