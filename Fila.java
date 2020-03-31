public class Fila {
	private Celula primeiro;
    private Celula ultimo;
    
    public Fila() {
		primeiro = new Celula();
		ultimo = primeiro;
	}
    
    public void inserir(int x) {
        ultimo.prox = new Celula(x);
		ultimo = ultimo.prox;
    }
    
    public int remover() throws Exception {
        if (primeiro == ultimo) {
			throw new Exception("Erro ao remover!");
		}
        
        Celula tmp = primeiro;
		primeiro = primeiro.prox;
		int resp = primeiro.elemento;
      tmp.prox = null;
      tmp = null;
      return resp;
    }
    
    public void mostrar() {
        System.out.print("[ ");
        
        for(Celula i = primeiro.prox; i != null; i = i.prox) {
            System.out.print(i.elemento + " ");
        }
        
        System.out.println("] ");
    }

    public int ParesMultiplos()
    {
        return ParesMultiplos(primeiro.prox);
    }

    public int ParesMultiplos(Celula i)
    {
        int resp = 0;
        if(i != null)
        {
            if(i.elemento % 2 == 0 && i.elemento % 5 == 0)
            {
                resp = 1 + ParesMultiplos(i.prox);
            }
            else{
                resp = ParesMultiplos(i.prox);
            }
        }
        return resp;
      }
      
    public static void main(String[] args) {
        Fila fila = new Fila();
        fila.inserir(10);
        fila.inserir(2);
        fila.inserir(3);
        fila.inserir(20);
        MyIO.println(fila.ParesMultiplos());
    }
}