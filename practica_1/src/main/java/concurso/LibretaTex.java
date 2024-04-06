package concurso;

import java.io.IOException;

public interface LibretaTex {
    public void inscribir(String p, String c)  ;

      
    public boolean estaInscripto( String p) throws IOException ;
        
}