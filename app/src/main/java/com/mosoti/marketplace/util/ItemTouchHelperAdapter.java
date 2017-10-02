package com.mosoti.marketplace.util;

/**
 * Created by mosoti on 10/2/17.
 */

public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
