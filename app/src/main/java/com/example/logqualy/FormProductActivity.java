package com.example.logqualy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.logqualy.model.Produto;
import com.google.firebase.storage.FirebaseStorage;

import java.io.ByteArrayOutputStream;

public class FormProductActivity extends AppCompatActivity {

    private EditText editProductNameForm;
    private EditText editProductDescriptionForm;
    private Button buttonSaveForm;
    private ImageView imageViewPhoto;
    private Intent intent;
    private Produto produto;
    private byte[] imageInByte;
    private FirebaseStorage mStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product_list);

        //adicionando o botão para voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadViews();
        clickButtons();
        mStorage = FirebaseStorage.getInstance();
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
        imageViewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }

    private void goToListaProdutoActivity(String saveOrEditExtra){
        Intent intent = new Intent(FormProductActivity.this, ListaProdutoActivity.class);
        //Esse extra envia a imagem para a tela de lista de produtos
        intent.putExtra(Constantes.EXTRA_IMAGE_PATH, imageInByte);

        //Esse extra envia um objeto Produto para a tela de lista de produtos
        intent.putExtra(saveOrEditExtra, produto);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void productUpdate(){
        String productName = editProductNameForm.getText().toString();
        String productDescription = editProductDescriptionForm.getText().toString();

        produto.setNameProduct(productName);
        produto.setDescriptionProduct(productDescription);
    }

    private void getProductFromForm(){
        if (validateForm()){
            String nameProduct = editProductNameForm.getText().toString();
            String descriptionProduct = editProductDescriptionForm.getText().toString();
            String photoProduct = "adress image";

            //          Drawable drawable = imageViewPhoto.getDrawable();
//            Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();

            produto = new Produto (nameProduct, descriptionProduct);
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
        buttonSaveForm = findViewById(R.id.buttonSaveProductForm);
        imageViewPhoto = findViewById(R.id.imagePhotoProductForm);

    }

    //Esse método criar a startar uma intent para abrir o App de câmera do celular
    private void dispatchTakePictureIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(takePictureIntent, Constantes.REQUEST_IMAGE_CAPTURE);
        }
    }

    //Quando o usuário finalizar a câmera, o App voltará para o método abaixo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constantes.REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //Pegando o extra para futura manipulação
            Bundle extras = data.getExtras();

            //Pegando imagem que veio da intent
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //Carregando a imagem na imageView
            imageViewPhoto.setImageBitmap(imageBitmap);

            //Transformando a imagem em JPGE
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

            //Transformando a imagem num array de bites
            imageInByte = stream.toByteArray();
        }
    }
}