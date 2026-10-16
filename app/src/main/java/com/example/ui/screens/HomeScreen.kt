package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.AvatarTeamRow
import com.example.ui.components.AvatarType
import com.example.ui.components.EduButton
import com.example.ui.components.EduCard
import com.example.ui.components.GeometricAvatar
import com.example.ui.theme.CoralOrange
import com.example.ui.theme.Cream
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.LavenderBlue
import com.example.ui.theme.LimeGreen
import com.example.ui.theme.NightBlue
import com.example.ui.theme.PastelPink
import com.example.ui.theme.SoftBlueBg
import com.example.ui.theme.SoftGreenBg
import com.example.ui.theme.SoftOrangeBg
import com.example.ui.theme.SoftPinkBg
import com.example.ui.theme.SoftYellowBg
import com.example.ui.theme.SubtleBorder
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.WarmYellow

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var heroStageAvatar by remember { mutableStateOf(AvatarType.EMPATICO) }
    var selectedAvatarForMood by remember { mutableStateOf<AvatarType?>(AvatarType.EMPATICO) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Cream)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            // Encabezado institucional cálido
            Column {
                Text(
                    text = "Prevención y Cuidado",
                    style = MaterialTheme.typography.labelMedium,
                    color = DeepBlue,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Aprender también es un gran plan.",
                    style = MaterialTheme.typography.displayMedium,
                    color = NightBlue
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Herramientas educativas para promover una relación saludable con la alimentación, el cuerpo y las emociones.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = NightBlue.copy(alpha = 0.8f)
                )
            }
        }

        // Escenario interactivo de personajes en lugar de imagen estática
        item {
            InteractiveHeroStage(
                selectedAvatar = heroStageAvatar,
                onAvatarSelected = { heroStageAvatar = it },
                onNavigate = onNavigate
            )
        }

        // Botón de acceso prioritario: "¿Necesitás orientación o ayuda?"
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SoftBlueBg,
                borderColor = LavenderBlue,
                cornerRadius = 22.dp,
                onClick = { onNavigate("orientation") },
                testTag = "home_orientation_card"
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    GeometricAvatar(
                        avatarType = AvatarType.EMPATICO,
                        size = 56.dp,
                        modifier = Modifier.padding(end = 12.dp)
                    )
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "¿Necesitás orientación o ayuda?",
                            style = MaterialTheme.typography.titleSmall,
                            color = DeepBlue,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Líneas oficiales gratuitas y primeros pasos profesionales.",
                            style = MaterialTheme.typography.bodySmall,
                            color = NightBlue
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(DeepBlue, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Ir a orientación",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }

        // Título de Accesos Principales
        item {
            Text(
                text = "Acceso por comunidad",
                style = MaterialTheme.typography.titleLarge,
                color = NightBlue,
                fontWeight = FontWeight.Bold
            )
        }

        // Tarjeta 1: Familias y Cuidadores
        item {
            HubCard(
                title = "Familias y Cuidadores",
                subtitle = "Comprender los TCA, cómo conversar en casa, señales de alerta y guías prácticas.",
                badgeText = "Acompañar sin juzgar",
                avatarType = AvatarType.EMPATICO,
                backgroundColor = SoftPinkBg,
                accentColor = PastelPink,
                onClick = { onNavigate("families") },
                testTag = "hub_families_button"
            )
        }

        // Tarjeta 2: Docentes y Equipos Educativos
        item {
            HubCard(
                title = "Docentes y Equipos Escolares",
                subtitle = "Formación, dinámicas de aula, detección temprana, prevención del bullying y derivación.",
                badgeText = "Rol pedagógico protector",
                avatarType = AvatarType.CURIOSO,
                backgroundColor = SoftBlueBg,
                accentColor = DeepBlue,
                onClick = { onNavigate("teachers") },
                testTag = "hub_teachers_button"
            )
        }

        // Tarjeta 3: Estudiantes (con las tres etapas)
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SurfaceWhite,
                cornerRadius = 24.dp,
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .clickable { onNavigate("students_hub") }
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GeometricAvatar(
                            avatarType = AvatarType.POSITIVO,
                            size = 54.dp
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Box(
                                modifier = Modifier
                                    .background(LimeGreen.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = "Recursos para Estudiantes",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = NightBlue,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Aprendizaje según cada etapa",
                                style = MaterialTheme.typography.titleMedium,
                                color = NightBlue
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // 3 botones rápidos para Nivel Inicial, Primario y Secundario
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        StudentLevelButton(
                            title = "Inicial",
                            age = "3 a 5 años",
                            avatar = AvatarType.DIVERTIDO,
                            bgColor = SoftYellowBg,
                            borderColor = WarmYellow,
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate("students_inicial") },
                            testTag = "btn_level_inicial"
                        )
                        StudentLevelButton(
                            title = "Primaria",
                            age = "6 a 11 años",
                            avatar = AvatarType.MOTIVADOR,
                            bgColor = SoftOrangeBg,
                            borderColor = CoralOrange,
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate("students_primaria") },
                            testTag = "btn_level_primaria"
                        )
                        StudentLevelButton(
                            title = "Secundaria",
                            age = "12 a 18 años",
                            avatar = AvatarType.CREATIVO,
                            bgColor = SoftBlueBg,
                            borderColor = LavenderBlue,
                            modifier = Modifier.weight(1f),
                            onClick = { onNavigate("students_secundaria") },
                            testTag = "btn_level_secundaria"
                        )
                    }
                }
            }
        }

        // Chequeo emocional interactivo con los avatares
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SurfaceWhite,
                cornerRadius = 24.dp,
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "¿Cómo te sentís hoy?",
                        style = MaterialTheme.typography.titleMedium,
                        color = NightBlue
                    )
                    Text(
                        text = "Elegí el personaje que mejor te acompaña en este momento:",
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    AvatarTeamRow(
                        selectedType = selectedAvatarForMood,
                        onAvatarClick = { selectedAvatarForMood = it }
                    )

                    selectedAvatarForMood?.let { avatar ->
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(avatar.primaryColor.copy(alpha = 0.12f))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "💬 ${avatar.characterName}: \"${avatar.description}. Recordá que todas las emociones son válidas y merecen ser escuchadas.\"",
                                style = MaterialTheme.typography.bodySmall,
                                color = NightBlue,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        // Accesos secundarios en cuadrícula (Biblioteca, Preguntas Frecuentes)
        item {
            Text(
                text = "Herramientas destacadas",
                style = MaterialTheme.typography.titleLarge,
                color = NightBlue,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                SecondaryToolCard(
                    title = "Biblioteca",
                    subtitle = "Fichas y guías con filtros",
                    icon = Icons.Default.MenuBook,
                    bgColor = SoftBlueBg,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate("library") },
                    testTag = "tool_library_card"
                )
                SecondaryToolCard(
                    title = "Preguntas Frecuentes",
                    subtitle = "Dudas institucionales y TCA",
                    icon = Icons.AutoMirrored.Filled.HelpOutline,
                    bgColor = SoftGreenBg,
                    modifier = Modifier.weight(1f),
                    onClick = { onNavigate("faq") },
                    testTag = "tool_faq_card"
                )
            }
        }

        // Pie de página institucional y confidencialidad
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Transparent,
                borderColor = Color.Transparent,
                elevation = 0.dp
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = DeepBlue,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Privacidad y Alcance Educativo",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = DeepBlue
                        )
                    }
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Esta plataforma es una herramienta pedagógica comunitaria. No recopila datos personales de salud ni reemplaza la evaluación o tratamiento médico profesional.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = TextAlign.Center,
                        color = NightBlue.copy(alpha = 0.65f),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

