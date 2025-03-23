package com.rioramdani0034.assesment1.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rioramdani0034.assesment1.R
import com.rioramdani0034.assesment1.ui.model.Blog
import com.rioramdani0034.assesment1.ui.model.blogList
import com.rioramdani0034.assesment1.ui.theme.Assesment1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddBlogScreen(navController: NavHostController ) {
    var title by rememberSaveable { mutableStateOf("") }
    var titleError by rememberSaveable { mutableStateOf(false) }
    var content by rememberSaveable { mutableStateOf("") }
    var contentError by rememberSaveable { mutableStateOf(false) }
    var author by rememberSaveable { mutableStateOf("") }
    var authorError by rememberSaveable { mutableStateOf(false) }
    val categoryOptions = listOf("Teknologi", "Pendidikan", "Kesehatan", "Kuliner", "Gaya Hidup")
    var selectedCategory by remember { mutableStateOf(categoryOptions[0]) }
    var expanded by remember { mutableStateOf(false) }
    var categoryError by rememberSaveable { mutableStateOf(false) }
    var uploadDate by rememberSaveable { mutableStateOf("") }
    var uploadDateError by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tambah Blog Baru") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Judul") },
                supportingText = { ErrorHint(titleError) },
                isError = titleError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("Konten") },
                supportingText = { ErrorHint(contentError) },
                isError = contentError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = author,
                onValueChange = { author = it },
                label = { Text("Penulis") },
                supportingText = { ErrorHint(authorError) },
                isError = authorError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = it }
            ) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = { selectedCategory = it },
                    readOnly = true,
                    label = { Text("Kategori") },
                    supportingText = { ErrorHint(categoryError) },
                    isError = categoryError,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categoryOptions.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = uploadDate,
                onValueChange = { uploadDate = it },
                label = { Text("Tanggal Unggah (yyyy-mm-dd)") },
                supportingText = { ErrorHint(uploadDateError) },
                isError = uploadDateError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    // Validasi semua data sebelum submit
                    titleError = title.isBlank()
                    contentError = content.isBlank()
                    authorError = author.isBlank()
                    categoryError = selectedCategory.isBlank()
                    uploadDateError = uploadDate.isBlank()

                    // Jika semua data valid, tambahkan blog baru
                    if (!titleError && !contentError && !authorError && !categoryError && !uploadDateError) {
                        val newBlog = Blog(
                            id = blogList.size + 1,
                            title = title,
                            content = content,
                            author = author,
                            category = selectedCategory,
                            uploadDate = uploadDate
                        )
                        blogList.add(newBlog)
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.submit))
            }
            // Reset Button
            Button(
                onClick = {
                    title = ""
                    content = ""
                    author = ""
                    selectedCategory = categoryOptions[0]
                    uploadDate = ""
                    titleError = false
                    contentError = false
                    authorError = false
                    categoryError = false
                    uploadDateError = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(R.string.reset))
            }
        }
    }
}
@Composable
fun ErrorHint(isError: Boolean) {
    if (isError){
        Text(
            text = stringResource(R.string.input_invalid),
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AddBlogScreenPreview() {
    Assesment1Theme {
        AddBlogScreen(rememberNavController())
    }
}