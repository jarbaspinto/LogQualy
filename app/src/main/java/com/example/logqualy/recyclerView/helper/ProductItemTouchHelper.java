package com.example.logqualy.recyclerView.helper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logqualy.recyclerView.adapter.ProductAdapter;
import static androidx.recyclerview.widget.ItemTouchHelper.Callback.makeMovementFlags;

public class ProductItemTouchHelper extends ItemTouchHelper.Callback{

    private ProductAdapter adapter;

    public ProductItemTouchHelper(ProductAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int moveSwipe = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int moveGrag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        return  makeMovementFlags(moveGrag, moveSwipe);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target){
       int inicialPosition = viewHolder.getAdapterPosition();
       int finalPosition =  target.getAdapterPosition();

       adapter.alteraPosicao(inicialPosition, finalPosition);
       return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction){
        adapter.removeProductItem(viewHolder.getAdapterPosition());
    }
}
