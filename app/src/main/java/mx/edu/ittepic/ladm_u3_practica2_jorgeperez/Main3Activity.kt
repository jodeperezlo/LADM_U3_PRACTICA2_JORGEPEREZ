package mx.edu.ittepic.ladm_u3_practica2_jorgeperez

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {
    var id = ""
    var baseRestuarante = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        var extras = intent.extras

        id = extras!!.getString("id")!!
        editText.setText(extras.getString("nombre"))
        editText2.setText(extras.getString("domicilio"))
        editText3.setText(extras.getString("celular"))
        //pedido
        editText4.setText(extras.getString("descripcion"))
        editText5.setText(extras.getString("precio"))
        editText6.setText(extras.getString("cantidad"))

        if(extras.getString("entregado")=="true"){
            checkBox.isChecked = true
        }


        //Actualizar
        button.setOnClickListener {
            baseRestuarante.collection("restaurante")
                .document(id)
                .update("nombre",editText.text.toString(),
                    "domicilio",editText2.text.toString(),
                    "celular",editText3.text.toString(),
                    "pedido.descripcion",editText4.text.toString(),
                    "pedido.precio",editText5.text.toString().toDouble(),
                    "pedido.cantidad",editText6.text.toString().toInt(),
                    "pedido.entregado",checkBox.isChecked)
                .addOnSuccessListener {

                    Toast.makeText(this,"ACTUALIZACION REALIZADA", Toast.LENGTH_LONG).show()
                    finish()
                }
                .addOnFailureListener {
                    Toast.makeText(this,"ERROR NO SE PUEDE ACTUALIZAR", Toast.LENGTH_LONG).show()
                }
        }


        button2.setOnClickListener {
            finish()
        }


    }

}
