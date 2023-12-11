package com.mak.foodiepal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val users = ArrayList<User>()
        users.add(User("Aiub", "Khan", "a@b.com", "123"))
        users.add(User("Joe", "Martin", "joe@gk.com", "234"))
        users.add(User("Mark", "Anthony", "mark@gk.com", "345"))
        users.add(User("Damien", "Martin", "dam@gk.com", "456"))
        users.add(User("Mark", "Brown", "brown@gk.com", "567"))

        val btnSignIn = findViewById<AppCompatButton>(R.id.btnSignIn)

        btnSignIn.setOnClickListener {
            val txtEmail = findViewById<EditText>(R.id.txtEmail)
            val txtPassword = findViewById<EditText>(R.id.txtPassword)

            //Log.i("Main activity:", txtEmail.text.toString())

            if (txtEmail.text.trim().isEmpty() || txtPassword.text.isEmpty()) {
                showToastMessage("Username and password both required")
                return@setOnClickListener
            }

            val validUser =
                users.find {
                    it.userName == txtEmail.text.toString()
                        .trim() && it.password == txtPassword.text.toString()
                }

            if (validUser == null) {
                showToastMessage("Invalid user or password")
                return@setOnClickListener
            }

            //In java: Intent intent = new Intent(this, ShoppingActivity.class)

            val shoppingIntent = Intent(this, HolderActivity::class.java)
            shoppingIntent.putExtra("userName", validUser.userName)
            startActivity(shoppingIntent)
        }
    }

    private fun showSnackbar(message: String, view: View) {
        val snackbar = Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_SHORT
        )

        // snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.your_custom_color))

        snackbar.show()
    }

    private fun showToastMessage(msg: String) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
    }
}