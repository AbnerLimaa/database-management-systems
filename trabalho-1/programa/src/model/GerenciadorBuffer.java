package model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import observer.Observavel;
import observer.NotificacaoCache;
import observer.NotificacaoFetch;
import observer.NotificacaoStats;
import model.LeitorArquivo;

public class GerenciadorBuffer extends Observavel
{
    private LeitorArquivo leitor;
    private String pol_subs;
    private Map<Integer, String> cache;
    private ArrayList<String> stats; 

    public GerenciadorBuffer()
    {
        pol_subs = "";
        cache = new HashMap<>(5);
        stats = new ArrayList<>();
        try
        {
            leitor = new LeitorArquivo();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Nao foi possivel abrir o arquivo");
            System.out.println(e.getMessage());
        }
    }

    public void setPoliticaSubstituicao(String politica_substituicao)
    {
        this.pol_subs = politica_substituicao;
    }

    public String getPoliticaSubstituicao()
    {
        return pol_subs;
    }

    private int lru()
    {
        return 10;
    }

    private int mru()
    {
        return 10;
    }

    private int clock()
    {
        return 10;
    }

    private int fifo()
    {
        return 10;
    }

    public void fetch(int key)
    {
        String valor = cache.get(new Integer(key));
        if(valor == null)
        {
            int rem_key;
            stats.add("Cache miss");
            rem_key = evict();
            valor = leitor.lerPagina(key);
            cache.put(new Integer(key), valor);
        }
        else
            stats.add("Cache hit");
        super.notificacao = new NotificacaoFetch(valor);
        super.notificarObservadores();
    }

    private int evict()
    {
        int key = 9;
        switch(pol_subs)
        {
            case "": System.out.println("Politica de substituicao nao foi declarada");
                break;
            case "LRU": key = lru();
                break;
            case "MRU": key = mru();
                break;
            case "CLOCK": key = clock();
                break;
            case "FIFO": key = fifo();
                break;
            default: System.out.println("Politica de substituicao invalida");
                break;
        }
        return key;
    }

    public void displayCache()
    {
        super.notificacao = new NotificacaoCache(cache.values());
        super.notificarObservadores();
    }

    public void displayStats()
    {
        super.notificacao = new NotificacaoStats(stats.toString());
        super.notificarObservadores();
    }
}