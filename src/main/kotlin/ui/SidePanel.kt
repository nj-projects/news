package ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import utils.cursor

@Composable
@Preview
fun SidePanel(onMenuSelected: (header: String) -> Unit, onNewsSearch: (searchTerm: String, header: String) -> Unit) {
    var searchTerm by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.15f)
            .fillMaxHeight()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val bitmap = useResource("IYP-Main_Logo.png") {
            loadImageBitmap(it)
        }

        Image(bitmap, "Logo", modifier = Modifier.width(100.dp))
        Spacer(modifier = Modifier.padding(18.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            singleLine = true,
            placeholder = {
                Text("Search")
            },
            value = searchTerm,
            onValueChange = {
                searchTerm = it
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        onNewsSearch(searchTerm, "Results for '$searchTerm'")
                    },
                    modifier = Modifier.size(40.dp).pointerHoverIcon(cursor())
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search button",
                        tint = Color.Blue
                    )
                }
            }
        )
        TextButton(
            onClick = {
                searchTerm = ""
                onMenuSelected("Headlines")
            }
        ) {
            Text(
                "Headlines",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.pointerHoverIcon(cursor())
            )
        }
    }

}