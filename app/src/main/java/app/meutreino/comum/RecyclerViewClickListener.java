package app.meutreino.comum;

import android.view.View;

/**
 * Created by olive on 23/11/2016.
 */

public interface RecyclerViewClickListener {

    void onRowClicked(int position);
    void onViewClicked(View v, int position);
}
