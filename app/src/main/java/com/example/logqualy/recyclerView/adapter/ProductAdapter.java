package com.example.logqualy.recyclerView.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logqualy.Constantes;
import com.example.logqualy.ListaProdutoActivity;
import com.example.logqualy.R;
import com.example.logqualy.model.Produto;
import com.example.logqualy.recyclerView.adapter.listener.ProductItemClickListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private Context context;
    private List<Produto> produtoList;
    private ProductItemClickListener onItemClickListener;

    public ProductAdapter(Context context, List<Produto> produtoList){
        this.context = context;
        this.produtoList = produtoList;
        }

        public void setOnItemClickListener(ProductItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_product,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Produto produto = produtoList.get(position);
        holder.vincula(produto);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.productClick(produto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    public void removeProductItem(int adapterPosition){
        Produto produto = produtoList.get(adapterPosition);
        FirebaseFirestore.getInstance()
                .collection(Constantes.PRODUCTS_COLLECTION)
                .document(produto.getId())
                .delete();
        produtoList.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }
    public void alteraPosicao(int posicaoInical, int posicaoFinal){
        Collections.swap(produtoList, posicaoInical, posicaoFinal);
        notifyItemChanged(posicaoInical, posicaoFinal);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textProductName;
        private TextView textProductDescription;
        private TextView textProductData;
        private ImageView imageProduct;

        public ViewHolder(@NonNull View productView){
            super(productView);
            textProductName = productView.findViewById(R.id.productProduto);
            textProductDescription = productView.findViewById(R.id.productDescricao);
            textProductData = productView.findViewById(R.id.productData);
            imageProduct = productView.findViewById(R.id.imageProduct);
        }

        private void vincula(Produto produto){
            textProductName.setText(produto.getNameProduct());
            textProductDescription.setText(produto.getDescriptionProduct());
            textProductData.setText(produto.getDateProduct());

        }
    }
}
