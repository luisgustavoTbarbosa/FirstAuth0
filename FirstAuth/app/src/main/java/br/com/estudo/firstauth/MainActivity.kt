package br.com.estudo.firstauth

import android.net.Credentials
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.provider.WebAuthProvider
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    private lateinit var account: Auth0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            loginWithBrowser()
        }

        account = Auth0(
            "9onIOkDNkm0y7WZAhpRKijZTlk0amZUw",
            "dev-txyag4u2.us.auth0.com"
        )
    }

    private fun loginWithBrowser() {
        // Setup the WebAuthProvider, using the custom scheme and scope.

        WebAuthProvider.login(account)
            .withScheme("demo")
            .withScope("openid profile email")
            // Launch the authentication passing the callback where the results will be received
            .start(this, object : Callback,
                com.auth0.android.callback.Callback<com.auth0.android.result.Credentials, AuthenticationException> {
                override fun onFailure(exception: AuthenticationException) {
                    // Something went wrong!
                }

                // Called when authentication completed successfully
                override fun onSuccess(credentials: com.auth0.android.result.Credentials) {
                    // Get the access token from the credentials object.
                    // This can be used to call APIs
                    val accessToken = credentials.accessToken
                }

            })
    }
}