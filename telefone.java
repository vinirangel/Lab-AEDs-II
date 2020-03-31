import java.util.Scanner;

class CelulaDupla {
	public int elemento;
	public CelulaDupla ant;
    public CelulaDupla prox;
    
	public CelulaDupla() {
		this(0);
    }
    
	public CelulaDupla(int elemento) {
		this.elemento = elemento;
		this.ant = this.prox = null;
	}
}

class telefone {
	private CelulaDupla primeiro;
    private CelulaDupla ultimo;
    
	public telefone() {
		primeiro = new CelulaDupla();
		ultimo = primeiro;
	}

    public void inserirInicio(int x) {
		CelulaDupla tmp = new CelulaDupla(x);

		tmp.ant = primeiro;
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if(primeiro == ultimo){
			ultimo = tmp;
		}else{
			tmp.prox.ant = tmp;
		}
		tmp = null;
    }

    public void inserirFim(int x) {
		ultimo.prox = new CelulaDupla(x);
		ultimo.prox.ant = ultimo;
		ultimo = ultimo.prox;
	}

	public int removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

		CelulaDupla tmp = primeiro;
		primeiro = primeiro.prox;
		int resp = primeiro.elemento;
		tmp.prox = primeiro.ant = null;
		tmp = null;
		return resp;
	}

	public int removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		} 
		int resp = ultimo.elemento;
		ultimo = ultimo.ant;
		ultimo.prox.ant = null;
		ultimo.prox = null;
		return resp;
	}

	public void inserir(int x, int pos) throws Exception {

		int tamanho = tamanho();

		if(pos < 0 || pos > tamanho){
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0){
			inserirInicio(x);
		} else if (pos == tamanho){
			inserirFim(x);
		} else {
			// Caminhar ate a posicao anterior a insercao
			CelulaDupla i = primeiro;
			for(int j = 0; j < pos; j++, i = i.prox);

			CelulaDupla tmp = new CelulaDupla(x);
			tmp.ant = i;
			tmp.prox = i.prox;
			tmp.ant.prox = tmp.prox.ant = tmp;
			tmp = i = null;
		}
	}

	public int remover(int pos) throws Exception {
		int resp;
		int tamanho = tamanho();

		if (primeiro == ultimo){
			throw new Exception("Erro ao remover (vazia)!");

		} else if(pos < 0 || pos >= tamanho){
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
		} else if (pos == 0){
			resp = removerInicio();
		} else if (pos == tamanho - 1){
			resp = removerFim();
		} else {
			// Caminhar ate a posicao anterior a insercao
			CelulaDupla i = primeiro.prox;
			for(int j = 0; j < pos; j++, i = i.prox);

			i.ant.prox = i.prox;
			i.prox.ant = i.ant;
			resp = i.elemento;
			i.prox = i.ant = null;
			i = null;
		}

		return resp;
    }

    public void mostrar() {
		System.out.print("[ "); // Comeca a mostrar.
		for (CelulaDupla i = primeiro.prox; i != null; i = i.prox) {
			System.out.print(i.elemento + " ");
		}
		System.out.println("] "); // Termina de mostrar.
    }
    
    public int tamanho() {
		int tamanho = 0; 
		for(CelulaDupla i = primeiro; i != ultimo; i = i.prox, tamanho++);
		return tamanho;
    }

    public void insercao(int tam) {
		for (CelulaDupla i = primeiro.prox.prox; i != null; i = i.prox) {
			int tmp = i.elemento;
            CelulaDupla j = i.ant;

         while ((j != primeiro) && (j.elemento > tmp)) {
            j.prox.elemento = j.elemento;
            j = j.ant;
         }
        j.prox.elemento = tmp;
      }
	}

    public static void main(String[] args) {
        telefone lista = new telefone();
        Scanner leitor = new Scanner(System.in);
        
        while(leitor.hasNext())
        {
            int i = leitor.nextInt();
            for(int j = 0; j < i; j++)
            {
                int tmp = MyIO.readInt();
                lista.inserirInicio(tmp);
            }
            lista.mostrar();
        }

    }
}
