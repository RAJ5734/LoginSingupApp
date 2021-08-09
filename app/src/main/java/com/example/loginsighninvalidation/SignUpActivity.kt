package com.example.loginsighninvalidation

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import com.example.loginsighninvalidation.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

//ViewBinding
private  lateinit var binding: ActivitySignUpBinding
//ActionBAr
private lateinit var actionBar: ActionBar
//progressBar
private lateinit var  progresDialog: ProgressDialog
//firebase
private lateinit var firebaseAuth: FirebaseAuth
private var email=""
private  var password=""


class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //configure acrion bar
        //actionBar = supportActionBar
//        actionBar!!.title="Sing Up"
        //Enable back button
//        actionBar!!.setDisplayHomeAsUpEnabled(true)
      //  actionBar!!.setDisplayHomeAsUpEnabled(true)

        //config progressdilog
        progresDialog= ProgressDialog(this)
        progresDialog.setTitle("Please wait")
       progresDialog.setMessage("Creatong new account IN.")
       progresDialog.setCanceledOnTouchOutside(false)

        //init firbase dialog
        firebaseAuth= FirebaseAuth.getInstance()
        //handle click bigingn singup
        binding.signupBtn.setOnClickListener {
            validateDta()
        }

    }

    private fun validateDta() {
        email=binding.emailTxt.text.toString().trim()
        password=binding.passwordTxt.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailTxt.setError("Invalid email format")
        }
        else if(TextUtils.isEmpty(password))
        {
            binding.passwordTxt.error="please enter your password "
        }else if(password.length<6){
            binding.passwordTxt.error="Password shoud be greter than 6 charecter"
        }
        else{
            firebaseSingnUp()
        }
    }

    private fun firebaseSingnUp() {
        //show progress
        progresDialog.show()
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //singnUp succes
                progresDialog.dismiss()
                //get current user
                val firebaseUser= firebaseAuth.currentUser
                val email=firebaseUser!!.email
                Toast.makeText(this,"Account crete with accounnt  ",Toast.LENGTH_SHORT).show()
                //open profile
                startActivity(Intent(this,ActiveActivity::class.java))
                finish()

            }
            .addOnFailureListener { e->
                progresDialog.dismiss()
                Toast.makeText(this,"SingnUp faild due to some ression ",Toast.LENGTH_SHORT).show()

            }
    }

    override fun onSupportNavigateUp(): Boolean {
       onBackPressed()
        return super.onSupportNavigateUp()
    }
}