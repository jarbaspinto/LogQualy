package com.example.logqualy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.logqualy.model.Produto;

public class FormProductActivity extends AppCompatActivity {

    private EditText editProductNameForm;
    private EditText editProductDescriptionForm;
    private EditText editProductDateForm;
    private Button buttonSaveForm;

    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product_list);

        carregaCampos();

        buttonSaveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormProductActivity.this, ListaProdutoActivity.class);
                startActivity(intent);
            }
        });
    }
    private void carregaDadosFormulario(){
        editProductNameForm.setText(produto.getNameProduct());
        editProductDescriptionForm.setText(produto.getDescriptionProduct());
        editProductDateForm.setText(produto.getDateProduct());
    }
    private void atualizaProduct(){
        String nameProduct = editProductNameForm.getText().toString();
        String descriptionProduct = editProductDescriptionForm.getText().toString();
        String dateProduct = editProductDateForm.getText().toString();

        produto.setNameProduct(nameProduct);
        produto.setDescriptionProduct(descriptionProduct);
        produto.setDateProduct(dateProduct);
    }

    private Produto pegaProductFormulario(){
        String nameProduct = editProductNameForm.getText().toString();
        String descriptionProduct = editProductDescriptionForm.getText().toString();
        String dateProduct = editProductDateForm.getText().toString();

        return new Produto(nameProduct, descriptionProduct, dateProduct);
    }

    private void carregaCampos(){
        editProductNameForm = findViewById(R.id.editTextProductForm);
        editProductDescriptionForm = findViewById(R.id.editTextDescriptionForm);
        editProductDateForm = findViewById(R.id.editTextDateForm);
        buttonSaveForm = findViewById(R.id.buttonSaveProductForm);

    }
}