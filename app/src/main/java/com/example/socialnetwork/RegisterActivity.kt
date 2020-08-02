
package com.example.socialnetwork

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.socialnetwork.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private val mAuth= FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        register.setOnClickListener {
            loading.visibility=View.VISIBLE
            mAuth.createUserWithEmailAndPassword(username.text.toString(),password.text.toString())
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val user=mAuth.currentUser
                        loading.visibility= View.GONE
                        updateUi(user)
                    } else {
                        Toast.makeText(this,"Authentication is failed",Toast.LENGTH_LONG).show()
                        loading.visibility= View.GONE
                    }
                }
        }
    }
    private fun updateUi(user: FirebaseUser?){
        if(user!=null){
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}