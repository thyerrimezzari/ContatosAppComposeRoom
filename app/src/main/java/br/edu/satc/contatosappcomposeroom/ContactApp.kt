package br.edu.satc.contatosappcomposeroom

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ContactApp(users: List<User>, addUser: (user: User) -> Unit) {
    var contacts by remember { mutableStateOf(users) }
    var nome by remember { mutableStateOf(TextFieldValue("")) }
    var sobrenome by remember { mutableStateOf(TextFieldValue("")) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Lista de Contatos",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(contacts) { contact ->
                ContactItem(contact)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = nome,
                onValueChange = { nome = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
                    .padding(8.dp)
            )
            BasicTextField(
                value = sobrenome,
                onValueChange = { sobrenome = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
                    .padding(8.dp)
            )

            Button(
                onClick = {
                    if (nome.text.isNotEmpty() && sobrenome.text.isNotEmpty()) {
                        val user = User(0, nome.text, sobrenome.text)
                        addUser(user)
                        contacts = contacts + user
                        nome = TextFieldValue("") // Limpa o campo de texto
                        sobrenome = TextFieldValue("") // Limpa o campo de texto
                        keyboardController?.hide()
                    }
                }
            ) {
                Text(text = "Adicionar")
            }
        }
    }
}

@Composable
fun ContactItem(user: User) {
    Text(
        text = user.firstName + " " + user.lastName,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun ContactAppPreview() {
    //ContactApp()
}
