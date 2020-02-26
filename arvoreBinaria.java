import java.util.*;

class No{

	public Time elemento;
	public No esq,dir;

	public No(Time elemento, No esq, No dir) {
		this.elemento = elemento;
		this.esq = esq;
		this.dir = dir;
	}
	public No(Time elemento) {
		this(elemento, null, null);
	}
}

class Time{

	Time(){

	}
	private String arquivo;
	private String nome;
	private String apelido;
	private String estadio;
	private String tecnico;
	private String liga;
	private String nomeArquivo;
	private int capacidade;
	private int fundacaoDia = 0;
	private int fundacaoMes = 0;
	private static int fundacaoAno;
	private long paginaTam;

	public void ReadTimes()
	{
	}

	public void ReadTimes( String filename  )
	{
		this.nomeArquivo = filename;
	}

	/*Clona e retorna o time passado como parametro
	*/ 	public Time clone ( Time time )
	{
		Time time2 = new Time();
		time2.arquivo = time.arquivo;
		time2.nome = time.nome;
		time2.apelido = time.estadio;
		time2.tecnico = time.tecnico;
		time2.liga = time.liga;
		time2.nomeArquivo = time.nomeArquivo;
		time2.capacidade = time.capacidade;
		time2.fundacaoDia = time.fundacaoDia;
		time2.fundacaoMes = time.fundacaoMes;
		time2.fundacaoAno = time.fundacaoAno;
		time2.paginaTam = time.paginaTam;
		return time2;
	}


	/*Remove tudo que estiver entre tags e retorna a String limpa
	*/
	public String removeTags ( String file )
	{
		String Regex = "<[^>]*>";

		for ( int x = 0; x < file.length(); x++)
		{
			file = file.replaceAll(Regex, "");
		}
		return file;
	}

	/*Metodo que le o nome do arquivo, e o arquivo a partir da table que contem os elementos desejados
	*/
	public void lerArquivo ( String filename )
	{
		Arq.openRead ( filename, "utf-8");
		paginaTam = Arq.length();
		String linha = "infobox vcard";
		do
		{
			linha = Arq.readLine();
		}while( linha.indexOf ("infobox vcard") == -1);		

		linha = linha + Arq.readLine();
		linha = linha + Arq.readLine();
		Arq.close();
		arquivo = linha;
		nomeArquivo = filename;
	}

	public String getArquivo ( )
	{
		return this.arquivo;
	}

	/*Usa delimitadores para buscar o nome completo do time
	*/
	public void setNome ( )
	{
		String busca = this.arquivo;
		String[] delimitador =	busca.split("Full name</th><td>");
		String[] delimitador2  = delimitador[1].split("</td>");
		String resposta = delimitador2[0];
		resposta = resposta.replace("\n", "");
		resposta = resposta.replace("&#91;1&#93;", "");
		resposta = resposta.replace("&#160;", " ");
		resposta = resposta.replace("&amp;", " ");
		resposta = removeTags(resposta);
		this.nome = resposta;
	}

	public String getNome ( ){
		return this.nome;
	}

	/*Usa delimitadores para buscar o apelido do time, alem de remover o lixo que esta no meio da String
	*/
	public void setApelido (  )
	{
		String busca = this.arquivo;
		String[] delimitador = busca.split("nickname" +"\"" +">" );
		String[] delimitador2 = delimitador[1].split("</td>");
		String resposta = delimitador2[0];
		resposta = resposta.replace("&#91;1&#93;", "");
		resposta = resposta.replace("&#91;note 1&#93;", "");
		resposta = resposta.replace("&amp;", "");
		resposta = resposta.replace("\"", "");
		resposta = removeTags(resposta);
		this.apelido = resposta;
	}

	public String getApelido ( )
	{
		return this.apelido;
	}

	/*Busca o nome do estadio com o uso de delimitadores, removendo as tags que estao na String
	*/
	public void setEstadio ( )
	{
		String busca = this.arquivo;
		String[] delimitador = busca.split("Ground</th");
		String[] delimitador2 = delimitador[1].split("</td>");
		String resposta = delimitador2[0];
		resposta = removeTags(resposta);
		resposta = resposta.replaceAll(">", "");
		this.estadio = resposta;
	}

