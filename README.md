# TechFatec — Módulo de Relatórios (Padrão Bridge)

Alunas: Yasmin Oliveira, Leticia Borges.

Projeto acadêmico (Fase 2 — Implementação e Controle de Versão) que expande o
módulo de relatórios de um sistema de inteligência de negócios. O sistema
legado gerava exclusivamente o **Relatório de Vendas** em **PDF**. O novo
requisito exige o **Relatório de Desempenho de RH** e a exportação de
**todos** os relatórios (atuais e futuros) em **PDF**, **Excel (XLSX)** e
**HTML**.

Para evitar a explosão combinatória de subclasses (N relatórios × M formatos)
e para respeitar o **Princípio Aberto/Fechado (OCP)** do SOLID, a solução
aplica o **Padrão de Projeto Bridge**.

## Por que Bridge?

Uma solução baseada apenas em herança (`RelatorioVendasPDF`,
`RelatorioVendasExcel`, `RelatorioRHPDF`, `RelatorioRHHTML`, ...) cresceria de
forma multiplicativa: cada novo relatório ou formato exigiria alterar classes
existentes. O Bridge separa **o que** o relatório representa (Abstração) de
**como** ele é exportado (Implementação), ligando as duas hierarquias por
**composição/agregação** em vez de herança. O crescimento passa a ser
aditivo (N + M classes), e nenhuma classe existente precisa ser modificada
para adicionar um novo relatório ou um novo formato.

## Diagramas

### Diagrama de Classes

<img width="2340" height="1080" alt="diagrama-classes" src="https://github.com/user-attachments/assets/67f64b03-cb84-4ed0-8889-6d8421212ed7" />

- **Abstração**: `Relatorio` (classe abstrata) → `RelatorioVendas`,
  `RelatorioRH`
- **Implementação**: `FormatoExportacao` (interface) → `ExportadorPDF`,
  `ExportadorExcel`, `ExportadorHTML`
- **Relação**: agregação entre `Relatorio` e `FormatoExportacao` via o
  atributo protegido `exportador`

### Diagrama de Sequência

<img width="1800" height="968" alt="diagrama-sequencia" src="https://github.com/user-attachments/assets/87293eed-4cce-457f-9800-8f0c017e155d" />

O cliente (`Main`) cria o exportador desejado, injeta-o no construtor do
relatório e chama `gerarRelatorio()`. Internamente, a abstração delega cada
etapa (`desenharCabecalho`, `desenharCorpo`, `finalizarArquivo`) ao objeto
`FormatoExportacao`, sem conhecer sua classe concreta.

## Estrutura de diretórios

```
techfatec-bridge/
├── README.md
├── docs/
│   ├── diagrama-classes.png
│   └── diagrama-sequencia.png
└── src/
    ├── abstracao/
    │   ├── Relatorio.java          # Abstraction
    │   ├── RelatorioVendas.java    # Refined Abstraction
    │   └── RelatorioRH.java        # Refined Abstraction
    ├── implementacao/
    │   ├── FormatoExportacao.java  # Implementor (interface)
    │   ├── ExportadorPDF.java      # Concrete Implementor
    │   ├── ExportadorExcel.java    # Concrete Implementor
    │   └── ExportadorHTML.java     # Concrete Implementor
    └── cliente/
        └── Main.java               # Classe cliente / script de validação
```

## Injeção de dependência

A dependência de `Relatorio` em relação a `FormatoExportacao` é **sempre**
injetada — nunca instanciada internamente com `new`:

```java
protected Relatorio(FormatoExportacao exportador) {
    this.exportador = exportador;
}

public void setExportador(FormatoExportacao exportador) {
    this.exportador = exportador; // troca dinâmica em tempo de execução
}
```

Somente a classe `cliente.Main` conhece e instancia os exportadores
concretos (`new ExportadorPDF()`, `new ExportadorExcel()`,
`new ExportadorHTML()`); as classes de relatório dependem apenas da
interface `FormatoExportacao`.

## Script de validação (Main)

`src/cliente/Main.java` executa as três rotinas exigidas, na ordem:

1. Geração do **Relatório de Vendas em PDF**.
2. Troca **dinâmica**, em tempo de execução, do **mesmo objeto**
   `RelatorioVendas` para o formato **Excel** (via `setExportador`, sem
   recriar o relatório).
3. Geração do **Relatório de Desempenho de RH em HTML**.

## Como compilar e executar

Pré-requisito: JDK 17+ instalado (`javac -version`).

```bash
# a partir da raiz do repositório
mkdir -p bin
javac -d bin $(find src -name "*.java")
java -cp bin cliente.Main
```

### Saída esperada (resumo)

```
======================================================
 1) Geracao do Relatorio de Vendas em PDF
======================================================
[PDF] Iniciando documento. Cabecalho: "Relatorio de Vendas"
...
[PDF] Documento finalizado e salvo como relatorio.pdf

======================================================
 2) MESMO objeto RelatorioVendas, exportador trocado
    dinamicamente em tempo de execucao para Excel
======================================================
[XLSX] Criando planilha. Titulo na celula A1: "Relatorio de Vendas"
...
[XLSX] Planilha finalizada e salva como relatorio.xlsx

======================================================
 3) Geracao do Relatorio de Desempenho de RH em HTML
======================================================
[HTML] <h1>Relatorio de Desempenho de RH</h1>
...
[HTML] Documento finalizado e salvo como relatorio.html
```

## Aderência aos requisitos da Fase 2

| Requisito | Onde |
|---|---|
| Separação física dos componentes | `src/abstracao/`, `src/implementacao/`, `src/cliente/` |
| Proibição de `new` do exportador dentro do relatório | Construtor de `Relatorio` recebe `FormatoExportacao` já pronto; nenhuma subclasse de `Relatorio` instancia exportadores |
| Injeção via construtor | `Relatorio(FormatoExportacao exportador)` |
| Alteração dinâmica em tempo de execução | `Relatorio.setExportador(...)`, demonstrado no passo 2 do `Main` |
| Geração de Vendas em PDF | `Main`, passo 1 |
| Troca dinâmica de Vendas para Excel | `Main`, passo 2 |
| Geração de RH em HTML | `Main`, passo 3 |


## Autoria

Projeto acadêmico — disciplina de Padrões de Projeto de Software (Fatec).




