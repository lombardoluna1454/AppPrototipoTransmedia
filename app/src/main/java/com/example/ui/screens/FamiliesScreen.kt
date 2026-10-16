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
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
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
import com.example.data.ConversationGuide
import com.example.data.MythItem
import com.example.data.TcaRepository
import com.example.data.WarningSign
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
import com.example.ui.theme.WarningRed

@Composable
fun FamiliesScreen(
    onNavigate: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Comprender", "Acompañar", "Señales", "Guías")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        // Selector de Pestañas
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
                    // Pestaña 1: Comprender los TCA
                    item {
                        AvatarDialogueCard(
                            avatarType = AvatarType.EMPATICO,
                            title = "Espacio para familias y cuidadores",
                            message = "Las familias no causan los trastornos de la conducta alimentaria; son el sostén y factor protector más valioso en su prevención y recuperación."
                        )
                    }

                    item {
                        SectionHeader(
                            title = "¿Qué son los TCA?",
                            subtitle = "Conceptos esenciales con rigor clínico y empatía"
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
                                    text = "Los Trastornos de la Conducta Alimentaria (TCA) son condiciones de salud complejas que afectan la relación de una persona con la comida, su cuerpo y sus emociones. No son caprichos, elecciones de moda ni falta de voluntad.",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = NightBlue,
                                    lineHeight = 22.sp
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = "Intervienen múltiples factores entrelazados:",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = DeepBlue
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                FactorRow("Biológicos y genéticos", "Predisposición neurológica y vulnerabilidad al estrés.")
                                FactorRow("Psicológicos", "Alto perfeccionismo, autoexigencia o dificultad para regular la angustia.")
                                FactorRow("Socioculturales", "Presión estética, cultura de las dietas y mandatos corporales irreales.")
                                FactorRow("Acontecimientos vitales", "Duelos, acoso escolar (bullying) o transiciones difíciles.")
                            }
                        }
                    }

                    item {
                        SectionHeader(
                            title = "Principales tipos de TCA",
                            subtitle = "Información pedagógica para comprender la diversidad del cuadro"
                        )
                    }

                    item {
                        TcaTypeCard(
                            title = "Anorexia Nerviosa",
                            desc = "Caracterizada por una restricción severa y persistente de la ingesta energética frente a un miedo intenso a subir de peso y una alteración de la propia percepción corporal.",
                            accentColor = DeepBlue
                        )
                    }

                    item {
                        TcaTypeCard(
                            title = "Bulimia Nerviosa",
                            desc = "Presencia de episodios repetidos de ingesta descontrolada acompañados de conductas compensatorias inapropiadas y un sufrimiento anímico profundo.",
                            accentColor = CoralOrange
                        )
                    }

                    item {
                        TcaTypeCard(
                            title = "Trastorno por Atracón",
                            desc = "Episodios de ingesta copiosa en poco tiempo con intensa sensación de pérdida de control, angustia, culpa y malestar, sin conductas compensatorias.",
                            accentColor = WarmYellow
                        )
                    }

                    item {
                        TcaTypeCard(
                            title = "ARFID (Evitación / Restricción)",
                            desc = "Restricción alimentaria por desinterés en comer, aversión sensorial (texturas, olores) o temor a consecuencias atragantamiento, sin distorsión de la imagen corporal.",
                            accentColor = LimeGreen
                        )
                    }

                    item {
                        SectionHeader(
                            title = "Mitos frecuentes desmentidos",
                            subtitle = "Tocá cada tarjeta para ver la realidad respaldada por la ciencia"
                        )
                    }

                    items(TcaRepository.mythsList) { myth ->
                        MythCard(myth = myth)
                    }
                }

                1 -> {
                    // Pestaña 2: Cómo Acompañar
                    item {
                        AvatarDialogueCard(
                            avatarType = AvatarType.MOTIVADOR,
                            title = "Cuidar las palabras en el hogar",
                            message = "El hogar puede ser un refugio de aceptación. Cuando evitamos hablar del peso corporal de cualquier persona, quitamos un gran peso de encima."
                        )
                    }

                    item {
                        SectionHeader(
                            title = "Pilares para el acompañamiento familiar",
                            subtitle = "Recomendaciones de especialistas en salud infanto-juvenil"
                        )
                    }

                    item {
                        GuidelineCard(
                            number = "1",
                            title = "Escucha sin juicios ni minimización",
                            desc = "Si tu hijo/a expresa tristeza o disconformidad, no respondas con '¡no digas tonterías!'. Validá su emoción diciendo: 'Te escucho, entiendo que te sientas abrumado/a y estoy con vos.'",
                            bgColor = SoftGreenBg
                        )
                    }

                    item {
                        GuidelineCard(
                            number = "2",
                            title = "Relación flexible con los alimentos",
                            desc = "Evitá etiquetar las comidas como 'buenas' o 'malas', o referirte a ciertos platos como 'premios' o 'pecados'. La comida nos brinda energía y placer compartido.",
                            bgColor = SoftYellowBg
                        )
                    }

                    item {
                        GuidelineCard(
                            number = "3",
                            title = "Cero comentarios sobre cuerpos y dietas",
                            desc = "No comentes sobre los cambios de peso propios, de los hijos ni de personas en la tele o la calle. Los comentarios 'halagadores' sobre adelgazar refuerzan la idea de que la delgadez define el valor.",
                            bgColor = SoftPinkBg
                        )
                    }

                    item {
                        GuidelineCard(
                            number = "4",
                            title = "Acompañar el consumo de redes sociales",
                            desc = "Conversen abiertamente sobre los filtros, las poses preparadas y los algoritmos que muestran cuerpos irreales. Propongan pausas de pantalla y hábitos analógicos en familia.",
                            bgColor = SoftBlueBg
                        )
                    }
                }

                2 -> {
                    // Pestaña 3: Señales que requieren atención
                    item {
                        EduCard(
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = SoftYellowBg,
                            borderColor = WarmYellow,
                            cornerRadius = 20.dp
                        ) {
                            Row(
                                modifier = Modifier.padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Info,
                                    contentDescription = null,
                                    tint = DeepBlue,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Aclaración importante: Ninguna señal aislada confirma un TCA. Se trata de pautas orientativas para observar con afecto y consultar oportunamente a profesionales.",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = NightBlue,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }

                    items(TcaRepository.warningSigns) { sign ->
                        WarningSignCard(sign = sign)
                    }
                }

                3 -> {
                    // Pestaña 4: Guías Prácticas y Simulador
                    item {
                        SectionHeader(
                            title = "Simulador: ¿Cómo conversar?",
                            subtitle = "Ejemplos prácticos de frases recomendadas y frases a evitar"
                        )
                    }

                    items(TcaRepository.conversationGuides) { guide ->
                        ConversationGuideCard(guide = guide)
                    }

                    item {
                        SectionHeader(
                            title = "Checklist para preparar una consulta médica",
                            subtitle = "Puntos útiles para conversar con pediatría o psicología"
                        )
                    }

                    item {
                        ConsultationChecklist()
                    }

                    item {
                        EduButton(
                            text = "Necesito Orientación Profesional",
                            onClick = { onNavigate("orientation") },
                            modifier = Modifier.fillMaxWidth(),
                            backgroundColor = DeepBlue,
                            icon = Icons.AutoMirrored.Filled.HelpOutline,
                            testTag = "btn_need_orientation_families"
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun SectionHeader(title: String, subtitle: String) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = NightBlue
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodySmall,
            color = NightBlue.copy(alpha = 0.7f)
        )
    }
}

@Composable
private fun FactorRow(title: String, desc: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(DeepBlue, CircleShape)
                .padding(top = 6.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = NightBlue
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                color = NightBlue.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun TcaTypeCard(title: String, desc: String, accentColor: Color) {
    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        borderColor = accentColor.copy(alpha = 0.4f),
        cornerRadius = 18.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(accentColor, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = NightBlue,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
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
private fun MythCard(myth: MythItem) {
    var expanded by remember { mutableStateOf(false) }

    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = if (expanded) SoftBlueBg else SurfaceWhite,
        borderColor = if (expanded) DeepBlue else SubtleBorder,
        cornerRadius = 20.dp,
        onClick = { expanded = !expanded },
        testTag = "myth_card_${myth.id}"
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GeometricAvatar(avatarType = myth.avatar, size = 44.dp, showShadow = false)
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "MITO FRECUENTE",
                        style = MaterialTheme.typography.labelSmall,
                        color = CoralOrange,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "\"${myth.myth}\"",
                        style = MaterialTheme.typography.titleSmall,
                        color = NightBlue
                    )
                }
                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Contraer" else "Expandir",
                    tint = DeepBlue
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(SurfaceWhite)
                        .padding(12.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = DeepBlue,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "REALIDAD MÉDICA",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = DeepBlue
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = myth.reality,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = NightBlue
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = myth.explanation,
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue.copy(alpha = 0.85f),
                        lineHeight = 18.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun GuidelineCard(number: String, title: String, desc: String, bgColor: Color) {
    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = bgColor,
        cornerRadius = 20.dp
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(DeepBlue, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = number,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
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
private fun WarningSignCard(sign: WarningSign) {
    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        cornerRadius = 20.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Box(
                modifier = Modifier
                    .background(LavenderBlue.copy(alpha = 0.25f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = sign.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = DeepBlue,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = sign.title,
                style = MaterialTheme.typography.titleSmall,
                color = NightBlue,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = sign.description,
                style = MaterialTheme.typography.bodySmall,
                color = NightBlue.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SoftGreenBg)
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = null,
                    tint = DeepBlue,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = sign.actionTip,
                    style = MaterialTheme.typography.bodySmall,
                    color = NightBlue,
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
private fun ConversationGuideCard(guide: ConversationGuide) {
    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        cornerRadius = 20.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                GeometricAvatar(avatarType = guide.avatar, size = 38.dp, showShadow = false)
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = guide.context,
                    style = MaterialTheme.typography.titleSmall,
                    color = NightBlue,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Frase Recomendada
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SoftGreenBg)
                    .border(1.dp, LimeGreen.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = DeepBlue,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Qué decir (desde el afecto):",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = DeepBlue
                    )
                    Text(
                        text = guide.recommendedPhrase,
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Frase a evitar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(SoftPinkBg)
                    .border(1.dp, PastelPink.copy(alpha = 0.6f), RoundedCornerShape(12.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = WarningRed,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = "Qué evitar (desestimar o juzgar):",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = WarningRed
                    )
                    Text(
                        text = guide.avoidPhrase,
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "💡 Fundamento: ${guide.whyExplanation}",
                style = MaterialTheme.typography.bodySmall,
                color = NightBlue.copy(alpha = 0.75f),
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun ConsultationChecklist() {
    val items = listOf(
        "Anotar cambios en el ánimo, horarios o aislamiento de las últimas semanas.",
        "Evitar acudir a la consulta con tono acusatorio o de 'castigo'.",
        "Elegir profesionales (pediatra, psicología, nutrición) con enfoque no pesocentrista.",
        "Preguntarle con anticipación qué dudas o temores tiene respecto a la visita médica."
    )
    val checkedStates = remember { mutableStateMapOf<Int, Boolean>() }

    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        cornerRadius = 20.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            items.forEachIndexed { index, itemText ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { checkedStates[index] = !(checkedStates[index] ?: false) }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedStates[index] ?: false,
                        onCheckedChange = { checkedStates[index] = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = DeepBlue,
                            uncheckedColor = SubtleBorder
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = itemText,
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue
                    )
                }
            }
        }
    }
}
