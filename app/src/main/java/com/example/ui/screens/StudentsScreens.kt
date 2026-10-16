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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEmotions
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
import com.example.ui.components.AvatarDialogueCard
import com.example.ui.components.AvatarExpression
import com.example.ui.components.AvatarType
import com.example.ui.components.EduButton
import com.example.ui.components.EduCard
import com.example.ui.components.EduProgressBar
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

/**
 * Hub principal de Estudiantes con selector de las tres etapas
 */
@Composable
fun StudentsHubScreen(
    initialTab: Int = 0,
    onNavigateToLevel: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedLevelIndex by remember(initialTab) { mutableIntStateOf(initialTab) }
    val levelTabs = listOf("Inicial (3-5)", "Primaria (6-11)", "Secundaria (12-18)")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Cream)
    ) {
        EduTabBar(
            tabs = levelTabs,
            selectedIndex = selectedLevelIndex,
            onTabSelected = { selectedLevelIndex = it },
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        when (selectedLevelIndex) {
            0 -> EarlyChildhoodScreen()
            1 -> PrimarySchoolScreen()
            2 -> SecondarySchoolScreen(onNavigateHelp = { onNavigateToLevel("orientation") })
        }
    }
}

/**
 * 1. NIVEL INICIAL (3 a 5 años)
 * Altamente visual, lúdico, positivo, enfocado en emociones, diversidad y escucha corporal sin dietas ni peso.
 */
@Composable
fun EarlyChildhoodScreen() {
    var selectedEmotionAvatar by remember { mutableStateOf<AvatarType?>(null) }
    var emotionFeedback by remember { mutableStateOf<String?>(null) }
    var bodySignalSelected by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            AvatarDialogueCard(
                avatarType = AvatarType.DIVERTIDO,
                title = "¡Hola, explorador/a!",
                message = "¡Todos los personajes tenemos formas, colores y emociones diferentes! Y eso nos hace únicos y geniales."
            )
        }

        // Actividad 1: Rueda de Emociones
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SurfaceWhite,
                cornerRadius = 24.dp,
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.EmojiEmotions,
                            contentDescription = null,
                            tint = DeepBlue,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Juego: ¿Cómo se siente tu personaje?",
                            style = MaterialTheme.typography.titleMedium,
                            color = NightBlue
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Tocá un personaje para descubrir su emoción y cómo cuidarlo:",
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue.copy(alpha = 0.75f)
                    )
                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        EmotionCharacterButton(
                            avatar = AvatarType.MOTIVADOR,
                            label = "¡Alegre!",
                            onClick = {
                                selectedEmotionAvatar = AvatarType.MOTIVADOR
                                emotionFeedback = "¡Motivador está súper contento! Cuando estamos felices, nos encanta jugar y sonreír juntos."
                            }
                        )
                        EmotionCharacterButton(
                            avatar = AvatarType.CURIOSO,
                            label = "En calma",
                            onClick = {
                                selectedEmotionAvatar = AvatarType.CURIOSO
                                emotionFeedback = "Curioso respira hondo como el mar. La calma nos ayuda a descansar y pensar tranquilos."
                            }
                        )
                        EmotionCharacterButton(
                            avatar = AvatarType.EMPATICO,
                            label = "Con cariño",
                            onClick = {
                                selectedEmotionAvatar = AvatarType.EMPATICO
                                emotionFeedback = "Empático te manda un abrazo suavecito. ¡Pedir abrazos y ayuda siempre está bien!"
                            }
                        )
                    }

                    selectedEmotionAvatar?.let { avatar ->
                        Spacer(modifier = Modifier.height(12.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .background(avatar.primaryColor.copy(alpha = 0.15f))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = emotionFeedback ?: "",
                                style = MaterialTheme.typography.bodyMedium,
                                color = NightBlue,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }

        // Actividad 2: Escuchando a mi cuerpo
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SoftYellowBg,
                borderColor = WarmYellow,
                cornerRadius = 24.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Escucho los mensajes de mi cuerpo",
                        style = MaterialTheme.typography.titleMedium,
                        color = NightBlue
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Nuestro cuerpo es sabio y nos avisa lo que necesita. ¿Qué mensaje elegís?",
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        BodySignalChip(
                            emoji = "🍲",
                            title = "Tengo hambre",
                            desc = "Es hora de comer algo rico en familia para tener energía.",
                            isSelected = bodySignalSelected == "hambre",
                            modifier = Modifier.weight(1f),
                            onClick = { bodySignalSelected = "hambre" }
                        )
                        BodySignalChip(
                            emoji = "✨",
                            title = "Estoy lleno",
                            desc = "Mi pancita ya comió lo suficiente y está satisfecha.",
                            isSelected = bodySignalSelected == "lleno",
                            modifier = Modifier.weight(1f),
                            onClick = { bodySignalSelected = "lleno" }
                        )
                        BodySignalChip(
                            emoji = "😴",
                            title = "Tengo sueño",
                            desc = "Mi cuerpo pide descansar para recargar pilas.",
                            isSelected = bodySignalSelected == "sueno",
                            modifier = Modifier.weight(1f),
                            onClick = { bodySignalSelected = "sueno" }
                        )
                    }
                }
            }
        }

        // Cuento breve: La ronda de las formas
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SurfaceWhite,
                cornerRadius = 24.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Cuento: La ronda de todas las figuras",
                        style = MaterialTheme.typography.titleMedium,
                        color = DeepBlue
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "En el mundo de los personajes, el Círculo es redondito, el Triángulo tiene tres puntitas y la Estrella brilla en todas direcciones. Ninguno quiere ser igual al otro, porque si todos fuéramos idénticos, ¡el mundo sería muy aburrido!\n\nCada uno tiene una sonrisa hermosa y un corazón enorme. ¡Ser diferentes es nuestro mayor tesoro!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = NightBlue,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

