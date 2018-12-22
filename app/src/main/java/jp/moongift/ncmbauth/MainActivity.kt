package jp.moongift.ncmbauth

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import jp.moongift.ncmbauth.BuildConfig
import com.nifcloud.mbaas.core.*



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NCMB.initialize(applicationContext, BuildConfig.APPLICATION_KEY, BuildConfig.CLIENT_KEY)

        // 会員登録処理用のボタン
        var btnSignUp : Button = findViewById(R.id.signUp)
        btnSignUp.setOnClickListener {
            this.signUp()
        }

        // ログイン用のボタン
        var btnSignIn : Button = findViewById(R.id.signIn)
        btnSignIn.setOnClickListener {
            this.signIn()
        }
    }

    // 会員登録処理
    fun signUp() {
        var userName = (findViewById(R.id.userName) as TextView).text.toString()
        var password = (findViewById(R.id.password) as TextView).text.toString()
        val user = NCMBUser()
        NCMBUser.logout()
        user.userName = userName
        user.setPassword(password)
        user.saveInBackground {e ->
            if (e != null) {
                Log.d("[Error]", e.toString())
            } else {
                (findViewById(R.id.lblStats) as TextView).text = "Sign Up successful"
            }
        }
    }

    // ログイン処理
    fun signIn() {
        var userName = (findViewById(R.id.userName) as TextView).text.toString()
        var password = (findViewById(R.id.password) as TextView).text.toString()
        NCMBUser.loginInBackground(userName, password, {user, e ->
            if (e != null) {
                Log.d("[Error]", e.toString())
            } else {
                (findViewById(R.id.lblStats) as TextView).text = "Log in successful by ${user.userName}"
            }

        })
    }
}
