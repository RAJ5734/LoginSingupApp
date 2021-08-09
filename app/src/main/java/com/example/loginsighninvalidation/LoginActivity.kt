package com.example.loginsighninvalidation

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.example.loginsighninvalidation.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

//ViewBinding
private  lateinit var  binding: ActivityLoginBinding
//Progressbar
private  lateinit var actionBar: ActionBar
//progressDilog
private  lateinit var progressDialog: ProgressDialog
//FirebaseAuth
private lateinit var firebaseAuth:FirebaseAuth
private  var email=""
private var password=""


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //configire action bar
        //actionBar = supportActionBar
       // actionBar!!.title =  "Login"

        //config progressdilog
        progressDialog= ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Loging In..")
        progressDialog.setCanceledOnTouchOutside(false)
        //init firebase
        firebaseAuth= FirebaseAuth.getInstance()
        checkUser()
        //Handle btnlogin
        binding.loginBtn.setOnClickListener {
            validateDate()
        }

        //handle click open resiter activity
        binding.noAccountTv.setOnClickListener{
            startActivity(Intent(this,SignUpActivity::class.java))
            validateDate()
        }


    }
    private  fun validateDate()
    {
        email=binding.emailTxt.text.toString().trim()
        password=binding.passwordTxt.text.toString().trim()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailTxt.setError("Invalid email format")
        }
        else if(TextUtils.isEmpty(password))
        {
            binding.passwordTxt.error="please enter your password "
        }else{
            firebaseLogin()
        }

    }

    private fun firebaseLogin() {
      progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //login sucees
                progressDialog.dismiss()
                //user info
                val firebaseUser= firebaseAuth.currentUser
                val email=firebaseUser!!.email
                Toast.makeText(this,"Login done ",
                    Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,ActiveActivity::class.java))
                finish()

            }
            .addOnFailureListener{
                //login failed
                progressDialog.dismiss()
                Toast.makeText(this,"Login failed due to ",
                    Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkUser(){
        val firebaseUser= firebaseAuth.currentUser
        if(firebaseUser!=null){
            //user is allredy loggged in
            startActivity(Intent(this,ActiveActivity::class.java))
            finish()
        }
    }
}