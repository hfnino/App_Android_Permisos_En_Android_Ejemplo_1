package co.com.henryto.permisosenandroid

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

// Video tutorial =>  https://www.youtube.com/watch?v=rdfjT0bQBgs&list=PL8ie04dqq7_OcBYDpvHrcSFVoggLi3cm_&index=18

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnTomarFoto : Button = findViewById(R.id.btnTomarFoto)
        var btnEnviarSmsAlmacenamiento : Button = findViewById(R.id.btnEnviarSmsAlmacenamiento)

        btnTomarFoto.setOnClickListener {
            checkPermissionsCamera()
        }

        btnEnviarSmsAlmacenamiento.setOnClickListener {
            checkPermissionsSmsAlmacenamiento()
        }
    }


    private fun checkPermissionsCamera() {
        //Esta función es para validar si el usuario ha o no ha aceptado el permiso para usar la camara
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED ) {
            // Si el permiso Manifest.permission.CAMERA   NO (!=) ha sido aceptado (PackageManager.PERMISSION_GRANTED)

            requestPermissionCamera() // Esta funcion mostrara mensaje a usuario para que acepte
                                        // o rechace el permiso para usar la camara
        }
        else{
            openCamara()
        }
    }

    private fun openCamara() {
        Toast.makeText(this, "Se abre app para tomar foto", Toast.LENGTH_SHORT).show()
    }

    private fun requestPermissionCamera() {
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
            Toast.makeText(this, "El permiso para usar la camara ha sido rechazado con anterioridad, " +
                    "para permitirlo, debe hacerlos desde la configuración de las aplicaciones de Android ",
                Toast.LENGTH_SHORT).show()
        }
        else{
            // Si se llega aca, es por que nunca se ha solicitado al usuario que acepte o rechace
            // el permiso que se esta solicitando, entonces procedemos a solicitarle el permiso:
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 3461)
            // Como parametos de ActivityCompat.requestPermissions, le ponemos el contexto que es this,
            // tambien va un array de permisos, que para el ejemplo solo seria un array de un solo element que es el
            // permiso que es el la camara, pero pueden ir todos los que se requieran, y por ultimo, le damos un numero cualquiera
            // que nosotros queramos y que representa un codigo, y ese codigo es el que asociamos a los
            // permisos que estamos solicitando, de tal manera que luego podemos usarlo para validaciones en caso de
            //  ue tengamos otros permisos con otros codigos y queramos hacer logica distinta. Cada vez que pidamos
            // un permiso o varios, hay que ponerle un codigo diferente.
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    private fun checkPermissionsSmsAlmacenamiento() {
        //Esta función es para validar si el usuario ha o no ha aceptado el permiso de Enviar SMS
        // y el de Almacenamiento, es decir si el u
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
            != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Si el permiso Manifest.permission.SEND_SMS   NO (!=) ha sido aceptado (PackageManager.PERMISSION_GRANTED)
            // y el permiso Manifest.permission.WRITE_EXTERNAL_STORAGE tampoco (!=) ha sido aceptado (PackageManager.PERMISSION_GRANTED)

            requestPermissionSmsAlmacenamiento() // Esta funcion mostrara mensaje a usuario para que acepte
            // o rechace el permiso para enviar SMS y el de almacenamiento
        }
        else{
            enviarSmsAlmacenamiento()
        }
    }

    private fun enviarSmsAlmacenamiento() {
        Toast.makeText(this, "Se abre app para enviar SMS y para Almacenamiento", Toast.LENGTH_SHORT).show()
    }

    private fun requestPermissionSmsAlmacenamiento() {
        // Esta funcion mostrara mensaje a usuario para que acepte o rechace el permiso para poder
        // enviar Sms y para almacenamiento ...... Ojoo.... si el usuario rechaza el permiso, entonces no podemos mostrarle
        // el mensaje cada rato para que lo acepte, por lo que si el usuario luego necesita que si
        // se acepte el permiso, deberá hacerlo manualmente llengo al administrador de aplicaciones
        // en la configuración del telefono y activar el permiso correspondiente. Teniendo en cuenta
        // lo anterior, debemos comprobar si el usuario ya ha rechazado el permiso con anterioridad
        // o si por el contrario es la primera ves que se le muestra el mensaje para aceptarlo o
        // rechazarlo.

        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)
            || ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            //Este if nos valida si el usuario ya ha rechazado el permiso de camara con anterioridad, entonces:
            Toast.makeText(this, "El permiso para enviar SMS y/o de Almacenamiento" +
                    "han sido rechazados con anterioridad, para permitirlos, debe hacerlos desde la configuración " +
                    "de las aplicaciones de Android ", Toast.LENGTH_SHORT).show()
        }
        else{
            // Si se llega aca, es por que nunca se ha solicitado al usuario que acepte o rechace
            // el permiso que se esta solicitando, entonces procedemos a solicitarle el permiso:
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS,
                Manifest.permission.WRITE_EXTERNAL_STORAGE), 2890)
            // Como parametos de ActivityCompat.requestPermissions, le ponemos el contexto que es this,
            // tambien va un array de permisos, que para el ejemplo es un array de dos elementos que es el
            // permiso que es el poder enviar SMS y el permiso de almacenamiento, pero pueden ir todos los que se
            // requieran, y por ultimo, le damos un numero cualquiera
            // que nosotros queramos y que representa un codigo, y ese codigo es el que asociamos a los
            // permisos que estamos solicitando, de tal manera que luego podemos usarlo para validaciones en caso de
            //  ue tengamos otros permisos con otros codigos y queramos hacer logica distinta. Cada vez que pidamos
            // un permiso o varios, hay que ponerle un codigo diferente.
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    override fun onRequestPermissionsResult( // sobreescribimos el metodo onRequestPermissionsResult
        requestCode: Int, // viene con el metodo onRequestPermissionsResult que estamos sobreescribiendo y nos
        // sirbe para validar el codigo que pusimos en el momento de solicitar los permisos.
        permissions: Array<out String>, // viene con el metodo onRequestPermissionsResult que estamos sobreescribiendo
        grantResults: IntArray  // viene con el metodo onRequestPermissionsResult que estamos sobreescribiendo y nos
        // sirbe para validar cada unos de los permisos que pusimos en el array de permisos
        // en el momento de solicitar los permisos.
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults) // viene con el metodo onRequestPermissionsResult que estamos sobreescribiendo

        if(requestCode == 3461){
            //validamos que el argumento requestCode sea = al codigo que pusimos en el momento de
            //solicitar los permisos, si no es el mismo codigo, entonces es por que esta aceptando otros permisos
            // y si si lo es, entonces =>
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // Si el array de permisos grantResults no esta vacio y ademas el elemento en la posision
                //[0] (que sabemos que es el permiso de la camara) ha sido aceptado (PackageManager.PERMISSION_GRANTED)
                // entonces =>
                openCamara()
            }
            else {
                Toast.makeText(this, "El permiso para usar la camara ha sido rechazado por primera vez", Toast.LENGTH_SHORT).show()
            }
        }

        if(requestCode == 2890){
            //validamos que el argumento requestCode sea = al codigo que pusimos en el momento de
            //solicitar los permisos, si no es el mismo codigo, entonces es por que esta aceptando otros permisos
            // y si si lo es, entonces =>
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                // Si el array de permisos grantResults no esta vacio y ademas el elemento en la posision
                //[0] (que sabemos que es el permiso para enviar SMS) ha sido aceptado (PackageManager.PERMISSION_GRANTED)
                // y ademas el elemento en la posision [1] (que sabemos que es el permiso para Almacenamient)
                // ha sido aceptado (PackageManager.PERMISSION_GRANTED)entonces =>
                //enviarSmsAlmacenamiento()
            }
            else {
                Toast.makeText(this, "El permiso para enviar SMS y/o el de Almacenamiento" +
                        " han sidos rechazados por primera vez", Toast.LENGTH_SHORT).show()
            }
        }
    }
}