/**
 * 2. NIVEL PRIMARIO (6 a 11 años)
 * Fomenta autoestima independiente del aspecto físico, respeto por la diversidad corporal,
 * juego interactivo de mitos publicitarios y diario emocional privado.
 */
@Composable
fun PrimarySchoolScreen() {
    var quizStep by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<Int?>(null) }
    var answeredCorrectly by remember { mutableStateOf<Boolean?>(null) }
    var diaryEntryText by remember { mutableStateOf("") }
    var diarySavedMessage by remember { mutableStateOf<String?>(null) }

    val quizQuestions = listOf(
        Triple(
            "En las publicidades y dibujos, a veces muestran un solo tipo de cuerpo como el 'ideal'. ¿Es real?",
            listOf("Sí, todas las personas deberían ser iguales", "No, los cuerpos humanos son naturalmente muy diversos y todos valiosos"),
            1
        ),
        Triple(
            "Si un/a compañero/a se siente triste o se burlan de él/ella, lo mejor es:",
            listOf("Reírse para no quedar afuera", "Acompañarlo/a, invitarlo a jugar y avisarle a un docente"),
            1
        ),
        Triple(
            "Tus 'superpoderes' y lo que te hace increíble tienen que ver con:",
            listOf("Tus talentos, tus risas, tus ideas y tu bondad", "Cuánto pesás o qué ropa usás"),
            0
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            AvatarDialogueCard(
                avatarType = AvatarType.POSITIVO,
                title = "¡Mis talentos no tienen talla!",
                message = "Tu valor como persona está en tus pasiones, tu curiosidad y tu empatía con los demás. ¡Nunca en una medida o apariencia física!"
            )
        }

        // Quiz Interactivo: Mitos y Publicidad
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SurfaceWhite,
                cornerRadius = 24.dp,
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Desafío de Pensamiento Crítico",
                            style = MaterialTheme.typography.titleMedium,
                            color = NightBlue
                        )
                        Text(
                            text = "Pregunta ${quizStep + 1} de ${quizQuestions.size}",
                            style = MaterialTheme.typography.labelSmall,
                            color = DeepBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = quizQuestions[quizStep].first,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = NightBlue
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    quizQuestions[quizStep].second.forEachIndexed { index, option ->
                        val isChosen = selectedAnswer == index
                        val isCorrectOption = index == quizQuestions[quizStep].third

                        val optionBg = when {
                            selectedAnswer == null -> SurfaceWhite
                            isChosen && isCorrectOption -> SoftGreenBg
                            isChosen && !isCorrectOption -> SoftPinkBg
                            else -> SurfaceWhite
                        }

                        val optionBorder = when {
                            selectedAnswer == null -> SubtleBorder
                            isChosen && isCorrectOption -> LimeGreen
                            isChosen && !isCorrectOption -> CoralOrange
                            else -> SubtleBorder
                        }

                        Surface(
                            onClick = {
                                if (selectedAnswer == null) {
                                    selectedAnswer = index
                                    answeredCorrectly = (index == quizQuestions[quizStep].third)
                                }
                            },
                            enabled = selectedAnswer == null,
                            shape = RoundedCornerShape(14.dp),
                            color = optionBg,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .border(1.dp, optionBorder, RoundedCornerShape(14.dp))
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${('A' + index)}. $option",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = NightBlue,
                                    modifier = Modifier.weight(1f)
                                )
                                if (selectedAnswer != null && isChosen) {
                                    Icon(
                                        imageVector = if (answeredCorrectly == true) Icons.Default.Check else Icons.Default.Close,
                                        contentDescription = null,
                                        tint = if (answeredCorrectly == true) DeepBlue else CoralOrange
                                    )
                                }
                            }
                        }
                    }

                    if (selectedAnswer != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = if (answeredCorrectly == true) "🎉 ¡Excelente respuesta! Tenés una mirada súper despierta y empática." else "💡 ¡Buen intento! Recordá que todas las personas somos valiosas tal como somos.",
                            style = MaterialTheme.typography.bodySmall,
                            color = DeepBlue,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                        EduButton(
                            text = if (quizStep < quizQuestions.size - 1) "Siguiente Pregunta" else "¡Volver a empezar!",
                            onClick = {
                                if (quizStep < quizQuestions.size - 1) {
                                    quizStep++
                                } else {
                                    quizStep = 0
                                }
                                selectedAnswer = null
                                answeredCorrectly = null
                            },
                            backgroundColor = DeepBlue,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // Diario Privado de Emociones (Sin comidas ni pesos)
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SoftPinkBg,
                borderColor = PastelPink.copy(alpha = 0.5f),
                cornerRadius = 24.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            tint = DeepBlue,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mi Diario Emocional Privado",
                            style = MaterialTheme.typography.titleMedium,
                            color = NightBlue
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Un rincón íntimo para escribir qué cosas lindas te pasaron hoy o qué te gustaría compartir con alguien de confianza. (No se guarda en ningún servidor).",
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue.copy(alpha = 0.8f)
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    OutlinedTextField(
                        value = diaryEntryText,
                        onValueChange = { diaryEntryText = it },
                        placeholder = { Text("Hoy me sentí bien cuando...", color = NightBlue.copy(alpha = 0.5f)) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .testTag("diary_input_primaria"),
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SurfaceWhite,
                            unfocusedContainerColor = SurfaceWhite,
                            focusedBorderColor = DeepBlue,
                            unfocusedBorderColor = SubtleBorder
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    EduButton(
                        text = "Guardar en mi diario",
                        onClick = {
                            if (diaryEntryText.isNotBlank()) {
                                diarySavedMessage = "⭐ ¡Reflexión guardada en tu dispositivo! Reconocer tus sentimientos es de valientes."
                                diaryEntryText = ""
                            }
                        },
                        backgroundColor = DeepBlue,
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "btn_save_diary_primaria"
                    )

                    diarySavedMessage?.let { msg ->
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = msg,
                            style = MaterialTheme.typography.bodySmall,
                            color = DeepBlue,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

/**
 * 3. NIVEL SECUNDARIO (12 a 18 años)
 * Enfoque maduro, reflexivo, respetuoso de su autonomía.
 * Redes sociales, filtros, cultura de la restricción, simulador de decisiones entre pares y cómo pedir ayuda.
 */
@Composable
fun SecondarySchoolScreen(
    onNavigateHelp: () -> Unit
) {
    var dilemmaStep by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableStateOf<Int?>(null) }

    val peerDilemmas = listOf(
        Pair(
            "Tu amiga empieza a saltear las comidas del recreo diciendo que 'no tiene hambre' y la notás muy angustiada frente al espejo.",
            listOf(
                "Le decís en frente de todos: '¡Tenés que comer algo ahora mismo!'",
                "En privado, le decís que la querés, que notás su tristeza y le ofrecés acompañarla a hablar con un docente o su familia.",
                "Hacés como si no pasara nada para no meterte en problemas."
            )
        ),
        Pair(
            "En el grupo de WhatsApp de amigos mandan memes burlándose de la apariencia de una compañera del curso.",
            listOf(
                "Ponés un límite claro: 'Che, no da opinar de los cuerpos de los demás, no tiene gracia'.",
                "Te reís para pertenecer al grupo.",
                "Reenviás el meme a otros cursos."
            )
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            AvatarDialogueCard(
                avatarType = AvatarType.CREATIVO,
                title = "Autonomía y pensamiento crítico",
                message = "Las redes sociales muestran fragmentos cuidadosamente editados y algoritmos diseñados para lucrar con nuestras inseguridades. La vida real es diversa, espontánea y real."
            )
        }

        // Módulo 1: Redes y Algoritmos
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SurfaceWhite,
                cornerRadius = 24.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Filtros vs. Realidad: La trampa de la comparación",
                        style = MaterialTheme.typography.titleMedium,
                        color = DeepBlue
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    InsightPoint("El sesgo de confirmación", "Cuanto más interactúes con contenidos de fitness extremo o dietas 'milagro', más te mostrará el algoritmo ese contenido, haciéndote creer que 'todo el mundo vive así'.")
                    InsightPoint("Los cuerpos son cambiantes", "La postura, la iluminación cenital, las lentes gran angular y los filtros de retoque en tiempo real alteran por completo las imágenes digitales.")
                    InsightPoint("Higiene digital activa", "Dejar de seguir cuentas que te hagan sentir culpable o insatisfecho/a con tu vida o tu cuerpo es un acto de autocuidado fundamental.")
                }
            }
        }

        // Módulo 2: Desarmando la Cultura de la Restricción
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SoftBlueBg,
                borderColor = LavenderBlue,
                cornerRadius = 24.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "¿Por qué las dietas restrictivas son dañinas?",
                        style = MaterialTheme.typography.titleMedium,
                        color = NightBlue
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "En la adolescencia, el cuerpo y el cerebro están en pleno desarrollo. Las restricciones voluntarias activan señales biológicas de alarma: aumenta la irritabilidad, se altera el sueño, se reduce la concentración y se generan obsesiones involuntarias con la comida.\n\nComer de forma variada y suficiente es el combustible que tu mente necesita para soñar, crear y vivir.",
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue,
                        lineHeight = 20.sp
                    )
                }
            }
        }

        // Módulo 3: Simulador de Decisiones "Acompañar a un amigo/a"
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SurfaceWhite,
                cornerRadius = 24.dp,
                elevation = 2.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Simulador: ¿Cómo actuar entre pares?",
                        style = MaterialTheme.typography.titleMedium,
                        color = NightBlue
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = peerDilemmas[dilemmaStep].first,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = NightBlue
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    peerDilemmas[dilemmaStep].second.forEachIndexed { i, opt ->
                        val isSelected = selectedOption == i
                        val isBestOption = (i == 1 && dilemmaStep == 0) || (i == 0 && dilemmaStep == 1)

                        val bg = when {
                            selectedOption == null -> SurfaceWhite
                            isSelected && isBestOption -> SoftGreenBg
                            isSelected && !isBestOption -> SoftPinkBg
                            else -> SurfaceWhite
                        }

                        Surface(
                            onClick = {
                                if (selectedOption == null) {
                                    selectedOption = i
                                }
                            },
                            enabled = selectedOption == null,
                            shape = RoundedCornerShape(12.dp),
                            color = bg,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .border(1.dp, if (isSelected) DeepBlue else SubtleBorder, RoundedCornerShape(12.dp))
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = opt,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = NightBlue,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }

                    if (selectedOption != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(SoftGreenBg)
                                .padding(10.dp)
                        ) {
                            Text(
                                text = "💡 Clave de empatía: Ser un buen amigo no significa cargar solo con el secreto; significa ser el puente hacia una persona adulta de confianza.",
                                style = MaterialTheme.typography.bodySmall,
                                color = DeepBlue,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        EduButton(
                            text = if (dilemmaStep < peerDilemmas.size - 1) "Siguiente caso" else "Reiniciar simulación",
                            onClick = {
                                if (dilemmaStep < peerDilemmas.size - 1) dilemmaStep++ else dilemmaStep = 0
                                selectedOption = null
                            },
                            backgroundColor = DeepBlue,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // Módulo 4: Pedir ayuda sin miedo
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SoftYellowBg,
                borderColor = WarmYellow,
                cornerRadius = 24.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Pedir ayuda es un acto de valentía",
                        style = MaterialTheme.typography.titleMedium,
                        color = NightBlue
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Si sentís que la comida, el espejo o los pensamientos sobre tu cuerpo te están quitando la paz, no tenés que pasar por esto solo/a. Hay profesionales preparados para escucharte con empatía y sin juzgarte.",
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue,
                        lineHeight = 18.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    EduButton(
                        text = "Ver canales de ayuda confidenciales",
                        onClick = onNavigateHelp,
                        backgroundColor = DeepBlue,
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "btn_secondary_help_channels"
                    )
                }
            }
        }

        item { Spacer(modifier = Modifier.height(20.dp)) }
    }
}

@Composable
private fun EmotionCharacterButton(
    avatar: AvatarType,
    label: String,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        color = Color.Transparent,
        modifier = Modifier.padding(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            GeometricAvatar(avatarType = avatar, size = 52.dp, showShadow = false)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color = NightBlue
            )
        }
    }
}

@Composable
private fun BodySignalChip(
    emoji: String,
    title: String,
    desc: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    Surface(
        onClick = onClick,
        modifier = modifier.border(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) DeepBlue else SubtleBorder,
            shape = shape
        ),
        shape = shape,
        color = if (isSelected) SurfaceWhite else SurfaceWhite.copy(alpha = 0.6f)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = emoji, fontSize = 26.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = NightBlue,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                color = NightBlue.copy(alpha = 0.75f),
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                lineHeight = 14.sp
            )
        }
    }
}

@Composable
private fun InsightPoint(title: String, desc: String) {
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
                color = NightBlue.copy(alpha = 0.8f),
                lineHeight = 18.sp
            )
        }
    }
}
