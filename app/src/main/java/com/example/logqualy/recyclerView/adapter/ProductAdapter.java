package com.example.logqualy.recyclerView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logqualy.R;
import com.example.logqualy.model.Produto;
import com.example.logqualy.recyclerView.adapter.listener.ProductItemClickListener;

import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder{

    private Context context;
    private List<Produto> produtos;
    private ProductItemClickListener onItemClickListener;

    public ProductAdapter(Context context, List<Produto> produtos){
        this.context = context;
        this.produtos = produtos;
        }

        public void setOnItemClickListener(ProductItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
        }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_product,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Produto produto = produtos.get(position);

        holder.vincula(produto);
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }

    public void removeProductItem(int adapterPosition){
        produtos.remove(adapterPosition);
        notifyDataSetChanged();
    }
    public void alteraPosicao(int posicaoInical, int posicaoFinal){
        Collections.swap(produtos, posicaoInical, posicaoFinal);
        notifyItemChanged(posicaoInical, posicaoFinal);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textProductName;
        private TextView textProductDescription;
        private TextView getTextProductData;
    }
}
