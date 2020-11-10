package observer;

import java.util.Collection;

import observer.Notificacao;

public class NotificacaoCache extends Notificacao
{
    private Collection<String> cache;

    public NotificacaoCache(Collection<String> cache)
    {
        this.cache = cache;
    }

    public Collection<String> getCache()
    {
        return this.cache;
    }
}