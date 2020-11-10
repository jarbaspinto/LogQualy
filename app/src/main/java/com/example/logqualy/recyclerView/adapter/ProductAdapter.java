package com.example.logqualy.recyclerView.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logqualy.Constantes;
import com.example.logqualy.ListaProdutoActivity;
import com.example.logqualy.R;
import com.example.logqualy.model.Produto;
import com.example.logqualy.recyclerView.adapter.listener.ProductItemClickListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
        notifyItemMoved(posicaoInical, posicaoFinal);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textProductName;
        private TextView textProductDescription;
        private TextView textProductData;
        private ImageView imageProduct;
        private ImageView imageCameraForm;

        public ViewHolder(@NonNull View productView){
            super(productView);
            textProductName = productView.findViewById(R.id.productProduto);
            textProductDescription = productView.findViewById(R.id.productDescricao);
            textProductData = productView.findViewById(R.id.productData);
            imageProduct = productView.findViewById(R.id.imageProduct);
            imageCameraForm = productView.findViewById(R.id.imagePhotoProductForm);
        }

        private void vincula(Produto produto){
            textProductName.setText(produto.getNameProduct());
            textProductDescription.setText(produto.getDescriptionProduct());
            textProductData.setText(produto.getDateProduct());

            //Para recuperar uma imagem é necessário ter uma instancia do storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //aqui estamosa pegando o caminho do servidor
            StorageReference storageRef = storage.getReference();

            //Aqui estamos pegando a referencia do caminho do arquivo
            StorageReference reference = storageRef.child("image/" + produto.getPhotoProduct());

            //Essa constante determinará o tamanho máximo do arquivo
            final long ONE_MEGABYTE = 1024*1024;

            //Com a referência chamamos o método getBytes passando o tamanho máximo
            reference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    //A imagem vem como bytes e precisa ser transformada em bitmap para ser exibida no imageView
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0 , bytes.length);

                    //passando a imagem para imageview
                    imageProduct.setImageBitmap(bmp);
                }
            });
        }
    }
}
