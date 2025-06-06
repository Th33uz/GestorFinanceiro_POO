package Financas.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatadorData{

    private static final String FORMATO = "dd/MM/yyyy";

    public static String obterDataAtualFormatada(){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO);
        return sdf.format(new Date());
    }

    public static boolean validarData(String dataStr){
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATO);
        sdf.setLenient(false);

        try{
            Date data = sdf.parse(dataStr);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }
}
