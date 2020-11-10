package controller;

import java.io.IOException;

import view.View;
import model.GerenciadorBuffer;

public class Controlador
{
    private View view;
    private GerenciadorBuffer gerenciador;

    public Controlador()
    {
        view = new View();
        gerenciador = new GerenciadorBuffer();
        gerenciador.adicionarObservador(view);
        view.setObservavel(gerenciador);
    }

    public void iniciar()
    {
        gerenciador.setPoliticaSubstituicao(view.escolherPoliticaSubstituicao()); 
        boolean continua = true;
        while(continua)
        {
            String operacao = view.escolherOperacao();
            switch(operacao)
            {
                case "FETCH":
                {
                    int chave = view.escolherChave();
                    gerenciador.fetch(chave);
                }
                    break;
                case "DISPLAY CACHE": gerenciador.displayCache();
                    break;
                case "DISPLAY STATS": gerenciador.displayStats();
                    break;
                case "EXIT": continua = false;
                    break;
                default: System.out.println("Operacao invalida");
                    break;
            }
        }
    }
}