public class espelhado
{

    public static boolean isFim(String s){
        return (s.length() >= 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
     }

    public static void espelhar(String linha) {
        String[] temp = linha.split(" ");
        int num1 = Integer.parseInt(temp[0]);
        int num2 = Integer.parseInt(temp[1]);

        String output = "";
        String temp = "";

        for (int x = num1; x <= num2; x++)
        {
            output += x;
        }

        for (int i = output.length; i >= 0; i--)
        {
            temp += output.charAt(i);
        }

        MyIO.println(output + temp);
    }     
     public static void main (String[] Args)
    {
        String[] linha = new String[100];
        int numEntrada = 0;

        do {
            linha[numEntrada] = MyIO.readLine();
            //MyIO.println("A linha e :"+linha[numEntrada]);
            espelhar(linha[numEntrada]);
        } while (isFim(linha[numEntrada++]) == false);
        numEntrada--;
    }
}