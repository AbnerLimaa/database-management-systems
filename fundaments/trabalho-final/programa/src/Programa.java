import controller.Gerenciador;

import java.lang.Class;
import java.lang.ClassLoader;
import java.lang.SecurityException;
import java.lang.NoSuchFieldException;
import java.lang.IllegalArgumentException;
import java.lang.IllegalAccessException;
import java.lang.reflect.Field;
import java.io.File;

public class Programa
{
    public static void main(String[] args)
    {
        try 
        {
            Class clazz = ClassLoader.class;
            Field field = clazz.getDeclaredField("sys_paths");
            boolean accessible = field.isAccessible();
            if (!accessible)
                field.setAccessible(true);
            Object original = field.get(clazz);
            field.set(clazz, null);
            try 
            {
                System.setProperty("java.library.path", System.getProperty("java.library.path") + ";" + new File("").getAbsolutePath() + File.separator + "lib");
                System.loadLibrary("sqljdbc_auth");
            }
            finally 
            {
               field.set(clazz, original);
               field.setAccessible(accessible);
            }
        } 
        catch (SecurityException e) 
        {
            e.printStackTrace();
        } 
        catch (NoSuchFieldException e) 
        {
            e.printStackTrace();
        } 
        catch (IllegalArgumentException e) 
        {
            e.printStackTrace();
        } 
        catch (IllegalAccessException e) 
        {
            e.printStackTrace();
        }
        Gerenciador gerenciador = new Gerenciador();
        gerenciador.iniciar();
    }
}