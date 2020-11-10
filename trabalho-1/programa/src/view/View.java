package view;

import java.util.Scanner;
import java.io.IOException;

import observer.Observador;
import observer.NotificacaoFetch;
import observer.NotificacaoCache;
import observer.NotificacaoStats;
import observer.Notificacao;

public class View extends Observador 
{
    private Scanner scanner;

    public View()
    {
        scanner = new Scanner(System.in);
    }

    private void limparTela() throws IOException, InterruptedException
    {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }

    public void update()
    {
        super.update();
        if(super.notificacao instanceof NotificacaoFetch)
        {
            NotificacaoFetch not = (NotificacaoFetch)super.notificacao;
            System.out.println(not.getFetch());
        }
        if(super.notificacao instanceof NotificacaoCache)
        {
            NotificacaoCache not = (NotificacaoCache)super.notificacao;
            System.out.println(not.getCache());
        }
        if(super.notificacao instanceof NotificacaoStats)
        {
            NotificacaoStats not = (NotificacaoStats)super.notificacao;
            System.out.println(not.getStats());
        }
    }

    public String escolherPoliticaSubstituicao() 
    {
        String pol_subs = "";
        int opcao = 0;
        try
        {
            while(true)
            {
                limparTela();
                System.out.println("Escolha a politica de substituicao que sera utilizada.");
                System.out.println("1 - LRU\n2 - MRU\n3 - CLOCK\n4 - FIFO");
                opcao = scanner.nextInt();
                if(opcao >= 1 && opcao <= 4)
                {
                    if(opcao == 1)  
                        pol_subs = "LRU"; 
                    if(opcao == 2)
                        pol_subs = "MRU"; 
                    if(opcao == 3)
                        pol_subs = "CLOCK"; 
                    if(opcao == 4)
                        pol_subs = "FIFO";
                    break;
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("O programa parou de funcionar");
            System.out.println(e.getMessage());
        }
        catch(InterruptedException e)
        {
            System.out.println("O programa parou de funcionar");
            System.out.println(e.getMessage());
        }
        return pol_subs;
    }

    public String escolherOperacao()
    {
        String operacao = "";
        int opcao = 0;
        while(true)
        {
            System.out.println("Escolha uma operacao.");
            System.out.println("1 - FETCH\n2 - DISPLAY CACHE\n3 - DISPLAY STATS\n4 - EXIT");
            opcao = scanner.nextInt();
            if(opcao >= 1 && opcao <= 4)
            {
                if(opcao == 1)  
                    operacao = "FETCH"; 
                if(opcao == 2)
                    operacao = "DISPLAY CACHE"; 
                if(opcao == 3)
                    operacao = "DISPLAY STATS";
                if(opcao == 4)
                    operacao = "EXIT";
                break;
            }
        }
        return operacao;
    }

    public int escolherChave()
    {
        int chave;
        System.out.println("Escolha a pagina para carregar pela chave de busca");
        chave = scanner.nextInt();
        return chave;
    }
}