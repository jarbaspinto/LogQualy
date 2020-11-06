package com.example.logqualy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.logqualy.model.Produto;

public class FormProductActivity extends AppCompatActivity {

    private EditText editProductNameForm;
    private EditText editProductDescriptionForm;
    private EditText editProductDateForm;
    private Button buttonSaveForm;
    private ImageView imageViewPhoto;
    private Boolean eFormEdit = false;
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product_list);
        setTitle("Product Register");

        loadViews();
        clickButtons();

        Intent intent = getIntent();
        if (intent.hasExtra(Constantes.PRODUCT_EDIT)){
            eFormEdit = true;
            produto = (Produto) intent.getSerializableExtra(Constantes.PRODUCT_EDIT);
            loadFormData();
        }
    }

    private void loadFormData(){
        editProductNameForm.setText(produto.getNameProduct());
        editProductDescriptionForm.setText(produto.getDescriptionProduct());
        editProductDateForm.setText(produto.getDateProduct());
    }

    private void clickButtons(){
        buttonSaveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eFormEdit){
                    productUpdate();

                    Intent intent = new Intent(FormProductActivity.this, ListaProdutoActivity.class);
                    intent.putExtra(Constantes.PRODUCT_EDIT, produto);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }else{
                    produto = getProductFromForm();

                    Intent intent = new Intent(FormProductActivity.this, ListaProdutoActivity.class);
                    intent.putExtra(Constantes.PRODUCT_SAVE, produto);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private void productUpdate(){
        String productName = editProductNameForm.getText().toString();
        String productDescription = editProductDescriptionForm.getText().toString();
        String productDate = editProductDateForm.getText().toString();

        produto.setNameProduct(productName);
        produto.setDescriptionProduct(productDescription);
        produto.setDateProduct(productDate);
    }

    private Produto getProductFromForm(){
        if (validateForm()){
            String nameProduct = editProductNameForm.getText().toString();
            String descriptionProduct = editProductDescriptionForm.getText().toString();
            String dateProduct = editProductDateForm.getText().toString();
            String photoProduct = "adress image";

            return new Produto (nameProduct, descriptionProduct, dateProduct, photoProduct);
        }

        return null;
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