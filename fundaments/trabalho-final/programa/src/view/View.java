package view;

import java.util.Scanner;
import java.util.LinkedList;

import model.Entidade;
import observer.Observador;
import observer.NotificacaoTabela;

public class View extends Observador
{
    private boolean continua;
    private Scanner scanner;

    public View()
    {
        this.continua = true;
        this.scanner = new Scanner(System.in);
    }

    public boolean getContinua()
    {
        return this.continua;
    }

    public int telaInicial()
    {
        int opcao = 0;
        while(opcao < 1 || opcao > 3)
        {
            System.out.println("Escolha a operacao a ser realizada:");
            System.out.println("1 - Inserir uma playlist.");
            System.out.println("2 - Atualizar dados de um album.");
            System.out.println("3 - Sair");
            opcao = scanner.nextInt();
        }
        if(opcao == 3)
            this.continua = false;
        return opcao;
    }

    public void imprimirTexto(String texto)
    {
        System.out.println(texto);
    }

    public String lerTexto(String texto)
    {
        System.out.println(texto);
        scanner.nextLine();
        return scanner.nextLine();
    }

    public void imprimirTabela(LinkedList<Entidade> tabela)
    {
        for(Entidade item : tabela)
            System.out.println(item.toString());
        System.out.println();
    }

    public int escolherInteiro(String texto)
    {
        int opcao;
        System.out.println(texto);
        opcao = scanner.nextInt();
        return opcao;
    }

    public LinkedList<Integer> escolherListaInteiros(String texto)
    {
        LinkedList<Integer> lista = new LinkedList<Integer>();
        boolean proximo = true;
        while(proximo)
        {
            System.out.println(texto);
            int opcao = scanner.nextInt();
            lista.add(new Integer(opcao));
            System.out.println("continuar?(s/n)");
            if(scanner.next().equals("n"))
                proximo = false;
        }
        return lista;
    }

    public void update()
    {
        super.update();
        if(super.notificacao instanceof NotificacaoTabela)
        {
            NotificacaoTabela notificacao = (NotificacaoTabela)super.notificacao;
            this.imprimirTabela(notificacao.getTabela());
        }
    }
}