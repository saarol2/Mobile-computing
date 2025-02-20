package com.example.composetutorial.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.example.composetutorial.ui.theme.ComposeTutorialTheme
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.composetutorial.Graph
import com.example.composetutorial.Routes

data class Message(val author: String, val body: String)

@Composable
fun Conversation(navController: NavController, messages: List<Message>) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.LightGray)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Messages",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            )
            IconButton(
                onClick = {
                    navController.navigate(Routes.settings) {
                        popUpTo(Routes.settings) {
                            inclusive = true
                        }
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Settings Icon"
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Conversation
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }
}

@Composable
fun MessageCard(msg: Message) {
    val userDao = Graph.database.userDao()
    val userFlow = userDao.getUsername(1).collectAsState(initial = null)
    val user = userFlow.value

    Row(modifier = Modifier.padding(all = 8.dp)) {
        if (user?.profileImageUri != null) {
            AsyncImage(
                model = user.profileImageUri,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            if (user != null) {
                Text(
                    text = user.username,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Surface(
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,

                color = surfaceColor,

                modifier = Modifier.animateContentSize().padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),

                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    ComposeTutorialTheme {
        Surface {
            MessageCard(
                msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
            )
        }
    }
}

@Preview
@Composable
fun PreviewConversation(navController: NavController = rememberNavController()) {
    ComposeTutorialTheme {
        Conversation(navController, SampleData.conversationSample)
    }
}