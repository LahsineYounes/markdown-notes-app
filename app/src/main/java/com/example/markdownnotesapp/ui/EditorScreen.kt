package com.example.markdownnotesapp.ui

import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.markdownnotesapp.NoteViewModel
import com.example.markdownnotesapp.model.Note
import com.example.markdownnotesapp.ui.theme.Cream
import com.example.markdownnotesapp.ui.theme.DeepPurple
import com.example.markdownnotesapp.ui.theme.SoftPurple
import io.noties.markwon.Markwon
import androidx.core.graphics.toColorInt

// write/edit a note
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorScreen(
    existingNote: Note? = null,
    viewModel: NoteViewModel = viewModel(),
    onBack: () -> Unit
) {
    var title by remember { mutableStateOf(existingNote?.title ?: "") }
    var content by remember { mutableStateOf(existingNote?.content ?: "") }
    var tag by remember { mutableStateOf(existingNote?.tag ?: "General") }
    var showPreview by remember { mutableStateOf(false) }

    val tags = listOf("General", "Work", "Personal", "Ideas")

    Scaffold(
        containerColor = Cream,
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back", tint = DeepPurple)
                }
                Spacer(Modifier.weight(1f))
                // Preview toggle
                TextButton(onClick = { showPreview = !showPreview }) {
                    Text(
                        if (showPreview) "Edit" else "Preview",
                        color = DeepPurple,
                        fontWeight = FontWeight.Medium
                    )
                }
                // Save button
                Button(
                    onClick = {
                        if (title.isNotBlank() || content.isNotBlank()) {
                            viewModel.addOrUpdate(
                                (existingNote ?: Note(title = "", content = ""))
                                    .copy(title = title, content = content, tag = tag)
                            )
                            onBack()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DeepPurple),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Save")
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            // Title field
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A1A2E)
                ),
                decorationBox = { inner ->
                    if (title.isEmpty()) Text(
                        "Note title...",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFCCCCCC)
                    )
                    inner()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // Tag row
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(tags) { t ->
                    FilterChip(
                        selected = tag == t,
                        onClick = { tag = t },
                        label = { Text(t, fontSize = 12.sp) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = DeepPurple,
                            selectedLabelColor = Color.White,
                            containerColor = SoftPurple
                        ),
                        shape = RoundedCornerShape(20.dp),
                        border = FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = tag == t,
                            borderColor = Color.Transparent,
                            selectedBorderColor = Color.Transparent
                        )
                    )
                }
            }

            Spacer(Modifier.height(8.dp))
            HorizontalDivider(color = Color(0xFFEEEEEE))
            Spacer(Modifier.height(8.dp))

            if (showPreview) {
                // Simple markdown preview using AndroidView + Markwon
                MarkdownPreview(content)
            } else {
                // Editor
                BasicTextField(
                    value = content,
                    onValueChange = { content = it },
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFF2D2D2D),
                        lineHeight = 26.sp,
                        fontFamily = FontFamily.Monospace
                    ),
                    decorationBox = { inner ->
                        if (content.isEmpty()) Text(
                            "Start writing... (supports **bold**, *italic*, # headings)",
                            color = Color(0xFFCCCCCC),
                            fontSize = 15.sp
                        )
                        inner()
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}

// Simple markdown preview using Markwon library
@Composable
fun MarkdownPreview(markdownText: String) {
    val context = LocalContext.current
    val markwon = remember { Markwon.create(context) }

    AndroidView(
        factory = { ctx ->
            TextView(ctx).apply {
                setTextColor("#2D2D2D".toColorInt())
                textSize = 16f
                setPadding(0, 0, 0, 0)
            }
        },
        update = { textView ->
            markwon.setMarkdown(textView, markdownText)
        },
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    )
}