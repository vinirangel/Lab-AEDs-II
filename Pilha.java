class Celula{
    public int elemento; // Elemento inserido na celula.
	public Celula prox; // Aponta a celula prox.
	
	public Celula() { this(0); }

	public Celula(int elemento) {
      this.elemento = elemento;
      this.prox = null;
    }
}
public class Pilha {
    private Celula topo;
    
	public Pilha() {
		topo = null;
    }
    
	public void inserir(int x) {
		Celula tmp = new Celula(x);
		tmp.prox = topo;
		topo = tmp;
      tmp = null;
	}

    public int remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}

		int resp = topo.elemento;
      Celula tmp = topo;
		topo = topo.prox;
      tmp.prox = null;
      tmp = null;
		return resp;
    }
    
	public void mostrar() {
		System.out.print("[ ");
		for(Celula i = topo; i != null; i = i.prox){
			System.out.print(i.elemento + " ");
      }
		System.out.println("] ");
	}
	public int soma() {
      int soma = 0;
		for(Celula i = topo; i != null; i = i.prox){
			soma = soma + i.elemento;
      }
      return soma;
    }
    public int somaRec()
    {
        return somaRec(topo);
    }

    public int somaRec(Celula i)
    {
        int soma = 0;
        if(i.prox != null)
        {
            soma = i.elemento + somaRec(i.prox);
        }
        return soma;
    }

    public int maiorElemento()
    {
        int maior = 0;
        for (Celula i = topo; i != null; i = i.prox)
        {
            if(i.elemento > maior)
                maior = i.elemento;
        }
        return maior;
    }

    public int maiorRec()
    {
        return maiorRec(topo);
    }

    public int maiorRec(Celula i)
    {
        int resp = 0;
        if(i != null)
        {
            int maiorRec = maiorRec(i.prox);
            resp = (i.elemento > maiorRec) ? i.elemento : maiorRec;
        }
        return resp; 
        }

        public void mostrarRem()
        {
            mostrarRem(topo);
        }

        public void mostrarRem(Celula i)
        {
            if(i !=  null)
            {
                MyIO.println(i.elemento);
                mostrarRem(i.prox);
            }
        }

        public void mostrarInRec()
        {
            mostrarInRec(topo);
        }

        public void mostrarInRec(Celula i)
        {
            if(i != null)
            {
                mostrarInRec(i.prox);
                MyIO.println(i.elemento);
            }
        }

        public void mostrarIn()
        {
            int quant = 0;
            for(Celula i = topo; i != null; i = i.prox)
            {
                quant++;
            }
            int[] resp = new int[quant];
            int x = 0;
            
            for(Celula i = topo; i != null; i = i.prox, x++)
            {
                resp[x] = i.elemento;
            }
            
            for(int y = quant-1; y >= 0; y--)
            {
                MyIO.println(resp[y]);
            }
        }
        
        public static void main(String[] args) {
        Pilha pilha = new Pilha();
        pilha.inserir(1);
        pilha.inserir(3);
        pilha.inserir(9);
        pilha.inserir(5);
        pilha.inserir(7);

        //MyIO.println(pilha.somaRec());
        //MyIO.println(pilha.maiorElemento());
        //MyIO.println(pilha.maiorRec());
        //pilha.mostrarRem();
        //pilha.mostrarInRec();
        pilha.mostrarIn();
        }
}