package com.example.cadastrodeclientes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FormularioActivity extends AppCompatActivity {

    private EditText edNome;
    private EditText edTelefone;
    private EditText edCelular;
    private Button btnSalvar;
    private String acao;
    private Cliente cliente;
    private ScrollView linear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        edNome = findViewById( R.id.edNome );
        edTelefone = findViewById( R.id.edTelefone );
        edCelular = findViewById( R.id.edCelular );
        btnSalvar = findViewById( R.id.btnSalvar );

        //Iniciar com foco no nome
        edNome.requestFocus();

        //Mascaras de exemplo
        //CPF = "###.###.###-##";
        //FONE_CELULAR = "(##) ####-#####";
        //FORMAT_FONE = "(##) ####-####";
        //FORMAT_CEP = "#####-###";
        //FORMAT_DATE = "##/##/####";
        //FORMAT_HOUR = "##:##";

        //Mascara para o telefone residencial
        final EditText phone_residencial = (EditText) findViewById(R.id.edTelefone);
        phone_residencial.addTextChangedListener(Mask.insert("(##)####-####", phone_residencial));

        //Mascara para o telefone celular
        final EditText phone_celular = (EditText) findViewById(R.id.edCelular);
        phone_celular.addTextChangedListener(Mask.insert("(##)####-#####", phone_celular));

        acao = getIntent().getStringExtra("acao");
        if( acao.equals("editar")){
            carregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View view) {
                salvar();
        }
    });

}

    //Validar telefone residencial
    public static boolean isValidPhoneResid(String valida) {
        valida = valida.replace("(","");
        valida = valida.replace(")","");
        valida = valida.replace("-","");

        if (valida.isEmpty() || valida.length() !=10) return false;
        return true;
    }

    //Validar telefone celular
    public static boolean isValidPhoneCelular(String valida) {
        valida = valida.replace("(","");
        valida = valida.replace(")","");
        valida = valida.replace("-","");

        if (valida.isEmpty() || valida.length() !=11) return false;
        return true;
    }

    private void carregarFormulario(){
        int idCliente = getIntent().getIntExtra("idCliente", 0);
        if( idCliente != 0) {
            cliente = ClienteDAO.getClienteById(this, idCliente);

            edNome.setText( cliente.nome );
            edTelefone.setText( cliente.telefone );
            edCelular.setText( cliente.celular );

        }
    }

    private void salvar(){
    if( edNome.getText().toString().isEmpty()){
        Toast.makeText(this, "Campo nome preenchimento OBRIGATÓRIO!", Toast.LENGTH_SHORT).show();
        edNome.requestFocus();
        return;
    }

    if(edTelefone.getText().toString().isEmpty()){
        Toast.makeText(this, "Campo phone residencial de preenchimento OBRIGATÓRIO!", Toast.LENGTH_SHORT).show();
        edTelefone.requestFocus();
        return;
    }

    if(edCelular.getText().toString().isEmpty()){
        Toast.makeText(this, "Campo celular de preenchimento OBRIGATÓRIO!", Toast.LENGTH_SHORT).show();
        edCelular.requestFocus();
        return;
    }

    if(isValidPhoneResid(edTelefone.getText().toString().trim()) == false){

        Toast.makeText(this, "Phone residencial " + edTelefone.getText() + " inválido!", Toast.LENGTH_SHORT).show();
        edTelefone.setSelection(edTelefone.getText().length(), 0);
        edTelefone.selectAll();
        edTelefone.requestFocus();
        return;
    }

    if(isValidPhoneCelular(edCelular.getText().toString().trim()) == false){

        Toast.makeText(this, "Phone celular " + edCelular.getText() + " inválido!", Toast.LENGTH_SHORT).show();
        edCelular.setSelection(edCelular.getText().length(), 0);
        edCelular.selectAll();
        edCelular.requestFocus();
        return;
    }

        if (acao.equals("novo")) {
            cliente = new Cliente();
        }

        cliente.nome = edNome.getText().toString();
        cliente.telefone = edTelefone.getText().toString();
        cliente.celular = edCelular.getText().toString();

        if( acao.equals("editar")){
            ClienteDAO.editar(cliente, this);
            finish();
        }else {
            ClienteDAO.inserir(cliente, this);
            edNome.setText("");
            edTelefone.setText("");
            edCelular.setText("");
            edNome.requestFocus();
        }
    }
}
