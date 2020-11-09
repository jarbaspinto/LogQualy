package com.example.logqualy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.logqualy.model.Produto;

public class FormProductActivity extends AppCompatActivity {

    private EditText editProductNameForm;
    private EditText editProductDescriptionForm;
    private EditText editProductDateForm;
    private Button buttonSaveForm;
    private ImageView imageViewPhoto;
    private Intent intent;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product_list);

        //adicionando o botão para voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadViews();
        clickButtons();

        intent = getIntent();

        if (intent.hasExtra(Constantes.EXTRA_EDIT_PRODUCT)){
            getSupportActionBar().setTitle("Product Edit");
            produto = (Produto) intent.getSerializableExtra(Constantes.EXTRA_EDIT_PRODUCT);
            loadFormData();
        }
    }

    private void loadFormData(){
        editProductNameForm.setText(produto.getNameProduct());
        editProductDescriptionForm.setText(produto.getDescriptionProduct());
        editProductDateForm.setText(produto.getDateProduct());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void clickButtons(){
        buttonSaveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (intent.hasExtra(Constantes.EXTRA_EDIT_PRODUCT)){
                    productUpdate();
                    goToListaProdutoActivity(Constantes.PRODUCT_EDIT);
                    Toast.makeText(getApplicationContext(),"Product Edited",Toast.LENGTH_LONG).show();
                }else{
                    getProductFromForm();
                    goToListaProdutoActivity(Constantes.PRODUCT_SAVE);
                    Toast.makeText(getApplicationContext(),"Product Saved",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void goToListaProdutoActivity(String saveOrEditExtra){
        Intent intent = new Intent(FormProductActivity.this, ListaProdutoActivity.class);
        intent.putExtra(saveOrEditExtra, produto);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void productUpdate(){
        String productName = editProductNameForm.getText().toString();
        String productDescription = editProductDescriptionForm.getText().toString();
        String productDate = editProductDateForm.getText().toString();

        produto.setNameProduct(productName);
        produto.setDescriptionProduct(productDescription);
        produto.setDateProduct(productDate);
    }

    private void getProductFromForm(){
        if (validateForm()){
            String nameProduct = editProductNameForm.getText().toString();
            String descriptionProduct = editProductDescriptionForm.getText().toString();
            String dateProduct = editProductDateForm.getText().toString();
            String photoProduct = "adress image";

            produto = new Produto (nameProduct, descriptionProduct, dateProduct, photoProduct);
        }
    }

    private boolean validateForm(){
        if (TextUtils.isEmpty(editProductNameForm.getText())){
            editProductNameForm.setError("Informe o nome do produto");
            editProductNameForm.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(editProductDescriptionForm.getText())){
            editProductDescriptionForm.setError("Informe a descrição do produto");
            editProductDescriptionForm.requestFocus();
            return false;
        }

        return true;
    }

    private void loadViews(){
        editProductNameForm = findViewById(R.id.editTextProductForm);
        editProductDescriptionForm = findViewById(R.id.editTextDescriptionForm);
        editProductDateForm = findViewById(R.id.editTextDateForm);
        buttonSaveForm = findViewById(R.id.buttonSaveProductForm);
        imageViewPhoto = findViewById(R.id.imagePhotoProductForm);

    }
}