	public String getEstadio ( )
	{
		return this.estadio;
	}

	/*Busca se a String possui, Coach, Head coach ou Manager, remove tags e lixo, e retorna a String desejada
	*/
	public void setTecnico ( )
	{
		String busca = this.arquivo;
		String resposta = "";
		if ( busca.indexOf("Manager</th>") !=  -1 )
		{
			String[] delimitador = busca.split("Manager</th>");
			String[] delimitador2 = delimitador[1].split("</td>");
			resposta = delimitador2[0];
		}
		else if ( busca.indexOf("coach</th>") != -1 )
		{
			String[] delimitador = busca.split("coach</th>");
			String[] delimitador2 = delimitador[1].split("</td>");
			resposta = delimitador2[0];
		}
		else if ( busca.indexOf("Coach</th>") != -1 )
		{
			String[] delimitador = busca.split("Coach</th>");
			String[] delimitador2 = delimitador[1].split("</td>");
			resposta = delimitador2[0];
		}

		if ( resposta.indexOf("&#") != -1 )
		{
			String[] delimitador3  = resposta.split("&#");
			resposta = delimitador3[0];
		}
		resposta = removeTags(resposta);
		this.tecnico = resposta;
	}

	public String getTecnico ( )
	{
		return this.tecnico;
	}

	/*Busca a liga a qual o time pertence, removendo as tags e lixo da String no processo
	*/
	public void setLiga ( )
	{
		String busca = this.arquivo;
		if ( busca.indexOf("League</th><td")  ==  -1)
		{
			this.liga = "";
		}
		else
		{
			String[] delimitador = busca.split("League</th><td>");
			String[] delimitador2 = delimitador[1].split("</td>");
			String resposta = delimitador2[0];
			resposta = removeTags(resposta);
			this.liga = resposta;
		}
	}

	public String getLiga ( )
	{
		return this.liga;
	}

	/*Busca a capacidade do estadio do time desejado, retornando um valor inteiro
	*/
	public void setCapacidade ()
	{
		String busca = this.arquivo;
		String[] delimitador = busca.split("Capacity</th><td>");
		String[] delimitador2 = delimitador[1].split("<");
		String resposta = "";
		if ( delimitador2[0].indexOf("(") != -1 )
		{
			String[] delimitador3 = delimitador2[0].split("\\(");
			resposta = delimitador3[0].replace(",", "");
		}
		else
		{
			resposta = delimitador2[0].replace(",", "");
		}
		resposta = resposta.replace(".", "");
		resposta = resposta.replace(" ", "");
		resposta = resposta.replace("/", "");
		this.capacidade = Integer.parseInt(resposta);
	}


	public int getCapacidade()
	{
		return this.capacidade;
	}

	/*Funcao que converte o mes escrito em String para um valor numerico
	*/
	public String converterMes ( String resposta )
	{
		String mes = "";
		if ( resposta.indexOf("January") != -1)
		{
			mes = resposta.replace("January", "01");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("February") != -1) 
		{
			mes = resposta.replace("February", "02");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("March") != -1)
		{
			mes = resposta.replace("March", "03");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("April") != -1)
		{
			mes = resposta.replace("April", "04");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("May") != -1)
		{
			mes = resposta.replace("May", "05");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("June") != -1)
		{
			mes = resposta.replace("June", "06");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("July") != -1)
		{
			mes = resposta.replace("July", "07");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("August") != -1)
		{
			mes = resposta.replace("August", "08");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("September") != -1)
		{
			mes = resposta.replace("September", "09");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("October") != -1)
		{
			mes = resposta.replace("October", "10");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("November") != -1)
		{
			mes  = resposta.replace("November", "11");
			mes = mes.replace(" ", "/");
		}
		if ( resposta.indexOf("December") != -1)
		{
			mes = resposta.replace("December", "12");
			mes = mes.replace(" ", "/");
		}
		return mes;
	}

