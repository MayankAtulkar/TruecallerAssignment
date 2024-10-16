package com.impeccabletech.truecallerassignment.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.impeccabletech.truecallerassignment.preview.FakeMainViewModel
import com.impeccabletech.truecallerassignment.ui.theme.TruecallerAssignmentTheme
import com.impeccabletech.truecallerassignment.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TruecallerAssignmentTheme {
                MainScreen(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val char15 by viewModel.character15.observeAsState()
    val every15thChar by viewModel.every15thCharacter.observeAsState(listOf())
    val wordCount by viewModel.wordCount.observeAsState(mapOf())
    val isLoading by viewModel.isLoading.observeAsState(false)

    var showMore by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(40.dp))

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { viewModel.fetchWebContent() }
            ) {
                Text(text = "Load Content")
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        if (isLoading) {
            item {
                LoadingIndicator()
            }
        } else {
            // 15th Character Result
            item {
                Text(
                    text = "15th Character: ${char15 ?: "No character found"}",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            }

            // Every 15th Character Task
            item {
                Text(text = "Every 15th Character:", style = MaterialTheme.typography.titleMedium)

                val displayedChars = if (showMore) every15thChar.joinToString(", ") else every15thChar.take(60).joinToString(", ")

                Text(
                    text = displayedChars,
                    maxLines = if (showMore) Int.MAX_VALUE else 4,
                    overflow = TextOverflow.Ellipsis
                )

                if (every15thChar.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { showMore = !showMore }) {
                        Text(text = if (showMore) "Show Less" else "Show More")
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            }

            // Word Count Task
            item {
                Text(text = "Word Count:", style = MaterialTheme.typography.titleMedium)
            }

            items(wordCount.toList()) { (word, count) ->
                WordCountRow(word = word, count = count)
            }
        }

        // Spacer at the bottom
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun LoadingIndicator() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator() // Loader
    }
}

@Composable
fun WordCountRow(word: String, count: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = word, modifier = Modifier.weight(1f))
        Text(text = count.toString(), modifier = Modifier.weight(1f), textAlign = TextAlign.End)
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    TruecallerAssignmentTheme {
        val viewModel: FakeMainViewModel = viewModel()
        MainScreen(viewModel = viewModel)
    }
}

