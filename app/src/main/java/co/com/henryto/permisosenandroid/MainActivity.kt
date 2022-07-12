package co.com.henryto.permisosenandroid

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnTomarFoto : Button = findViewById(R.id.btnTomarFoto)

        btnTomarFoto.setOnClickListener {
            checkPermissions()
        }
    }

    private fun checkPermissions() {
        //Esta funsión es para validar si el usuario ha o no ha aceptado los permisos
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED ) {
            // Si el permiso Manifest.permission.CAMERA   NO (!=) ha sido aceptado (PackageManager.PERMISSION_GRANTED)

            requestCameraPermission() // Esta funcion mostrara mensaje a usuario para que acepte
                                        // o rechace el permiso para usar la camara
        }
        else{
            Toast.makeText(this, "Se abre app para tomar foto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestCameraPermission() {
        // Esta funcion mostrara mensaje a usuario para que acepte o rechace el permiso para usar
        // la camara ...... Ojoo.... si el usuario rechaza el permiso, entonces no podemos mostrarle
        // el mensaje cada rato para que lo acepte, por lo que si el usuario luego necesita que si
        // se acepte el permiso, deberá hacerlo manualmente llengo al administrador de aplicaciones
        // en la configuración del telefono y activar el permiso correspondiente. Teniendo en cuenta
        // lo anterior, debemos comprobar si el usuario ya ha rechazado el permiso con anterioridad
        // o si por el contrario es la primera ves que se le muestra el mensaje para aceptarlo o
        // rechazarlo.

        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            //Este if nos valida si el usuario ya ha rechazado el permiso de camara con anterioridad, entonces:
        }
        else{
            // Si se llega aca, es por que nunca se ha solicitado al usuario que acepte o rechace
            // el permiso que se esta solicitando, entonces procedemos a solicitarle el permiso:
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 3461)
            // Como parametos de ActivityCompat.requestPermissions, le ponemos el contexto que es this,
            // tambien va un array de permisos, que para el ejemplo solo pusimos un permiso que es el de
            // la camara, pero pueden ir todos los que se requieran, y por ultimo, le damos un numero cualquiera
            // que nosotros queramos y que representa un codigo, y ese codigo es el que asociamos a los
            // permisos que estamos solicitando, de tal manera que luego podemos usarlo para validaciones en caso de
            //  ue tengamos otros permisos con otros codigos y queramos hacer logica distinta. Cada vez que pidamos
            // un permiso o varios, hay que ponerle un codigo diferente.
        }
    }

    override fun onRequestPermissionsResult( // sobreescribimos el metodo onRequestPermissionsResult
        requestCode: Int, // viene con el metodo onRequestPermissionsResult que estamos sobreescribiendo y nos
                          // sirbe para validar el codigo que pusimos en el momento de solicitar los permisos.
        permissions: Array<out String>, // viene con el metodo onRequestPermissionsResult que estamos sobreescribiendo
        grantResults: IntArray  // viene con el metodo onRequestPermissionsResult que estamos sobreescribiendo y nos
                                // sirbe para validar cada unos de los permisos que pusimos en el array de permisos
                                // que pusimos en el momento de solicitar los permisos.
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 3461){
            //validamos que el argumento requestCode sea = al codigo que pusimos en el momento de
            //solicitar los permisos.


        }
    }
}