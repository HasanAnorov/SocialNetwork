package com.example.socialnetwork.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.socialnetwork.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val db =FirebaseFirestore.getInstance()
    private val mAuth=FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showData()

        btnSave.setOnClickListener{

           setLoading(true)

            val map :MutableMap <String,Any> = mutableMapOf()
            map["username"]= etUsername.text.toString()
            map["email"]=etEmailAddress.text.toString()
            map["info"]=etInfo.text.toString()
            map["phone"]=etPhone.text.toString()

            db.collection("users").document(mAuth.currentUser!!.uid).set(map)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(),"Your profile data has been changed successfully",Toast.LENGTH_LONG).show()
                    setLoading(false)
                }
                .addOnFailureListener {
                    Toast.makeText(requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
                    setLoading(false)
                }
        }
    }
    private fun setLoading(isLoading:Boolean){
        if(isLoading)progressBar.visibility=View.VISIBLE
        else progressBar.visibility=View.GONE
        etEmailAddress.isEnabled=!isLoading
        etInfo.isEnabled=!isLoading
        etPhone.isEnabled=!isLoading
        etUsername.isEnabled=!isLoading
        btnSave.isEnabled=!isLoading
    }
    private fun showData(){
    setLoading(true)
        db.collection("users").document(mAuth.currentUser!!.uid).get()
            .addOnSuccessListener {
                etEmailAddress.setText(it.get("email").toString())
                etUsername.setText(it.get("username").toString())
                etPhone.setText(it.get("phone").toString())
                etInfo.setText(it.get("info").toString())
    setLoading(false)
            }
    }
}