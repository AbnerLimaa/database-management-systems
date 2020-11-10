package observer;

import observer.Notificacao;

public class NotificacaoFetch extends Notificacao
{
    private String fetch;

    public NotificacaoFetch(String fetch)
    {
        this.fetch = fetch;
    }

    public String getFetch()
    {
        return this.fetch;
    }
}