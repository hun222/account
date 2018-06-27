package com.hoonyeee.android.account.adapater;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hoonyeee.android.account.DBConnect;
import com.hoonyeee.android.account.R;
import com.hoonyeee.android.account.domain.Account;

import java.util.List;

public class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.Holder>{
    List<Account> datas;
    Context context;
    DBConnect con;

    public BaseAdapter(Context context, DBConnect con){
        this.context = context;
        this.con = con;
    }
    public void setData(List<Account> datas){
        this.datas = datas;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Account account = datas.get(position);
        holder.setAccount(account.accNo);
        holder.setBank(account.bankName);
        holder.setId(account.id);
        holder.setColor(account.bankIdx);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView textBank, textAccount;
        CardView cardView;
        private int id;
        public Holder(View itemView) {
            super(itemView);
            textBank = itemView.findViewById(R.id.textBank);
            textAccount = itemView.findViewById(R.id.textAccount);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("계좌 삭제");
                    alertDialogBuilder
                            .setMessage(textBank.getText().toString() + "[" + textAccount.getText().toString() + "]" + "삭제하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("삭제",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            try {
                                                con.delete(id);
                                                for(int i=0; i<datas.size(); i++){
                                                    if(id == datas.get(i).id){
                                                        datas.remove(i);
                                                        break;
                                                    }
                                                }
                                                notifyDataSetChanged();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                            )
                            .setNegativeButton("취소",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    }
                            );
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                    Toast.makeText(context, id+"", Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }
        public void setId(int id){
            this.id = id;
        }
        public void setBank(String bankName){
            textBank.setText(bankName);
        }
        public void setAccount(String account){
            textAccount.setText(account);
        }

        // 1.신한 2.카카오뱅크 3.우리 4.농협
        public void setColor(int bankIdx){
            switch (bankIdx){
                case 1:
                    cardView.setCardBackgroundColor(Color.rgb(129,218,245));
                    textBank.setTextColor(Color.WHITE);
                    textAccount.setTextColor(Color.WHITE);
                    break;
                case 2:
                    cardView.setCardBackgroundColor(Color.rgb(255,235,51));
                    textBank.setTextColor(Color.rgb(66,54,48));
                    textAccount.setTextColor(Color.rgb(66,54,48));
                    break;
                case 3:
                    cardView.setCardBackgroundColor(Color.rgb(0,149,229));
                    textBank.setTextColor(Color.WHITE);
                    textAccount.setTextColor(Color.WHITE);
                    break;
                case 4:
                    cardView.setCardBackgroundColor(Color.rgb(78,195,125));
                    textBank.setTextColor(Color.WHITE);
                    textAccount.setTextColor(Color.WHITE);
                    break;
            }
        }
    }
}
