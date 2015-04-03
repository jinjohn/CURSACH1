package com.example.CALCANDROID;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.BreakIterator;

public class MyActivity extends Activity
{
   \\объявление переменных
    EditText Text1, Text2, Text3;
    TextView Text, TextMedium;
    int s , n;
    float p;
    boolean validate;
    String str = "";
    @Override       \\поверх

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void onMyButtonClick(View view)    \\ по нажатию на кнопку "рассчитать"
    {
        validate = true; \\точка валидации

        Text1 = (EditText) findViewById(R.id.EditText01);\\привязка поля редактрования к переменной
        Text2 = (EditText) findViewById(R.id.EditText02);\\привязка поля редактрования к переменной
        Text3 = (EditText) findViewById(R.id.EditText03);\\привязка поля редактрования к переменной

        if (Text1.getText().toString().equals(""))
        {Toast.makeText(this, "сумма кредита?", Toast.LENGTH_SHORT).show();
        validate = false;
        }
        else
        {s = Integer.parseInt(Text1.getText().toString());}

        if (Text2.getText().toString().equals(""))
        {Toast.makeText(this, "кол-во месяцев?", Toast.LENGTH_SHORT).show();
        validate = false;
        }
        else
        { n = Integer.parseInt(Text2.getText().toString());}

        if (Text3.getText().toString().equals(""))
        {Toast.makeText(this, "процент?", Toast.LENGTH_SHORT).show();
        validate = false;
        }
        else
        {p = Float.parseFloat(Text3.getText().toString());}


        if (validate)
        {Text = (TextView) findViewById(R.id.text);
        TextMedium = (TextView) findViewById(R.id.textView);
            TextMedium.setText ("платёж="+GetMinPlat(s,n,p)+"; сумма="+GetEsumm(s, n, p));
            for (int i = 0; i<n; i++)
            {
        str = str + "мес: "+(i+1)+"; проц: "+GetProc(s,n,p,i)+"; + долг: " + round((GetMinPlat(s,n,p)-GetProc(s,n,p,i)),2 )+"ост:"+GetOst(s,n,p,i)+";\n";
            }
            Text.setText(str);
        }
    }

    public double GetMinPlat (int S, int N, Float P) //сумма ежемесячного платежа - сумма кредита, кол-во месяцев, процентная ставка
    {
        return round(S*  (P/1200+(P/1200/(Math.pow(1+P/1200,N)-1))),2);
    }


    public double GetProc (int S, int N, Float P, int i) // стоимость процента на определенном месяце - сумма кнредита, кол-во месяцев, процентная ставка, номер месяца
    {
        return round((GetEsumm(s, n, p) - i*GetMinPlat(S,N,P))*(P/1200),2);
    }

    public double GetOst (int S, int N, Float P, int i) // остаток на определенном месяце - сумма кнредита, кол-во месяцев, процентная ставка, номер месяца
    {
        return round((GetEsumm(S, N, P) - (i+1)*GetMinPlat(S,N,P)),2);
    }

    public double GetEsumm (int S, int N, Float P) // итоговая сумма выплат - сумма кредита, кол-во месяцев, процентная ставка
    {
        return round(GetMinPlat(S,N,P)*N,2);
    }


    public static double round(double value, int scale) {
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);
    }
}


