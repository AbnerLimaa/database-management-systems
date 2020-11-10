package model;

import java.lang.StringBuilder;
import java.util.Iterator;
import java.util.LinkedList;

public class Entidade
{
    private LinkedList<String> tupla;

    public Entidade()
    {

    }
    
    public Entidade(LinkedList<String> colunas)
    {
        this.tupla = colunas; 
    }

    public LinkedList<String> getColunas()
    {
        return this.tupla;
    }

    public String toString()
    {
        StringBuilder texto = new StringBuilder("(");
        Iterator iterator = this.tupla.iterator();
        while(iterator.hasNext())
            texto.append(iterator.next() + " | ");
        texto.append(")");
        return texto.toString();
    }
}