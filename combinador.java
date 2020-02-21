public class combinador
{
	public static boolean isFim(String s){
		return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
	}

	public static void combinar(String linha)
	{
		String[] temp = linha.split(" ");
		String str1 = temp[0];
		String str2 = temp[1];
		String resp = "";

		int x = 0;
		for(x < str1.length() && x < str2.length(); x++)
		{
			resp += str1.charAt(x);
			resp += str2.charAt(x);
		}

		while ( x < str1.length() )
		{
			resp += str1.charAt(x);
			x++;
		}

		while ( x < str2.length() )
		{
			resp += str2.charAt(x);
			x++;
		}

		MyIO.println(resp);
	}
	public static void main(String[] args) {
		String[] linha = new String[100];
		int numEntrada = 0;

		do {
			linha[numEntrada] = MyIO.readLine();
			if (isFim(linha[numEntrada]) == false)
			{
				combinar(linha[numEntrada]);
			}
		} while (isFim(linha[numEntrada++]) == false);
		numEntrada--;
	}
}
