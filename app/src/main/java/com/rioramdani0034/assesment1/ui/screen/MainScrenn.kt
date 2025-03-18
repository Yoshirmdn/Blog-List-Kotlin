package com.rioramdani0034.assesment1.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rioramdani0034.assesment1.ui.model.Blog
import com.rioramdani0034.assesment1.ui.model.blogList
import com.rioramdani0034.assesment1.ui.theme.Assesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedBlog by remember { mutableStateOf<Blog?>(null) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = if (selectedBlog == null) "Blog List" else selectedBlog!!.title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    if (selectedBlog != null) {
                        IconButton(onClick = { selectedBlog = null }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Kembali ke daftar blog"
                            )
                        }
                    } else {
                        IconButton(onClick = { /* do something */ }) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = "Home"
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            imageVector = Icons.Filled.Info,
                            contentDescription = "Info"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        // Menampilkan BlogListScreen atau BlogDetailScreen berdasarkan selectedBlog
        if (selectedBlog == null) {
            BlogListScreen(
                // Menggunakan blogList yang didefinisikan di Blog.kt
                blogs = blogList,
                onBlogClick = { blog -> selectedBlog = blog },
                modifier = Modifier.padding(innerPadding)
            )
        }else {
            BlogDetailScreen(
                blog = selectedBlog!!,
                onBack = { selectedBlog = null },
                modifier = Modifier.padding(innerPadding)
                )
            }
    }
}

@Composable
fun BlogListScreen(blogs: List<Blog>, onBlogClick: (Blog) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp)
    ) {
        items(blogs) { blog ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { onBlogClick(blog) },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = blog.title, style = MaterialTheme.typography.titleLarge)
                    Text(text = "By ${blog.author}", style = MaterialTheme.typography.bodyMedium)
                    Text(text = "Uploaded on: ${blog.uploadDate}", style = MaterialTheme.typography.bodySmall)
                    Text(text = "Category: ${blog.category}", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun BlogDetailScreen(blog: Blog, onBack: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = blog.title, style = MaterialTheme.typography.titleLarge)
        Text(text = "By ${blog.author}", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Uploaded on: ${blog.uploadDate}", style = MaterialTheme.typography.bodySmall)
        Text(text = "Category: ${blog.category}", style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = blog.content, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onBack) {
            Text("Paham")
            }
        }
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    Assesment1Theme {
        MainScreen()
    }
}