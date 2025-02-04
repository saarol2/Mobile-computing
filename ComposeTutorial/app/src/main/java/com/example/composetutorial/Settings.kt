package com.example.composetutorial

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.composetutorial.data.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(navController: NavController, context: Context) {
    Header(navController)
    ChangeUsername(context)
}

@Composable
fun ChangeUsername(context: Context) {
    val userDao = Graph.database.userDao()
    var username by remember { mutableStateOf("") }
    val userFlow = userDao.getUsername(1).collectAsState(initial = null)

    LaunchedEffect(userFlow.value) {
        userFlow.value?.let { user ->
            username = user.username
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 65.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "User:",
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(R.drawable.example_profile),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Enter username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                val newUser = User(id = 1, username = username)
                CoroutineScope(Dispatchers.IO).launch {
                    userDao.insertUser(newUser)
                }
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Save Username")
        }
    }
}

@Composable
fun Header(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.LightGray)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { navController.navigate(Routes.conversation) {
                popUpTo(Routes.conversation) {
                    inclusive = true
                }
            } }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Settings Icon"
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "User settings",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
    }
}