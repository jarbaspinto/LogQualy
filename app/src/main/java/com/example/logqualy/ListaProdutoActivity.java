package com.example.logqualy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ListaProdutoActivity extends AppCompatActivity {

    private RecyclerView recyclerProductList;
    private FloatingActionButton fabCriaItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produto);

        fabCriaItemList = findViewById(R.id.fabCriaItem);
        fabCriaItemList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaProdutoActivity.this, FormProductActivity.class);
                startActivity(intent);
            }
        });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constantes.SOLICITA_NOVO_ITEM && data.hasExtra(Constantes.CHAVE_NOVO_ITEM)){
            if (resultCode == Activity.RESULT_OK){

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
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}