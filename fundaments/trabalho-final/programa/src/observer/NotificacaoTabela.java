package observer;

import java.util.LinkedList;

import model.Entidade;
import observer.Notificacao;

public class NotificacaoTabela extends Notificacao
{
    private LinkedList<Entidade> tabela;

    public NotificacaoTabela(LinkedList<Entidade> tabela)
    {
        this.tabela = tabela;
    }

    public LinkedList<Entidade> getTabela()
    {
        return this.tabela;
    }
}