package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LeitorArquivo
{
    private BufferedReader reader;
    private FileInputStream fileIS;
    private String file_path = "src/resources/arquivo.txt";

    public LeitorArquivo() throws FileNotFoundException
    {
        fileIS = new FileInputStream(new File(file_path).getAbsolutePath()); 
        reader = new BufferedReader(new InputStreamReader(fileIS));
    }

    public String lerPagina(int key)
    {
        String valor = "";
        int cont = 1;
        try
        {
            fileIS.getChannel().position(0);
            reader = new BufferedReader(new InputStreamReader(fileIS));
            while((valor = reader.readLine()) != null && cont != key)
                cont++;
        }
        catch(IOException e)
        {
            System.out.println("Nao foi possivel ler a pagina no arquivo");
            System.out.println(e.getMessage());
        }
        if(cont == key)
            return valor;
        return new String();
    }
}