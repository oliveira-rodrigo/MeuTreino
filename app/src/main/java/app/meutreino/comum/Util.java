package app.meutreino.comum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by olive on 21/12/2016.
 */

public class Util {

    public Date stringToDate(String aDate) throws ParseException {

        if(aDate == null) return null;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date stringDate = formatter.parse(aDate);
        return stringDate;
    }
}
