package com.hoqii.sales.selfservice.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageButton;

import com.hoqii.sales.selfservice.activity.OrderListActivity;
import com.hoqii.sales.selfservice.content.database.adapter.OrderMenuDatabaseAdapter;
import com.hoqii.sales.selfservice.entity.OrderMenu;
import com.hoqii.sales.selfservice.holder.OrderHolder;

import org.meruvian.midas.core.defaults.DefaultAdapter;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by meruvian on 14/07/15.
 */
public class OrderListActAdapter extends DefaultAdapter<OrderMenu, OrderHolder> {
    private DecimalFormat decimalFormat = new DecimalFormat("#,###");
    private double totalPrice;
    private OrderMenuDatabaseAdapter orderMenuDbAdapter;
    private Activity activity;

    public OrderListActAdapter(Context context, int layout, List contents, Activity activity) {
        super(context, layout, contents);
        this.activity = activity;

        orderMenuDbAdapter = new OrderMenuDatabaseAdapter(context);
    }

    @Override
    public OrderHolder ViewHolder(View view) {
        return new OrderHolder(view);
    }

    @Override
    public View createdView(View view, OrderHolder holder, OrderMenu orderMenu) {
        totalPrice = orderMenu.getProduct().getSellPrice() * (orderMenu.getQty());
        String q = String.valueOf(orderMenu.getQty());

        holder.menuName.setText(orderMenu.getProduct().getProduct().getName());
        holder.menuQuantity.setText(q);
        holder.totalPrice.setText("Rp " + decimalFormat.format(totalPrice));

        holder.buttonDelete.setOnClickListener(new OnClickData(getContext(), getPosition(), orderMenu.getId()));

        return view;
    }

    private class OnClickData extends ImageButton implements ImageButton.OnClickListener {
        private int location;
        private String orderMenuId;

        public OnClickData(Context context, int location, String orderMenuId) {
            super(context);
            this.location = location;
            this.orderMenuId = orderMenuId;
        }

        @Override
        public void onClick(View view) {
            confirmDialog(location, orderMenuId);
        }
    }

    private void confirmDialog(final int location, final String orderMenuId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Konfirmasi");
        builder.setMessage("Hapus order ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                OrderListActivity orderListActivity = (OrderListActivity) activity;

                getList().remove(location);
                notifyDataSetChanged();
                orderMenuDbAdapter.deleteOrderMenu(orderMenuId);
                orderListActivity.setTotalOrderTab();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
