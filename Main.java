import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Main {
 
    public static void main(String[] args) throws Exception 
    {
        RandomAccessFile f = new RandomAccessFile("cep_ordenado.dat", "r");

        Endereco e = new Endereco();
        
        long meio = f.length() /2;
        
        System.out.println(meio);
        
		Scanner scan = new Scanner(System.in);
       
        
        System.out.println("Digite um cep:");
        String cepDesejado = scan.next();
        
        long aux = pesquisa(cepDesejado, f, 0 , f.length()/300, e);
        
        f.seek(aux);
        
        e.leEndereco(f);
       
            System.out.println(e.getLogradouro().trim());
            System.out.println(e.getBairro().trim());
            System.out.println(e.getCidade().trim());
            System.out.println(e.getEstado().trim());
            System.out.println(e.getSigla().trim());
            System.out.println(e.getCep().trim()); 
        
        
       f.close();
       scan.close();
    }
    
    public static long pesquisa ( String cepDesejado, RandomAccessFile f,long menor, long maior, Endereco e ) throws IOException{
    		System.out.println(menor + "/"+ maior);
    		long meio = (maior + menor) / 2;
    		System.out.println(meio);
    		f.seek((meio-1)*300);
    		e.leEndereco(f);
    		System.out.println(e.getLogradouro());
    		
    		if (menor > maior)
    		 return -1;
    		if(e.getCep().equals(cepDesejado)) {
    			System.out.println(f.getFilePointer() + "filepointer");
    			
    			return meio;
    			
    		}else {
    			int result = cepDesejado.compareTo(e.getCep());
    			if(result < 0) {
    				
    				//parte que compara se for "do lado menor
    				return pesquisa(cepDesejado, f, menor, meio-1, e);
    				
    			}else if (result > 0){
    				
    				//parte que compara se for "do lado maior
    				return pesquisa(cepDesejado, f, meio+1, maior, e);
    			}
    		}
			return meio;
    
    }
    
 
}