	/*Funcao que detecta se a String enviada corresponde a um mes
	*/
	public boolean isMes ( String resposta )
	{
		boolean mes = false;
		if ( resposta.indexOf("January") != -1 || resposta.indexOf("February") != -1 || resposta.indexOf("March") != -1 ||
				resposta.indexOf("April") != -1 || resposta.indexOf("May") != -1 || resposta.indexOf("June") != -1 ||
				resposta.indexOf("July") != -1 || resposta.indexOf("August") != -1 || resposta.indexOf("September") != -1 ||
				resposta.indexOf("October") != -1 || resposta.indexOf("November") != -1 || resposta.indexOf("December") != -1)
		{
			mes = true; 
		}
		return mes;
	}

	/*Metodo que busca o dia, mes e ano de fundacao do time desejado,removendo o lixo da String e imprimindo os valores encontrados
	*/
	public void setData ( )
	{
		String busca = this.arquivo;
		String dia = "";
		String mes = "";
		String[] delimitador = busca.split("Founded</th><td>");
		String[] delimitador2 = delimitador[1].split("<");
		String resposta = delimitador2[0];
		resposta = resposta.replace("&#160;", " ");			 
		resposta = resposta.replace(",","");
		resposta = resposta.replace("th", "");

		if ( isMes(resposta) )
		{
			resposta = converterMes(resposta);
		}

		if ( resposta.indexOf("/") != -1 )
		{
			String[] delimitador3 = resposta.split("/");
			if ( delimitador3.length > 2 )
			{
				this.fundacaoAno = Integer.parseInt(delimitador3[2]);
				int comparar = Integer.parseInt(delimitador3[1]);
				this.fundacaoDia = Integer.parseInt(delimitador3[0]);
				this.fundacaoMes = Integer.parseInt(delimitador3[1]);

				if ( comparar > 12)
				{
					this.fundacaoDia = Integer.parseInt(delimitador3[1]);
					this.fundacaoMes = Integer.parseInt(delimitador3[0]);
				}
				if ( this.fundacaoDia < 10 )
				{
					dia = String.format("%02d", this.fundacaoDia);
					MyIO.print(dia + "/");
				}
				else
				{
					MyIO.print (this.fundacaoDia + "/");
				}
				if ( this.fundacaoMes < 10 )
				{
					mes = String.format("%02d", this.fundacaoMes);
					MyIO.print(mes + "/");
				}
				else
				{
					MyIO.print(this.fundacaoMes + "/");
				}
				MyIO.print(this.fundacaoAno + " ## ");
			}

			if ( delimitador3.length == 2)
			{
				delimitador3 = resposta.split("/");
				this.fundacaoDia = 0;				
				dia = String.format("%02d", this.fundacaoDia);
				this.fundacaoMes = Integer.parseInt(delimitador3[0]);
				mes = String.format("%02d", this.fundacaoMes);
				this.fundacaoAno = Integer.parseInt(delimitador3[1]);
				MyIO.print (dia + "/" + mes + "/" + this.fundacaoAno + " ## ");
			}                        
		}
		else
		{
			this.fundacaoDia = 0;
			this.fundacaoMes = 0;
			dia = String.format("%02d", this.fundacaoDia);
			mes = String.format("%02d", this.fundacaoMes);
			this.fundacaoAno = Integer.parseInt(resposta);
			MyIO.print (dia + "/" + mes + "/" + this.fundacaoAno + " ## ");
		}
	}

	public long getPaginaTam ( )
	{
		return this.paginaTam;
	}

	public String getNomeArquivo ( )
	{
		return this.nomeArquivo;
	}

	public void setAll()
	{
		setNome();
		setApelido();
		setTecnico();
		setEstadio();
		setCapacidade();
		setLiga();
	}


	public void  imprimir ( )
	{
		MyIO.print ( getNome() + " ## " );
		MyIO.print ( getApelido() + " ## " );
		setData();
		MyIO.print ( getEstadio() + " ## "  );
		MyIO.print ( getCapacidade() + " ## " );
		MyIO.print ( getTecnico() + " ## "  );
		MyIO.print ( getLiga() + " ## " );
		MyIO.print ( getNomeArquivo() + " ## ");
		MyIO.println ( getPaginaTam() + " ## ");
	}
}	

public class arvoreBinaria {
	private No raiz;
	private int comp = 0;

	public arvoreBinaria() {
		raiz = null;
	}

	public static boolean isFim(String s){
		return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}
	
