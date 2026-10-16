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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.data.ClassroomActivity
import com.example.data.EducationalLevel
import com.example.data.ResourceItem
import com.example.data.TcaRepository
import com.example.ui.components.AvatarDialogueCard
import com.example.ui.components.AvatarType
import com.example.ui.components.EduButton
import com.example.ui.components.EduCard
import com.example.ui.components.EduTabBar
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
import kotlinx.coroutines.launch

@Composable
fun TeachersScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Formación", "En el Aula", "Detección", "Fichas")
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(modifier = modifier.fillMaxSize().background(Cream)) {
        Column(modifier = Modifier.fillMaxSize()) {
            EduTabBar(
                tabs = tabs,
                selectedIndex = selectedTab,
                onTabSelected = { selectedTab = it },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                when (selectedTab) {
                    0 -> {
                        // Pestaña 1: Formación Docente
                        item {
                            AvatarDialogueCard(
                                avatarType = AvatarType.CURIOSO,
                                title = "Rol pedagógico y protector",
                                message = "Los docentes no diagnosticamos ni tratamos patologías. Nuestro rol fundamental es educar en diversidad, observar con afecto, contener y coordinar puentes seguros de ayuda."
                            )
                        }

                        item {
                            EduCard(
                                modifier = Modifier.fillMaxWidth(),
                                backgroundColor = SurfaceWhite,
                                cornerRadius = 20.dp
                            ) {
                                Column(modifier = Modifier.padding(18.dp)) {
                                    Text(
                                        text = "Factores protectores desde la escuela",
                                        style = MaterialTheme.typography.titleMedium,
                                        color = DeepBlue
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    TeacherPrincipleItem("Cultura escolar libre de burlas corporales", "Tolerancia cero a los 'apodos' relacionados con el peso, la estatura o la figura física.")
                                    TeacherPrincipleItem("Educación física no punitiva", "Promover el movimiento por disfrute, cooperación y salud, nunca como castigo o para 'quemar calorías'.")
                                    TeacherPrincipleItem("Educación emocional transversal", "Habilitar espacios cotidianos para hablar de frustraciones, temores y pertenencia grupal.")
                                    TeacherPrincipleItem("Desmitificar la nutrición moralista", "Evitar categorizar alimentos como 'chatarra', 'buenos' o 'malos' en proyectos escolares de ciencias.")
                                }
                            }
                        }

                        item {
                            EduCard(
                                modifier = Modifier.fillMaxWidth(),
                                backgroundColor = SoftPinkBg,
                                borderColor = PastelPink.copy(alpha = 0.5f),
                                cornerRadius = 20.dp
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Groups,
                                            contentDescription = null,
                                            tint = DeepBlue,
                                            modifier = Modifier.size(24.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Presión de pares y redes sociales",
                                            style = MaterialTheme.typography.titleSmall,
                                            color = NightBlue,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "La necesidad de aceptación social en la adolescencia es muy intensa. Cuando las burlas o comentarios sobre el cuerpo se normalizan en el aula o recreos, la vulnerabilidad a desarrollar un TCA se multiplica. Construir acuerdos de convivencia explícitos protege vidas.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = NightBlue.copy(alpha = 0.85f),
                                        lineHeight = 18.sp
                                    )
                                }
                            }
                        }
                    }

                    1 -> {
                        // Pestaña 2: Prevención en el Aula (Dinámicas)
                        item {
                            AvatarDialogueCard(
                                avatarType = AvatarType.CREATIVO,
                                title = "Propuestas listas para aplicar",
                                message = "Dinámicas pedagógicas con objetivos claros, tiempos sugeridos, pasos detallados y preguntas disparadoras para abrir el diálogo."
                            )
                        }

                        items(TcaRepository.classroomActivities) { activity ->
                            ActivityCard(activity = activity)
                        }
                    }

                    2 -> {
                        // Pestaña 3: Detección y Acompañamiento
                        item {
                            EduCard(
                                modifier = Modifier.fillMaxWidth(),
                                backgroundColor = SoftBlueBg,
                                borderColor = LavenderBlue,
                                cornerRadius = 20.dp
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = null,
                                            tint = DeepBlue,
                                            modifier = Modifier.size(22.dp)
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = "Protocolo de Privacidad y No Exposición",
                                            style = MaterialTheme.typography.titleSmall,
                                            color = DeepBlue,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Nunca confrontes a un estudiante en público ni comentes sus conductas alimentarias frente al grupo. La confidencialidad es indispensable para mantener el lazo de confianza.",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = NightBlue
                                    )
                                }
                            }
                        }

                        item {
                            ProtocolStepCard(
                                stepNumber = "Paso 1",
                                title = "Observación cuidadosa y registro de hechos",
                                desc = "Registrar cambios objetivos (aislamiento reiterado en el recreo, bajón anímico, comentarios descalificatorios hacia sí mismo) sin formular diagnósticos médicos apresurados.",
                                avatar = AvatarType.CURIOSO
                            )
                        }

                        item {
                            ProtocolStepCard(
                                stepNumber = "Paso 2",
                                title = "Encuentro privado y respetuoso",
                                desc = "Generar una charla breve a solas: 'Te he notado pensativo/a y me importa cómo estás'. Escuchar sin juzgar ni forzar a que admita problemas con la comida.",
                                avatar = AvatarType.EMPATICO
                            )
                        }

                        item {
                            ProtocolStepCard(
                                stepNumber = "Paso 3",
                                title = "Articulación con el equipo de orientación (EOE)",
                                desc = "Compartir la inquietud con directivos y profesionales del gabinete escolar para definir una estrategia coordinada y cuidada institucionalmente.",
                                avatar = AvatarType.POSITIVO
                            )
                        }

                        item {
                            ProtocolStepCard(
                                stepNumber = "Paso 4",
                                title = "Comunicación respetuosa con la familia",
                                desc = "Convocar a madres, padres o cuidadores en un clima de afecto y cooperación mutua, evitando señalamientos culpabilizadores y facilitando la consulta de salud.",
                                avatar = AvatarType.MOTIVADOR
                            )
                        }
                    }

                    3 -> {
                        // Pestaña 4: Recursos Descargables y Fichas
                        item {
                            AvatarDialogueCard(
                                avatarType = AvatarType.POSITIVO,
                                title = "Fichas institucionales para imprimir o proyectar",
                                message = "Material pedagógico listo para reuniones de equipo docente, jornadas con familias o carteleras del colegio."
                            )
                        }

                        items(TcaRepository.libraryResources.filter { it.audience == AudienceType.DOCENTES || it.audience == AudienceType.GENERAL }) { res ->
                            TeacherResourceFileCard(
                                resource = res,
                                onActionClick = {
                                    scope.launch {
                                        snackbarHostState.showSnackbar("Ficha visualizada: '${res.title}'. Lista para consulta.")
                                    }
                                }
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun TeacherPrincipleItem(title: String, desc: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = DeepBlue,
            modifier = Modifier
                .size(20.dp)
                .padding(top = 2.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                color = NightBlue,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                color = NightBlue.copy(alpha = 0.85f),
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun ActivityCard(activity: ClassroomActivity) {
    var expanded by remember { mutableStateOf(false) }

    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        borderColor = SubtleBorder,
        cornerRadius = 20.dp,
        onClick = { expanded = !expanded },
        testTag = "activity_card_${activity.id}"
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GeometricAvatar(avatarType = activity.avatar, size = 48.dp, showShadow = false)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Box(
                        modifier = Modifier
                            .background(activity.avatar.primaryColor.copy(alpha = 0.2f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "${activity.level.label} • ${activity.durationMinutes} min",
                            style = MaterialTheme.typography.labelSmall,
                            color = NightBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = activity.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = NightBlue
                    )
                }
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null,
                    tint = DeepBlue
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(SoftBlueBg)
                        .padding(14.dp)
                ) {
                    Text(
                        text = "Objetivos pedagógicos:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = DeepBlue
                    )
                    activity.objectives.forEach { obj ->
                        Text(
                            text = "• $obj",
                            style = MaterialTheme.typography.bodySmall,
                            color = NightBlue,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Paso a paso:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = DeepBlue
                    )
                    activity.stepByStep.forEachIndexed { i, step ->
                        Text(
                            text = "${i + 1}. $step",
                            style = MaterialTheme.typography.bodySmall,
                            color = NightBlue,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Preguntas para reflexionar:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = DeepBlue
                    )
                    activity.reflectionQuestions.forEach { q ->
                        Text(
                            text = "💬 $q",
                            style = MaterialTheme.typography.bodySmall,
                            color = NightBlue,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProtocolStepCard(
    stepNumber: String,
    title: String,
    desc: String,
    avatar: AvatarType
) {
    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        cornerRadius = 18.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            GeometricAvatar(avatarType = avatar, size = 44.dp, showShadow = false)
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .background(avatar.primaryColor.copy(alpha = 0.2f), RoundedCornerShape(6.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = stepNumber,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = NightBlue
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = NightBlue,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = desc,
                    style = MaterialTheme.typography.bodySmall,
                    color = NightBlue.copy(alpha = 0.85f),
                    lineHeight = 18.sp
                )
            }
        }
    }
}

@Composable
private fun TeacherResourceFileCard(
    resource: ResourceItem,
    onActionClick: () -> Unit
) {
    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        cornerRadius = 20.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GeometricAvatar(avatarType = resource.avatar, size = 44.dp, showShadow = false)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = resource.format.label,
                        style = MaterialTheme.typography.labelSmall,
                        color = DeepBlue,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = resource.title,
                        style = MaterialTheme.typography.titleSmall,
                        color = NightBlue
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = resource.description,
                style = MaterialTheme.typography.bodySmall,
                color = NightBlue.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ref: ${resource.professionalSource}",
                    style = MaterialTheme.typography.labelSmall,
                    color = NightBlue.copy(alpha = 0.6f),
                    fontSize = 11.sp,
                    modifier = Modifier.weight(1f)
                )
                EduButton(
                    text = "Ver Ficha",
                    onClick = onActionClick,
                    backgroundColor = DeepBlue,
                    icon = Icons.Default.Visibility,
                    modifier = Modifier.height(42.dp),
                    testTag = "btn_view_resource_${resource.id}"
                )
            }
        }
    }
}
