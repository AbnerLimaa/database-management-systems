package observer;

import observer.Notificacao;

public class NotificacaoStats extends Notificacao
{
    private String stats;

    public NotificacaoStats(String stats)
    {
        this.stats = stats;
    }

    public String getStats()
    {
        return this.stats;
    }
}