	public boolean pesquisar(String x) {
		MyIO.print(x+" raiz");
		return pesquisar(x, raiz);
	}

	private boolean pesquisar(String x, No i) {
		boolean resp = true;
		if (i == null) {
			comp++;
			System.out.printf(" %c%c%c\n",78 ,195, 79);
			resp = false;

		} else if (x.equals(i.elemento.getNome())) {
			comp++;
			MyIO.println(" SIM");

		} else if (  i.elemento.getNome().compareTo(x) < 0 ){
			comp++;
			MyIO.print(" dir");
			resp = pesquisar(x, i.esq);

		} else if ( i.elemento.getNome().compareTo(x) > 0 ) {
			comp++;
			MyIO.print(" esq");
			resp = pesquisar(x, i.dir);
		}
		return resp;
	}

	public void mostrarCentral() {
		System.out.print("[ ");
		mostrarCentral(raiz);
		System.out.println("]");
	}

	private void mostrarCentral(No i) {
		if (i != null) {
			mostrarCentral(i.esq);
			System.out.print(i.elemento + " ");
			mostrarCentral(i.dir);
		}
	}

	public void mostrarPre() {
		System.out.print("[ ");
		mostrarPre(raiz);
		System.out.println("]");
	}

	private void mostrarPre(No i) {
		if (i != null) {
			MyIO.println(i.elemento.getNome() + " ");
			mostrarPre(i.esq);
			mostrarPre(i.dir);
		}
	}

	private void inserir ( Time x ) {
		this.raiz = inserir( x, raiz );
	}

	private No inserir(Time x, No i) {
		
		if (i == null) {
			comp++;
			i = new No(x);

		} else if ( i.elemento.getNome().compareTo(x.getNome()) < 0 ){
			//System.out.println(" INSERIR ESQ ");
			comp++;
			i.esq = inserir(x, i.esq);

		} else if ( i.elemento.getNome().compareTo(x.getNome()) > 0 ){
			//System.out.println(" INSERIR DIR ");
			comp++;
			i.dir = inserir(x, i.dir);
		}
		else{
			MyIO.println("Erro na insercao");
		}

		return i;
	}

	public void remover(Time x) {
		raiz = remover(x, raiz);
	}

	private No remover(Time x, No i) {

		if ( x.getNome().compareTo(i.elemento.getNome()) == -1 ){
			i.esq = remover(x, i.esq);

		} else if ( x.getNome().compareTo(i.elemento.getNome()) == 1 ){
			i.dir = remover(x, i.dir);

			// Sem no a direita.
		} else if (i.dir == null) {
			i = i.esq;

			// Sem no a esquerda.
		} else if (i.esq == null) {
			i = i.dir;

			// No a esquerda e no a direita.
		}/* else {
		i.esq = antecessor(i, i.esq);
		}*/
		return i;
	}

	public Time getRaiz() {
		return raiz.elemento;
	}

	public static void main ( String[] Args)
	{
		String[] linha = new String[100];
		String[] linha2 = new String[100];
		arvoreBinaria arvore = new arvoreBinaria ( );
		Time time = null;
		String nome = "";
		int numEntrada = 0;
		int numEntrada2 = 0;
		long startTime = System.currentTimeMillis();

		do{
			linha[numEntrada] = MyIO.readLine();
		} while (isFim(linha[numEntrada++]) == false);
		numEntrada--;
					
		for ( int x = 0; x < numEntrada; x++ )
		{
			time = new Time();
			nome = linha[x];
			time.lerArquivo(nome);
			time.setNome();
			arvore.inserir(time);
		}
		
		do{
			linha2[numEntrada2] = MyIO.readLine();
		} while (isFim(linha2[numEntrada2++]) == false);
		numEntrada2--;

		for ( int x = 0; x < numEntrada2; x++ )
		{
			nome = linha2[x];
			arvore.pesquisar(nome);	
		}
		long endTime = System.currentTimeMillis();

		long execTime = endTime - startTime;

		Arq.openWriteClose("668007_arvoreBinaria.txt", "UTF-8", "Matricula: 668007\t"+"Tempo de execucao:\t"+execTime+"\t"+"Comparacoes:\t"+arvore.comp);
	}
}
