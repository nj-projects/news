package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.*
import data.NewsApiClient
import data.model.Article
import io.ktor.client.plugins.*
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    var articles by remember { mutableStateOf(emptyList<Article>()) }
    var headerTitle by remember { mutableStateOf("Headlines") }
    var searchTerm by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    LaunchedEffect(searchTerm) {
        scope.launch {
            try {
                val newsData = if (searchTerm.isNotEmpty()) {
                    NewsApiClient.getSearchNews(searchTerm)
                } else {
                    NewsApiClient.getTopHeadlines()
                }
                articles = newsData.articles
            } catch (e: ClientRequestException) {
                println("Error fetching data: ${e.message}")
            }
        }
    }

    Row {
        SidePanel(
            onMenuSelected = {
                headerTitle = it
                searchTerm = ""
                articles = emptyList()

            },
            onNewsSearch = { _searchTerm, _headerTitle ->
                searchTerm = _searchTerm
                headerTitle = _headerTitle
                articles = emptyList()
            })
    }
}