/**
 * Escenario interactivo con los personajes geométricos tocables
 */
@Composable
private fun InteractiveHeroStage(
    selectedAvatar: AvatarType,
    onAvatarSelected: (AvatarType) -> Unit,
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    EduCard(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        cornerRadius = 28.dp,
        elevation = 3.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Cabecera interactiva
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.TouchApp,
                        contentDescription = null,
                        tint = selectedAvatar.primaryColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "¡Tocá a los personajes para interactuar!",
                        style = MaterialTheme.typography.labelSmall,
                        color = NightBlue.copy(alpha = 0.8f),
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .background(selectedAvatar.primaryColor.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = selectedAvatar.characterName,
                        style = MaterialTheme.typography.labelSmall,
                        color = selectedAvatar.primaryColor,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Escenario con los 5 personajes en fila, grandes y táctiles
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(selectedAvatar.primaryColor.copy(alpha = 0.08f))
                    .padding(vertical = 12.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom
            ) {
                val characters = listOf(
                    AvatarType.EMPATICO,
                    AvatarType.CREATIVO,
                    AvatarType.CURIOSO,
                    AvatarType.MOTIVADOR,
                    AvatarType.POSITIVO
                )

                characters.forEach { avatar ->
                    val isSelected = avatar == selectedAvatar
                    Surface(
                        onClick = { onAvatarSelected(avatar) },
                        shape = RoundedCornerShape(16.dp),
                        color = if (isSelected) SurfaceWhite else Color.Transparent,
                        shadowElevation = if (isSelected) 3.dp else 0.dp,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 6.dp)
                        ) {
                            GeometricAvatar(
                                avatarType = avatar,
                                size = if (isSelected) 60.dp else 48.dp,
                                showShadow = false
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = avatar.characterName,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) avatar.primaryColor else NightBlue.copy(alpha = 0.7f),
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Globo de diálogo interactivo del personaje seleccionado con botón de acción
            val (message, route, buttonLabel) = when (selectedAvatar) {
                AvatarType.EMPATICO -> Triple(
                    "\"¡Hola! Te acompaño en la sección de Familias para aprender a conversar con afecto, sin juicios sobre el cuerpo ni dietas en el hogar.\"",
                    "families",
                    "Ir a Familias y Cuidadores"
                )
                AvatarType.CURIOSO -> Triple(
                    "\"¡Hola! Te acompaño en el espacio Docente para explorar protocolos de detección temprana, privacidad y dinámicas escolares.\"",
                    "teachers",
                    "Ir a Espacio Docente"
                )
                AvatarType.POSITIVO -> Triple(
                    "\"¡Hola! Todos los cuerpos y formas son únicos. ¡Vamos a descubrir emociones y cuentos en Nivel Inicial!\"",
                    "students_inicial",
                    "Jugar en Nivel Inicial (3 a 5)"
                )
                AvatarType.MOTIVADOR -> Triple(
                    "\"¡Hola! Tus talentos no tienen talla. ¡Vamos a resolver el desafío de mitos publicitarios y el diario en Primaria!\"",
                    "students_primaria",
                    "Explorar Primaria (6 a 11)"
                )
                AvatarType.CREATIVO -> Triple(
                    "\"¡Hola! Desarmemos los filtros y la trampa de los algoritmos de redes sociales en el espacio de Secundaria.\"",
                    "students_secundaria",
                    "Explorar Secundaria (12 a 18)"
                )
                AvatarType.DIVERTIDO -> Triple(
                    "\"¡Hola! En la Biblioteca encontrás todas las fichas, infografías y dinámicas basadas en evidencia.\"",
                    "library",
                    "Abrir Biblioteca de Recursos"
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .background(SoftBlueBg)
                    .border(1.dp, LavenderBlue.copy(alpha = 0.5f), RoundedCornerShape(18.dp))
                    .padding(14.dp)
            ) {
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = NightBlue,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))
                EduButton(
                    text = buttonLabel,
                    onClick = { onNavigate(route) },
                    backgroundColor = selectedAvatar.primaryColor,
                    modifier = Modifier.fillMaxWidth(),
                    testTag = "btn_stage_action_${selectedAvatar.name.lowercase()}"
                )
            }
        }
    }
}

@Composable
private fun HubCard(
    title: String,
    subtitle: String,
    badgeText: String,
    avatarType: AvatarType,
    backgroundColor: Color,
    accentColor: Color,
    onClick: () -> Unit,
    testTag: String
) {
    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = backgroundColor,
        borderColor = accentColor.copy(alpha = 0.35f),
        cornerRadius = 24.dp,
        elevation = 2.dp,
        onClick = onClick,
        testTag = testTag
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GeometricAvatar(
                avatarType = avatarType,
                size = 64.dp,
                modifier = Modifier.padding(end = 14.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .background(accentColor.copy(alpha = 0.25f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = badgeText,
                        style = MaterialTheme.typography.labelSmall,
                        color = NightBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = NightBlue
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = NightBlue.copy(alpha = 0.8f),
                    lineHeight = 18.sp
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(SurfaceWhite, CircleShape)
                    .border(1.dp, accentColor.copy(alpha = 0.5f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Abrir sección",
                    tint = NightBlue,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
private fun StudentLevelButton(
    title: String,
    age: String,
    avatar: AvatarType,
    bgColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    testTag: String
) {
    Surface(
        onClick = onClick,
        modifier = modifier
            .testTag(testTag)
            .border(1.dp, borderColor, RoundedCornerShape(18.dp)),
        shape = RoundedCornerShape(18.dp),
        color = bgColor
    ) {
        Column(
            modifier = Modifier.padding(vertical = 12.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GeometricAvatar(avatarType = avatar, size = 42.dp, showShadow = false)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = NightBlue
            )
            Text(
                text = age,
                style = MaterialTheme.typography.labelSmall,
                color = NightBlue.copy(alpha = 0.65f),
                fontSize = 10.sp
            )
        }
    }
}

@Composable
private fun SecondaryToolCard(
    title: String,
    subtitle: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    bgColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    testTag: String
) {
    EduCard(
        modifier = modifier,
        backgroundColor = bgColor,
        cornerRadius = 20.dp,
        onClick = onClick,
        testTag = testTag
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(SurfaceWhite, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = DeepBlue,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = NightBlue
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = NightBlue.copy(alpha = 0.7f),
                fontSize = 11.sp
            )
        }
    }
}
