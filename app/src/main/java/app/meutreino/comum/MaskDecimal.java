package app.meutreino.comum;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.DecimalFormat;

/**
 * Created by olive on 21/12/2016.
 */

public class MaskDecimal implements TextWatcher {

    final EditText campo;

    public MaskDecimal(EditText campo) {
        super();
        this.campo = campo;
    }

    private boolean isUpdating = false;
    private DecimalFormat df = new DecimalFormat("0.00");

    @Override
    public void onTextChanged(CharSequence s, int start, int before,
                              int after) {
        // Evita que o método seja executado varias vezes.
        // Se tirar ele entre em loop
        if (isUpdating) {
            isUpdating = false;
            return;
        }

        isUpdating = true;
        String str = s.toString();

        try {
            // Transformamos o número que está escrito no EditText em decimal.
            str = df.format(Double.valueOf(str));
            campo.setText(str);
            campo.setSelection(campo.getText().length());
        } catch (NumberFormatException e) {
            s = "";
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Não utilizado
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Não utilizado
    }
}
