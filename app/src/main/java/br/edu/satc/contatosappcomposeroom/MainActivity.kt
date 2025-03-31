package br.edu.satc.contatosappcomposeroom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import br.edu.satc.contatosappcomposeroom.ui.theme.ContatosAppComposeRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Init database.
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "br.edu.satc.contatosapp"
        )
            .allowMainThreadQueries() // isso habilita o uso do ROOM em Activitys normais
            .build()

        // inicializa o DAO
        val userDao = db.userDao()

        // pega todos os users cadastrados no banco
        val users: List<User> = userDao.getAll()

        setContent {
            ContatosAppComposeRoomTheme {
                ContactApp(users) {
                    userDao.insertAll(it)
                }
            }
        }
    }
}