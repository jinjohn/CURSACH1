package com.example.CALCANDROID; //наименование пакета;
import android.app.Activity; //к программе подключены библиотеки Activity - управление экранной формой;
import android.os.Bundle; //к программе подключены библиотеки Bundle - виртуализация;
import android.view.View;//к программе подключены библиотеки работы с View - представления;
import android.widget.EditText;//к программе подключены библиотеки работы с элементами EditText - поля ввода;
import android.widget.TextView;//к программе подключены библиотеки работы с элементами TextView - поля вывода;
import android.widget.Toast; //к программе подключены библиотеки всплывающих сообщений;
public class MyActivity extends Activity    // объявлен клас MyActivity;
{                                           //начало блока класса MyActivity;
    EditText Text1, Text2, Text3;           //объявлены переменные типа EditText;
    TextView Text, TextMedium;              //объявлены переменные типа TextView;
    int s , n;                              //объявлены переменные целого типа. Сумма залога, срок в месяцах;
    float p;                                //объявлена переменная с плавающей запятой. Проценты;
    boolean validate;                       //объявлена переменная типа "правда/ложь". Проверка данных;
    String str = "";                        //объявлена переменная типа строка. Формирование отчета;
    @Override                               //этот метод является переопределением реализации метода из класса,
                                            // который находится выше по иерархии;
    public void onCreate(Bundle savedInstanceState) //метод при первом запуске программы;
    {                                               //начало блока метода onCreate;
        super.onCreate(savedInstanceState);         //теперь метод вызывается при создании деятельности;
        setContentView(R.layout.main);              //используется разметка mian.xml;
    }                                               //конец блока метода onCreate;
    public void onMyButtonClick(View view)  //метод, вызывающийся при нажатии на кнопку - описан в разметке main.xml;
    {                                       //начало блока метода onMyButtonClick;
        validate = true;                    //переменная validate имеет значение true;
        Text1 = (EditText) findViewById(R.id.EditText01); //в переменную Text1 занесено
                                                                // значение из поля EditText01 типа Editable;
        Text2 = (EditText) findViewById(R.id.EditText02); //в переменную Text2 занесено
                                                                // значение из поля EditText02 типа Editable;
        Text3 = (EditText) findViewById(R.id.EditText03); //в переменную Text3 занесено
                                                                // значение из поля EditText03 типа Editable;
        if (Text1.getText().toString().equals(""))        //возвращает true, если Text1 пуст, иначе возвращает false;
        {                                                 //начало блока для условия = true;
            Toast.makeText(this, "сумма кредита?", Toast.LENGTH_SHORT).show(); //вывод сообщения "сумма кредита?";
            validate = false;                             //значение валидации равно false. Программа не будет считать;
        }                                                 //конец блока для условия = true;
        else                                              //описание иного случая (false);
        {                                                 //начало блока для условия = false;
           s = Integer.parseInt(Text1.getText().toString());//в переменную s занесено значение из Text1
                                                                                   // (из Editable в int);
        }                                                 //конец блока для условия = false;
        if (Text2.getText().toString().equals(""))        //возвращает true, если Text2 пуст, иначе возвращает false;
        {                                                 //начало блока для условия = true;
            Toast.makeText(this, "кол-во месяцев?", Toast.LENGTH_SHORT).show();//вывод сообщения "кол-во месяцев?";
            validate = false;                             //значение валидации равно false. Программа не будет считать;
        }                                                 //конец блока для условия = true;
        else                                              //описание иного случая (false);
        {                                                 //начало блока для условия = false;
            n = Integer.parseInt(Text2.getText().toString()); //в переменную n занесено значение из Text2
                                                                                   // (из Editable в int);
        }                                                 //конец блока для условия = false;

        if (Text3.getText().toString().equals(""))        //возвращает true, если Text3 пуст, иначе возвращает false;
        {Toast.makeText(this, "процент?", Toast.LENGTH_SHORT).show();//вывод сообщения "процент?";
        validate = false;                                 //значение валидации равно false. Программа не будет считать;
        }                                                 //конец блока для условия = true;
        else                                              //описание иного случая (false);
        {                                                 //начало блока для условия = false;
            p = Float.parseFloat(Text3.getText().toString());//в переменную n занесено значение из Text2
                                                                                   // (из Editable в int);
        }                                                 //конец блока для условия = false;


        if (validate)                                     //возвращает true, если validate = true, иначе возвращает false;
        {                                                 //начало блока для условия валидации true;
            str="";                                       //str очищена;
            Text = (TextView) findViewById(R.id.text);    //разметка id=text привязана к переменной Text;
            TextMedium = (TextView) findViewById(R.id.textView);//разметка id=TextView привязана к переменной TextMedium;
            TextMedium.setText ("платёж="+GetMinPlat(s,n,p)+"; сумма="+GetEsumm(s, n, p));// выводится на экран
                                                          //средним шрифтом ежемесячный платёж и итоговую сумму за период;
            for (int i = 0; i<n; i++)                     //объявлен цикл (n раз), объявлена переменная i (целый);
            {                                             //начало блока цикла;
                str = str + "мес: "+(i+1)+"; проц: "+GetProc(s,n,p,i)+"; + долг: "  //заполняем переменную
                        + round((GetMinPlat(s,n,p)-GetProc(s,n,p,i)),2 )+"ост:"+GetOst(s,n,p,i)+";\n";//контентом;
            }                                             //конец блока цикла;
            Text.setText(str);                            //заполнляем поле разметки text контентом переменной str;
        }                                                 //конец блока условия валидации true;
    }                                               //конец блока метода onMyButtonClick;
    public double GetMinPlat (int S, int N, Float P)//сумма ежемесячного платежа:
                                                    //S-сумма кредита, N-кол-во месяцев, P-процентная ставка;
    {                                               //начало блока функции;
        return round(S*  (P/1200+(P/1200/(Math.pow(1+P/1200,N)-1))),2); // возвращает значение ежемесячного платежа;
    }                                               //конец блока функции;
    public double GetProc (int S, int N, Float P, int i) //стоимость процента на i месяце:
                                                        //S-сумма займа, N-кол-во месяцев, P-процентная ставка,
                                                        //i - номер месяца;
    {                                               //начало блока функции;
        return round((GetEsumm(S, N, P) - i*GetMinPlat(S,N,P))*(P/1200),2);//возвращает значение процента;
    }                                               //конец блока функции;
    public double GetOst (int S, int N, Float P, int i)//остаток на месяце i:
                                                       //S-сумма займа, N кол-во месяцев, P-процентная ставка,
                                                       // i-номер месяца;
    {                                               //начало блока функции;
        return round((GetEsumm(S, N, P) - (i+1)*GetMinPlat(S,N,P)),2); // возвращает значение остатка с процентами;
    }                                               //конец блока функции;

    public double GetEsumm (int S, int N, Float P) // итоговая сумма выплат:
                                                    //S-сумма займа, N кол-во месяцев, P-процентная ставка;
    {                                               //начало блока функции;
        return round(GetMinPlat(S,N,P)*N,2);        //возвращает итоговую сумму выплат;
    }                                               //конец блока функции;

    public static double round(double value, int scale) //округление:
                                                       // value-значение, scale-кол-во знаков после запятой;
    {                                               //начало блока функции;
        return Math.round(value * Math.pow(10, scale)) / Math.pow(10, scale);//возращает округлённое значение;
    }                                               //конец блока функции;
}                                                   //конец блока класса MyActivity;
