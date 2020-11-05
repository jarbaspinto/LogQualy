package com.example.logqualy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.logqualy.model.Produto;
import com.example.logqualy.recyclerView.adapter.ProductAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class ListaProdutoActivity extends AppCompatActivity {

    private static final String TAG = "SAVE_PRODUCT";
    private RecyclerView recyclerViewProductList;
    private FloatingActionButton fabCriaItemList;
    private FirebaseUser user;
    private FirebaseFirestore db;

    static List<Produto> produtos;
    private ProductAdapter adapter;

    private int positionItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        db = FirebaseFirestore.getInstance();

        fabCriaItemList = findViewById(R.id.fabCriaItem);

        buttonClick();


        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }
    private void buttonClick(){
        fabCriaItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaProdutoActivity.this, FormProductActivity.class);
                startActivityForResult(intent, Constantes.REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constantes.REQUEST_CODE && data.hasExtra(Constantes.PRODUCT_SAVE)){
            if (resultCode == Activity.RESULT_OK){
                Produto produto = (Produto) data.getSerializableExtra(Constantes.PRODUCT_SAVE);

                db.collection(Constantes.PRODUCTS_COLLECTION).add(produto)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_item_logout:
                FirebaseAuth.getInstance().signOut();
                gotoLoginActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
//        if (item.getItemId() == R.id.menu_item_logout){
//            FirebaseAuth.getInstance().signOut();
//        }else{
//            return super.onOptionsItemSelected(item);
        }

    }

    private void gotoLoginActivity(){
        startActivity(new Intent(
                this,
                MainActivity.class
        ));
        finish();
    }

    private void recyclerViewConfigure(){
        recyclerViewProductList = findViewById(R.id.recyclerViewListaProduto);
        recyclerViewProductList.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(getApplicationContext(), produtos);
        recyclerViewProductList.setAdapter(adapter);
        adapter.
    }
}