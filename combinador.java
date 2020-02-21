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
        String[] resp = new String[100];
        int x = 0;

        while(x < linha.length()-1)
        {
            //if (str1.charAt(x) != null && str2.charAt(x) != null)
            if ( str1.compareTo(str2) <  0)
            {
                for ( int y = 0; y < str1.length(); y++)
                {
                    resp[y] = str1.charAt(y);
                    str2.charAt(y);
                }
            }
            x++;
        }
    }
    public static void main(String[] args) {
        String[] linha = new String[100];
        int numEntrada = 0;

        do {
            linha[numEntrada] = MyIO.readLine();
        } while (isFim(linha[numEntrada++]) == false);
        numEntrada--;
    }
}