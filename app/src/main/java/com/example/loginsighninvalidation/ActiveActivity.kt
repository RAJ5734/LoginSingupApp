package com.example.loginsighninvalidation

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewFragment
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.loginsighninvalidation.databinding.ActivityActiveBinding
import com.example.loginsighninvalidation.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

//ViewBinding
private  lateinit var binding: ActivityActiveBinding
//Action Bar
private lateinit var actionBar: ActionBar
//FirebaseAuth
private  lateinit var  firebaseAuth: FirebaseAuth

class ActiveActivity : AppCompatActivity() {

  private  val exampleList = generateDummyList(500)
    private val adapter=ExampleAdapter(exampleList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityActiveBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.setHasFixedSize(true)


        //actionBar =supportActionBar
        //actionBar!!.title="Profie"
        //init firbaseauth
        firebaseAuth= FirebaseAuth.getInstance()
        checkUser()

        //handle logout button
        binding.lougoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()

            //handle icon click
           // binding.bookImg.setOnClickListener {


            //}

        }
    }

    private fun checkUser() {
        val firebaseUser= firebaseAuth.currentUser
        if(firebaseUser!=null){
            //user not null, user is looged
            val email=firebaseUser.email
            //set to text view
            //binding.emailTV.text=email

        }else{
            //user us null, user us not logedin
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }
    private fun generateDummyList(size: Int): List<ProuductList> {
        val list = ArrayList<ProuductList>()
        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.applogo2
                1 -> R.drawable.book
                else -> R.drawable.entertenment
            }
            val item = ProuductList(drawable, "Item $i", "Line 2")
            list += item
        }
        return list


    }


}