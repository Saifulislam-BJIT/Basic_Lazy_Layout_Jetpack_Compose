package com.saiful.statejetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saiful.statejetpackcompose.ui.theme.StateJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StateJetpackComposeTheme {
                Column(
//                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    WaterCount()
                    LazyRowContent()
                }
            }
        }
    }
}

@Composable
fun WaterCount() {
    var count by rememberSaveable { mutableStateOf(0) }
    Column(modifier = Modifier.padding(16.dp)) {
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask) {
                WellnessTaskItem(
                    onClose = { showTask = false },
                    taskName = "Have you taken your 15 minute walk today?"
                )
            }
            Text("You've had $count glasses")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Button(onClick = { count++ }, enabled = count < 10) {
                Text("Add one")
            }
            Button(
                onClick = { count = 0 },
                Modifier.padding(start = 8.dp)
            ) {
                Text("Clear water count")
            }
        }
    }
}

@Composable
fun WellnessTaskItem(
    taskName: String,
    onClose: () -> Unit,
) {
    Row {
        Text(text = taskName)
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StateJetpackComposeTheme {
        WaterCount()
    }
}

@Composable
fun LazyRowContent() {
    val charList = ('A'..'Z').toList()

    Column(
        modifier = Modifier
            .border(1.dp, Color.Cyan)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Character List - Lazy Row")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "More")
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(charList.size) {
                Text(
                    text = charList[it].toString(),
                    modifier = Modifier
                        .border(1.dp, Color.Magenta)
                        .padding(8.dp)
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(top = 8.dp)
            .border(1.dp, Color.Cyan)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Character List - Lazy Vertical Grid")
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "More")
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item(
                span = { GridItemSpan(3) }
            ) {
                Text(
                    text = "Vertical Grid",
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.Cyan)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }
            items(
                charList.size,
                contentType = {it.toString()},
                span = {
                    GridItemSpan(
                        when(it % 3) {
                            0 -> 1
                            1 -> 2
                            else -> 3
                        }
                    )
                }
            ) {
                Text(
                    text = charList[it].toString(),
                    modifier = Modifier
                        .border(1.dp, Color.Magenta)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}