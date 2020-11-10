package controller;

import java.util.LinkedList;
import java.time.LocalDate;

import view.View;
import model.Model;

public class Gerenciador
{
    private Model model;
    private View view;

    public Gerenciador()
    {
        model = new Model();
        view = new View();
        view.setObservavel(model);
        model.adicionarObservador(view);
    }
    public void iniciar()
    {
        while(view.getContinua())
        {
            int opcao = view.telaInicial();
            if(opcao == 1)
                this.inserirPlaylist();
            if(opcao == 2)
                this.atualizarAlbum();
        }
    }

    private void inserirPlaylist()
    {
        model.getListaAlbuns();
        int cod_album = view.escolherInteiro("Escolha o album");
        model.getFaixasAlbum(cod_album);
        LinkedList<Integer> faixas = view.escolherListaInteiros("Escolha uma faixa");
        String nome_playlist = view.lerTexto("Insira o nome da playlist");
        model.adicionarPlaylist(faixas, nome_playlist, LocalDate.now().toString());
    }

    private void atualizarAlbum()
    {
        String descricao = view.lerTexto("Digite a descricao de um album ou parte dela");
        model.procurarAlbum(descricao);
        int opcao = view.escolherInteiro("Qual atributo mudar?\n1 - cd\n2 - descricao\n3 - data");
        String valor = view.lerTexto("Insira o novo valor");
        model.atualizarAtributoAlbum(opcao, valor);
    }
}