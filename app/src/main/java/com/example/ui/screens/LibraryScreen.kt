package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.AudienceType
import com.example.data.ResourceFormat
import com.example.data.ResourceItem
import com.example.data.TcaRepository
import com.example.ui.components.AvatarDialogueCard
import com.example.ui.components.AvatarType
import com.example.ui.components.EduButton
import com.example.ui.components.EduCard
import com.example.ui.components.EduChip
import com.example.ui.components.GeometricAvatar
import com.example.ui.theme.Cream
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.LavenderBlue
import com.example.ui.theme.NightBlue
import com.example.ui.theme.SoftBlueBg
import com.example.ui.theme.SubtleBorder
import com.example.ui.theme.SurfaceWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen(
    onNavigateBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedAudience by remember { mutableStateOf<AudienceType?>(null) }
    var selectedResourceForModal by remember { mutableStateOf<ResourceItem?>(null) }

    val filteredResources = remember(searchQuery, selectedAudience) {
        TcaRepository.libraryResources.filter { res ->
            val matchesQuery = searchQuery.isBlank() ||
                    res.title.contains(searchQuery, ignoreCase = true) ||
                    res.description.contains(searchQuery, ignoreCase = true) ||
                    res.topic.label.contains(searchQuery, ignoreCase = true)

            val matchesAudience = selectedAudience == null || res.audience == selectedAudience || res.audience == AudienceType.GENERAL
            matchesQuery && matchesAudience
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(4.dp))
                AvatarDialogueCard(
                    avatarType = AvatarType.CREATIVO,
                    title = "Biblioteca de Recursos Pedagógicos",
                    message = "Fichas, infografías, guías y dinámicas basadas en evidencia para familias, docentes y estudiantes."
                )
            }

            // Barra de Búsqueda
            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = { Text("Buscar por palabra clave o tema...", color = NightBlue.copy(alpha = 0.5f)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar",
                            tint = DeepBlue
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Limpiar búsqueda",
                                    tint = NightBlue
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("library_search_input"),
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = SurfaceWhite,
                        unfocusedContainerColor = SurfaceWhite,
                        focusedBorderColor = DeepBlue,
                        unfocusedBorderColor = SubtleBorder
                    ),
                    singleLine = true
                )
            }

            // Filtros de Audiencia
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    EduChip(
                        text = "Todos",
                        selected = selectedAudience == null,
                        onClick = { selectedAudience = null },
                        testTag = "filter_audience_all"
                    )
                    EduChip(
                        text = "Familias",
                        selected = selectedAudience == AudienceType.FAMILIAS,
                        onClick = { selectedAudience = AudienceType.FAMILIAS },
                        testTag = "filter_audience_families"
                    )
                    EduChip(
                        text = "Docentes",
                        selected = selectedAudience == AudienceType.DOCENTES,
                        onClick = { selectedAudience = AudienceType.DOCENTES },
                        testTag = "filter_audience_teachers"
                    )
                    EduChip(
                        text = "Estudiantes",
                        selected = selectedAudience == AudienceType.ESTUDIANTES,
                        onClick = { selectedAudience = AudienceType.ESTUDIANTES },
                        testTag = "filter_audience_students"
                    )
                }
            }

            // Contador de resultados
            item {
                Text(
                    text = "${filteredResources.size} recursos disponibles",
                    style = MaterialTheme.typography.labelMedium,
                    color = NightBlue.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Bold
                )
            }

            // Lista de recursos
            if (filteredResources.isEmpty()) {
                item {
                    EduCard(
                        modifier = Modifier.fillMaxWidth(),
                        backgroundColor = SurfaceWhite,
                        cornerRadius = 20.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            GeometricAvatar(avatarType = AvatarType.CURIOSO, size = 56.dp, showShadow = false)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "No se encontraron recursos para esa búsqueda",
                                style = MaterialTheme.typography.titleSmall,
                                color = NightBlue,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Probá usando términos más amplios o borrá el filtro.",
                                style = MaterialTheme.typography.bodySmall,
                                color = NightBlue.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            } else {
                items(filteredResources) { resource ->
                    ResourceListItem(
                        resource = resource,
                        onClick = { selectedResourceForModal = resource }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Modal BottomSheet para ver detalles completos del recurso
        selectedResourceForModal?.let { res ->
            ModalBottomSheet(
                onDismissRequest = { selectedResourceForModal = null },
                sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
                containerColor = SurfaceWhite
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GeometricAvatar(avatarType = res.avatar, size = 52.dp, showShadow = false)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .background(DeepBlue.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "${res.audience.label} • ${res.format.label}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = DeepBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = res.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = NightBlue
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = res.description,
                        style = MaterialTheme.typography.bodyMedium,
                        color = NightBlue,
                        lineHeight = 22.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Instrucciones de uso:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = DeepBlue
                    )
                    res.instructions.forEach { inst ->
                        Text(
                            text = "• $inst",
                            style = MaterialTheme.typography.bodySmall,
                            color = NightBlue,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }

                    if (res.materials.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Materiales necesarios:",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = DeepBlue
                        )
                        res.materials.forEach { mat ->
                            Text(
                                text = "📎 $mat",
                                style = MaterialTheme.typography.bodySmall,
                                color = NightBlue.copy(alpha = 0.8f)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(SoftBlueBg)
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "Fuente profesional: ${res.professionalSource}",
                            style = MaterialTheme.typography.bodySmall,
                            color = DeepBlue,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    EduButton(
                        text = "Cerrar Vista Previa",
                        onClick = { selectedResourceForModal = null },
                        backgroundColor = DeepBlue,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
private fun ResourceListItem(
    resource: ResourceItem,
    onClick: () -> Unit
) {
    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        cornerRadius = 20.dp,
        onClick = onClick,
        testTag = "resource_item_${resource.id}"
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GeometricAvatar(avatarType = resource.avatar, size = 44.dp, showShadow = false)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(LavenderBlue.copy(alpha = 0.25f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = resource.format.label,
                                style = MaterialTheme.typography.labelSmall,
                                color = DeepBlue,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = null,
                                tint = NightBlue.copy(alpha = 0.5f),
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = resource.estimatedTime,
                                style = MaterialTheme.typography.labelSmall,
                                color = NightBlue.copy(alpha = 0.6f),
                                fontSize = 11.sp
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = resource.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = NightBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = resource.description,
                style = MaterialTheme.typography.bodySmall,
                color = NightBlue.copy(alpha = 0.8f),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Destinado a: ${resource.audience.label}",
                    style = MaterialTheme.typography.labelSmall,
                    color = NightBlue.copy(alpha = 0.6f),
                    fontSize = 11.sp
                )
                Text(
                    text = "Leer ficha →",
                    style = MaterialTheme.typography.labelSmall,
                    color = DeepBlue,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
