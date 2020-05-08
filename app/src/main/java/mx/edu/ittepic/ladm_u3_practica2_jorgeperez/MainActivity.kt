package mx.edu.ittepic.ladm_u3_practica2_jorgeperez

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var baseRestuarante = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnInsertar.setOnClickListener {
            insertarPedido()
        }
        btnVerPedido.setOnClickListener {
            var ventanaConsulta = Intent(this,Main2Activity::class.java)
            startActivity(ventanaConsulta)
        }
    }

    private fun insertarPedido() {

        var data = hashMapOf(
            "nombre" to txtNombre.text.toString(),
            "domicilio" to txtDomicilio.text.toString(),
            "celular" to txtTelefono.text.toString()

        )
        baseRestuarante.collection("restaurante")
            .add(data)
            .addOnSuccessListener {
                var pedido = hashMapOf(
                    "descripcion" to txtDescripcion.text.toString(),
                    "precio" to txtPrecio.text.toString().toDouble(),
                    "cantidad" to txtCantidad.text.toString().toInt(),
                    "entregado" to ckBoxPedido.isChecked
                )
                baseRestuarante.collection("restaurante")
                    .document(it.id)
                    .update("pedido",pedido as Map<String,Any>)

                Toast.makeText(this,"SE INSERTÓ CORRECTAMENTE",Toast.LENGTH_LONG).show()
                limpiarCampos()
            }
            .addOnFailureListener {
                Toast.makeText(this,"ERROR: NO SE PUDO INSERTAR",Toast.LENGTH_LONG).show()
            }


    }
    private fun limpiarCampos() {
        txtDescripcion.setText("")
        txtNombre.setText("")
        txtCantidad.setText("")
        txtDomicilio.setText("")
        txtPrecio.setText("")
        txtTelefono.setText("")
        ckBoxPedido.isChecked = false
    }
}
