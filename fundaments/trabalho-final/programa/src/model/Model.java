package model;

import java.util.LinkedList;

import observer.NotificacaoTabela;
import observer.Observavel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model extends Observavel
{
    //private String connectionUrl = "jdbc:sqlserver://DESKTOP-VVCQNS2:1433;databaseName=BDSpotPer;integratedSecurity=true";
    private String connectionUrl = "jdbc:sqlserver://LEC48:49330;databaseName=BDSpotPer398067;user=SA;password=Admin123";
    private String cod_ult_album = "";

    public void getListaAlbuns()
    {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) 
        {
            String SQL = "SELECT * FROM dbo.Album";
            ResultSet rs = stmt.executeQuery(SQL);
            LinkedList<Entidade> lista_albuns = new LinkedList<Entidade>();
            LinkedList<String> label = new LinkedList<String>();
            label.add("codigo");
            label.add("cd");
            label.add("descricao");
            label.add("data");
            lista_albuns.add(new Entidade(label));
            while (rs.next()) 
            {
                LinkedList<String> colunas = new LinkedList<String>();
                colunas.add(rs.getString("Cod_Alb"));
                colunas.add(rs.getString("CD_Alb"));
                colunas.add(rs.getString("Desc_Alb"));
                colunas.add(rs.getString("DataGrav_Alb"));
                lista_albuns.add(new Entidade(colunas));
            }
            super.notificacao = new NotificacaoTabela(lista_albuns);
            super.notificarObservadores();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void getFaixasAlbum(int codigo)
    {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) 
        {
            String SQL = "SELECT * FROM dbo.Faixa WHERE Alb_Faixa = " + Integer.toString(codigo);
            ResultSet rs = stmt.executeQuery(SQL);
            LinkedList<Entidade> lista_faixas = new LinkedList<Entidade>();
            LinkedList<String> label = new LinkedList<String>();
            label.add("codigo");
            label.add("descricao");
            label.add("numero");
            label.add("tipo");
            label.add("tempo");
            lista_faixas.add(new Entidade(label));
            while (rs.next()) 
            {
                LinkedList<String> colunas = new LinkedList<String>();
                colunas.add(rs.getString("Cod_Faixa"));
                colunas.add(rs.getString("Desc_Faixa"));
                colunas.add(rs.getString("Num_Faixa"));
                colunas.add(rs.getString("Tipo_Faixa"));
                colunas.add(rs.getString("Tempo_Faixa"));
                lista_faixas.add(new Entidade(colunas));
            }
            super.notificacao = new NotificacaoTabela(lista_faixas);
            super.notificarObservadores();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void adicionarPlaylist(LinkedList<Integer> cod_faixas, String nome, String data)
    {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) 
        {
            int tempo = 0;
            for(Integer codigo : cod_faixas)
            {
                String consulta_cod = "SELECT Tempo_Faixa FROM dbo.Faixa WHERE Cod_Faixa = " + codigo.toString();
                ResultSet tempo_faixa = stmt.executeQuery(consulta_cod);
                tempo_faixa.next();
                tempo += Integer.parseInt(tempo_faixa.getString(1));
            }
            String inserir_playlist = "INSERT INTO dbo.Playlist VALUES('" + nome + "', '" + data + "', " + Integer.toString(tempo) + ");";
            stmt.execute(inserir_playlist);
            ResultSet playlist_adicionada = stmt.executeQuery("SELECT TOP 1 Cod_Play FROM dbo.Playlist ORDER BY Cod_Play DESC");
            playlist_adicionada.next();
            String codigo_playlist = playlist_adicionada.getString(1);
            for(Integer codigo : cod_faixas)
            {
                String inserir_faixa_play = "INSERT INTO dbo.Agrupa_Faixa_Play VALUES(" + codigo.toString() + ", " + codigo_playlist + ", '" + data + "', 0);";
                stmt.execute(inserir_faixa_play);
            }
            String consulta_playlist = "SELECT p.Nome_Play AS Playlist, f.Desc_Faixa AS Faixa FROM dbo.Playlist p, dbo.Agrupa_Faixa_Play a, dbo.Faixa f WHERE p.Cod_Play = " + codigo_playlist + " AND p.Cod_Play = a.Cod_Play_Agrupa AND a.Cod_Faixa_Agrupa = f.Cod_Faixa";
            ResultSet rs = stmt.executeQuery(consulta_playlist);
            LinkedList<Entidade> tabela = new LinkedList<Entidade>();
            LinkedList<String> label = new LinkedList<String>();
            label.add("playlist");
            label.add("faixa");
            tabela.add(new Entidade(label));
            while(rs.next())
            {
                LinkedList<String> colunas = new LinkedList<String>();
                colunas.add(rs.getString("Playlist"));
                colunas.add(rs.getString("Faixa"));
                tabela.add(new Entidade(colunas));
            }
            super.notificacao = new NotificacaoTabela(tabela);
            super.notificarObservadores();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void procurarAlbum(String descricao)
    {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) 
        {
            String SQL = "SELECT * FROM dbo.Album WHERE Desc_Alb LIKE '" + descricao + "%'";
            ResultSet rs = stmt.executeQuery(SQL);
            LinkedList<Entidade> lista_albuns = new LinkedList<Entidade>();
            LinkedList<String> label = new LinkedList<String>();
            label.add("codigo");
            label.add("cd");
            label.add("descricao");
            label.add("data");
            lista_albuns.add(new Entidade(label));
            while (rs.next()) 
            {
                LinkedList<String> colunas = new LinkedList<String>();
                cod_ult_album = rs.getString("Cod_Alb");
                colunas.add(cod_ult_album);
                colunas.add(rs.getString("CD_Alb"));
                colunas.add(rs.getString("Desc_Alb"));
                colunas.add(rs.getString("DataGrav_Alb"));
                lista_albuns.add(new Entidade(colunas));
            }
            super.notificacao = new NotificacaoTabela(lista_albuns);
            super.notificarObservadores();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

    public void atualizarAtributoAlbum(int atributo, String valor)
    {
        try (Connection con = DriverManager.getConnection(connectionUrl); Statement stmt = con.createStatement();) 
        {
            String coluna = "";
            if(atributo == 1)
                coluna = "CD_Alb";
            if(atributo == 2)  
                coluna = "Desc_Alb";
            if(atributo == 3)
                coluna = "DataGrav_Alb";
            String update_valor = "UPDATE dbo.Album SET " + coluna + " = '" + valor + "' WHERE Cod_Alb = " + cod_ult_album + ";";
            stmt.execute(update_valor);
            ResultSet rs = stmt.executeQuery("SELECT * FROM dbo.Album WHERE Cod_Alb = " + cod_ult_album);
            LinkedList<Entidade> lista_albuns = new LinkedList<Entidade>();
            LinkedList<String> label = new LinkedList<String>();
            label.add("codigo");
            label.add("cd");
            label.add("descricao");
            label.add("data");
            lista_albuns.add(new Entidade(label));
            while (rs.next()) 
            {
                LinkedList<String> colunas = new LinkedList<String>();
                cod_ult_album = rs.getString("Cod_Alb");
                colunas.add(cod_ult_album);
                colunas.add(rs.getString("CD_Alb"));
                colunas.add(rs.getString("Desc_Alb"));
                colunas.add(rs.getString("DataGrav_Alb"));
                lista_albuns.add(new Entidade(colunas));
            }
            super.notificacao = new NotificacaoTabela(lista_albuns);
            super.notificarObservadores();
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}