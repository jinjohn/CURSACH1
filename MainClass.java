import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.Reader;



public class MainClass
{
    public static void main(String[] args) throws Exception
    {
        InputStream inputStream=System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader );

        System.out.println("Введите желаемую сумму в рублях и нажмите Enter"); //'S'
        String sa1 = bufferedReader.readLine();
        System.out.println("Введите срок кредита в месяцах и нажмите Enter:"); //'N'
        String sa2 = bufferedReader.readLine();
        System.out.println("Введите процентную ставку (годовых)и нажмите Enter:"); //'P'
        String sa3 = bufferedReader.readLine();

        int s1 = Integer.parseInt(sa1);
        int n1 = Integer.parseInt(sa2);
        float p1 = Integer.parseInt(sa3);

        System.out.println(mesplat(s1,n1,p1));

    }
    public static double mesplat(int S,  int N,float P)
    {
        P=P/12/10;
        return S*(P+(P/(Math.pow(1+P,N)-1)));
    }
    выдаржлвыражлдтжыдва
}



