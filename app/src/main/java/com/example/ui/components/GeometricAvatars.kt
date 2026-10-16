package com.example.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CoralOrange
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.LavenderBlue
import com.example.ui.theme.LimeGreen
import com.example.ui.theme.NightBlue
import com.example.ui.theme.PastelPink
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.WarmYellow
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

enum class AvatarType(
    val characterName: String,
    val roleTitle: String,
    val description: String,
    val primaryColor: Color,
    val highlightColor: Color,
    val shadowColor: Color
) {
    CURIOSO(
        characterName = "Curioso",
        roleTitle = "Exploración y Calma",
        description = "Siempre con ganas de aprender y acompañar con serenidad",
        primaryColor = DeepBlue,
        highlightColor = Color(0xFF4E58E8),
        shadowColor = Color(0xFF191F91)
    ),
    MOTIVADOR(
        characterName = "Motivador",
        roleTitle = "Ánimo y Vitalidad",
        description = "Te impulsa a seguir y valorar tus pequeños grandes logros",
        primaryColor = CoralOrange,
        highlightColor = Color(0xFFFF8B73),
        shadowColor = Color(0xFFD64427)
    ),
    CREATIVO(
        characterName = "Creativo",
        roleTitle = "Pensamiento Libre",
        description = "Convierte las ideas en acción y busca nuevas perspectivas",
        primaryColor = WarmYellow,
        highlightColor = Color(0xFFFFE57A),
        shadowColor = Color(0xFFDCAE1A)
    ),
    EMPATICO(
        characterName = "Empático",
        roleTitle = "Cuidado y Afecto",
        description = "Te acompaña en cada paso y escucha con el corazón abierto",
        primaryColor = PastelPink,
        highlightColor = Color(0xFFFFB3DC),
        shadowColor = Color(0xFFD9509C)
    ),
    POSITIVO(
        characterName = "Positivo",
        roleTitle = "Diversidad y Fortaleza",
        description = "Ve el lado bueno de cada desafío y celebra ser únicos",
        primaryColor = LimeGreen,
        highlightColor = Color(0xFFCDFF33),
        shadowColor = Color(0xFF8DBF00)
    ),
    DIVERTIDO(
        characterName = "Divertido",
        roleTitle = "Alegría y Espontaneidad",
        description = "Hacerlo fácil y respetuoso también es parte del camino",
        primaryColor = LavenderBlue,
        highlightColor = Color(0xFFADC0FF),
        shadowColor = Color(0xFF5D74D9)
    )
}

enum class AvatarExpression {
    HAPPY,
    CURIOUS,
    WINKING,
    CALM,
    TALKING
}

/**
 * Renderiza de manera nativa y responsive a los avatares geométricos en estilo 3D suave.
 */
@Composable
fun GeometricAvatar(
    avatarType: AvatarType,
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
    expression: AvatarExpression = AvatarExpression.HAPPY,
    animate: Boolean = false,
    showShadow: Boolean = true
) {
    Box(
        modifier = modifier
            .size(size)
            .semantics { contentDescription = "Personaje geométrico ${avatarType.characterName}" },
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(size)
        ) {
            val canvasW = this.size.width
            val canvasH = this.size.height

            if (showShadow) {
                // Sombra suave en la base
                drawOval(
                    color = Color(0x1A0B1440),
                    topLeft = Offset(canvasW * 0.2f, canvasH * 0.88f),
                    size = Size(canvasW * 0.6f, canvasH * 0.12f)
                )
            }

            when (avatarType) {
                AvatarType.CURIOSO -> drawCuriosoCloud(avatarType, expression, canvasW, canvasH)
                AvatarType.MOTIVADOR -> drawMotivadorCircle(avatarType, expression, canvasW, canvasH)
                AvatarType.CREATIVO -> drawCreativoTriangle(avatarType, expression, canvasW, canvasH)
                AvatarType.EMPATICO -> drawEmpaticoHeart(avatarType, expression, canvasW, canvasH)
                AvatarType.POSITIVO -> drawPositivoStar(avatarType, expression, canvasW, canvasH)
                AvatarType.DIVERTIDO -> drawDivertidoPill(avatarType, expression, canvasW, canvasH)
            }
        }
    }
}

// 1. Curioso (Nube redondeada azul)
private fun DrawScope.drawCuriosoCloud(
    avatarType: AvatarType,
    expression: AvatarExpression,
    w: Float,
    h: Float
) {
    // Patitas
    val footColor = avatarType.shadowColor
    drawRoundRect(
        color = footColor,
        topLeft = Offset(w * 0.32f, h * 0.78f),
        size = Size(w * 0.12f, h * 0.14f),
        cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
    )
    drawRoundRect(
        color = footColor,
        topLeft = Offset(w * 0.56f, h * 0.78f),
        size = Size(w * 0.12f, h * 0.14f),
        cornerRadius = CornerRadius(w * 0.06f, w * 0.06f)
    )

    // Cuerpo con volumen 3D usando Brush radial/lineal
    val bodyBrush = Brush.radialGradient(
        colors = listOf(avatarType.highlightColor, avatarType.primaryColor, avatarType.shadowColor),
        center = Offset(w * 0.4f, h * 0.35f),
        radius = w * 0.55f
    )

    // Forma lobulada de nube orgánica
    drawCircle(bodyBrush, radius = w * 0.28f, center = Offset(w * 0.5f, h * 0.44f))
    drawCircle(bodyBrush, radius = w * 0.24f, center = Offset(w * 0.28f, h * 0.56f))
    drawCircle(bodyBrush, radius = w * 0.24f, center = Offset(w * 0.72f, h * 0.56f))
    drawCircle(bodyBrush, radius = w * 0.22f, center = Offset(w * 0.5f, h * 0.65f))
    drawRoundRect(
        bodyBrush,
        topLeft = Offset(w * 0.26f, h * 0.46f),
        size = Size(w * 0.48f, h * 0.32f),
        cornerRadius = CornerRadius(w * 0.15f, w * 0.15f)
    )

    // Ojos y boca
    drawAvatarFace(
        expression = expression,
        leftEyeCenter = Offset(w * 0.40f, h * 0.50f),
        rightEyeCenter = Offset(w * 0.60f, h * 0.50f),
        mouthCenter = Offset(w * 0.50f, h * 0.63f),
        eyeRadius = w * 0.075f,
        mouthWidth = w * 0.16f
    )
}

// 2. Motivador (Círculo naranja con bracitos alegres)
private fun DrawScope.drawMotivadorCircle(
    avatarType: AvatarType,
    expression: AvatarExpression,
    w: Float,
    h: Float
) {
    // Bracitos levantados
    val limbColor = avatarType.shadowColor
    drawRoundRect(
        color = limbColor,
        topLeft = Offset(w * 0.08f, h * 0.44f),
        size = Size(w * 0.14f, h * 0.10f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )
    drawRoundRect(
        color = limbColor,
        topLeft = Offset(w * 0.78f, h * 0.36f),
        size = Size(w * 0.16f, h * 0.10f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )

    // Patitas
    drawRoundRect(
        color = limbColor,
        topLeft = Offset(w * 0.35f, h * 0.78f),
        size = Size(w * 0.11f, h * 0.12f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )
    drawRoundRect(
        color = limbColor,
        topLeft = Offset(w * 0.54f, h * 0.78f),
        size = Size(w * 0.11f, h * 0.12f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )

    // Cuerpo circular con gradiente 3D suave
    val bodyBrush = Brush.radialGradient(
        colors = listOf(avatarType.highlightColor, avatarType.primaryColor, avatarType.shadowColor),
        center = Offset(w * 0.42f, h * 0.42f),
        radius = w * 0.45f
    )
    drawCircle(bodyBrush, radius = w * 0.34f, center = Offset(w * 0.5f, h * 0.52f))

    // Carita expresiva
    drawAvatarFace(
        expression = expression,
        leftEyeCenter = Offset(w * 0.40f, h * 0.48f),
        rightEyeCenter = Offset(w * 0.60f, h * 0.48f),
        mouthCenter = Offset(w * 0.50f, h * 0.62f),
        eyeRadius = w * 0.075f,
        mouthWidth = w * 0.18f
    )
}

// 3. Creativo (Triángulo amarillo suave)
private fun DrawScope.drawCreativoTriangle(
    avatarType: AvatarType,
    expression: AvatarExpression,
    w: Float,
    h: Float
) {
    // Patitas
    val footColor = avatarType.shadowColor
    drawRoundRect(
        color = footColor,
        topLeft = Offset(w * 0.36f, h * 0.79f),
        size = Size(w * 0.10f, h * 0.12f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )
    drawRoundRect(
        color = footColor,
        topLeft = Offset(w * 0.54f, h * 0.79f),
        size = Size(w * 0.10f, h * 0.12f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )

    // Triángulo redondeado
    val triPath = Path().apply {
        moveTo(w * 0.5f, h * 0.16f)
        cubicTo(w * 0.54f, h * 0.16f, w * 0.82f, h * 0.70f, w * 0.80f, h * 0.75f)
        cubicTo(w * 0.78f, h * 0.80f, w * 0.22f, h * 0.80f, w * 0.20f, h * 0.75f)
        cubicTo(w * 0.18f, h * 0.70f, w * 0.46f, h * 0.16f, w * 0.5f, h * 0.16f)
        close()
    }

    val bodyBrush = Brush.radialGradient(
        colors = listOf(avatarType.highlightColor, avatarType.primaryColor, avatarType.shadowColor),
        center = Offset(w * 0.46f, h * 0.42f),
        radius = w * 0.48f
    )
    drawPath(triPath, bodyBrush)

    // Carita
    drawAvatarFace(
        expression = expression,
        leftEyeCenter = Offset(w * 0.42f, h * 0.54f),
        rightEyeCenter = Offset(w * 0.58f, h * 0.54f),
        mouthCenter = Offset(w * 0.50f, h * 0.66f),
        eyeRadius = w * 0.065f,
        mouthWidth = w * 0.15f
    )
}

// 4. Empático (Corazón rosa suave)
private fun DrawScope.drawEmpaticoHeart(
    avatarType: AvatarType,
    expression: AvatarExpression,
    w: Float,
    h: Float
) {
    // Bracitos de saludo / abrazo
    val limbColor = avatarType.shadowColor
    drawRoundRect(
        color = limbColor,
        topLeft = Offset(w * 0.08f, h * 0.48f),
        size = Size(w * 0.14f, h * 0.09f),
        cornerRadius = CornerRadius(w * 0.04f, w * 0.04f)
    )
    drawRoundRect(
        color = limbColor,
        topLeft = Offset(w * 0.78f, h * 0.48f),
        size = Size(w * 0.14f, h * 0.09f),
        cornerRadius = CornerRadius(w * 0.04f, w * 0.04f)
    )

    // Patitas
    drawRoundRect(
        color = limbColor,
        topLeft = Offset(w * 0.38f, h * 0.80f),
        size = Size(w * 0.10f, h * 0.11f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )
    drawRoundRect(
        color = limbColor,
        topLeft = Offset(w * 0.52f, h * 0.80f),
        size = Size(w * 0.10f, h * 0.11f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )

    // Corazón suave
    val heartPath = Path().apply {
        moveTo(w * 0.5f, h * 0.78f)
        cubicTo(w * 0.18f, h * 0.58f, w * 0.12f, h * 0.28f, w * 0.32f, h * 0.24f)
        cubicTo(w * 0.42f, h * 0.22f, w * 0.48f, h * 0.32f, w * 0.5f, h * 0.38f)
        cubicTo(w * 0.52f, h * 0.32f, w * 0.58f, h * 0.22f, w * 0.68f, h * 0.24f)
        cubicTo(w * 0.88f, h * 0.28f, w * 0.82f, h * 0.58f, w * 0.5f, h * 0.78f)
        close()
    }

    val bodyBrush = Brush.radialGradient(
        colors = listOf(avatarType.highlightColor, avatarType.primaryColor, avatarType.shadowColor),
        center = Offset(w * 0.42f, h * 0.38f),
        radius = w * 0.45f
    )
    drawPath(heartPath, bodyBrush)

    // Carita
    drawAvatarFace(
        expression = expression,
        leftEyeCenter = Offset(w * 0.41f, h * 0.48f),
        rightEyeCenter = Offset(w * 0.59f, h * 0.48f),
        mouthCenter = Offset(w * 0.50f, h * 0.60f),
        eyeRadius = w * 0.065f,
        mouthWidth = w * 0.14f
    )
}

// 5. Positivo (Estrella lima verde)
private fun DrawScope.drawPositivoStar(
    avatarType: AvatarType,
    expression: AvatarExpression,
    w: Float,
    h: Float
) {
    // Patitas
    val footColor = avatarType.shadowColor
    drawRoundRect(
        color = footColor,
        topLeft = Offset(w * 0.36f, h * 0.82f),
        size = Size(w * 0.11f, h * 0.10f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )
    drawRoundRect(
        color = footColor,
        topLeft = Offset(w * 0.53f, h * 0.82f),
        size = Size(w * 0.11f, h * 0.10f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )

    // Estrella redondeada de 5 puntas
    val starPath = Path()
    val numPoints = 5
    val outerR = w * 0.36f
    val innerR = w * 0.22f
    val cx = w * 0.5f
    val cy = h * 0.52f

    for (i in 0 until numPoints * 2) {
        val r = if (i % 2 == 0) outerR else innerR
        val angle = i * PI / numPoints - PI / 2
        val x = cx + (r * cos(angle)).toFloat()
        val y = cy + (r * sin(angle)).toFloat()
        if (i == 0) starPath.moveTo(x, y) else starPath.lineTo(x, y)
    }
    starPath.close()

    val bodyBrush = Brush.radialGradient(
        colors = listOf(avatarType.highlightColor, avatarType.primaryColor, avatarType.shadowColor),
        center = Offset(w * 0.44f, h * 0.44f),
        radius = w * 0.44f
    )
    drawPath(starPath, bodyBrush)

    // Carita
    drawAvatarFace(
        expression = expression,
        leftEyeCenter = Offset(w * 0.42f, h * 0.50f),
        rightEyeCenter = Offset(w * 0.58f, h * 0.50f),
        mouthCenter = Offset(w * 0.50f, h * 0.62f),
        eyeRadius = w * 0.065f,
        mouthWidth = w * 0.14f
    )
}

// 6. Divertido (Forma redondeada lavanda)
private fun DrawScope.drawDivertidoPill(
    avatarType: AvatarType,
    expression: AvatarExpression,
    w: Float,
    h: Float
) {
    // Patitas
    val footColor = avatarType.shadowColor
    drawRoundRect(
        color = footColor,
        topLeft = Offset(w * 0.35f, h * 0.78f),
        size = Size(w * 0.11f, h * 0.11f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )
    drawRoundRect(
        color = footColor,
        topLeft = Offset(w * 0.54f, h * 0.78f),
        size = Size(w * 0.11f, h * 0.11f),
        cornerRadius = CornerRadius(w * 0.05f, w * 0.05f)
    )

    // Cuerpo redondeado tipo hexágono / squircle
    val bodyBrush = Brush.radialGradient(
        colors = listOf(avatarType.highlightColor, avatarType.primaryColor, avatarType.shadowColor),
        center = Offset(w * 0.42f, h * 0.40f),
        radius = w * 0.42f
    )
    drawRoundRect(
        brush = bodyBrush,
        topLeft = Offset(w * 0.18f, h * 0.22f),
        size = Size(w * 0.64f, h * 0.58f),
        cornerRadius = CornerRadius(w * 0.24f, w * 0.24f)
    )

    // Carita (con guiño juguetón por defecto)
    drawAvatarFace(
        expression = AvatarExpression.WINKING,
        leftEyeCenter = Offset(w * 0.39f, h * 0.48f),
        rightEyeCenter = Offset(w * 0.61f, h * 0.48f),
        mouthCenter = Offset(w * 0.50f, h * 0.61f),
        eyeRadius = w * 0.07f,
        mouthWidth = w * 0.16f
    )
}

/**
 * Dibuja los ojos expresivos estilo 3D y la boca sonriente
 */
private fun DrawScope.drawAvatarFace(
    expression: AvatarExpression,
    leftEyeCenter: Offset,
    rightEyeCenter: Offset,
    mouthCenter: Offset,
    eyeRadius: Float,
    mouthWidth: Float
) {
    // Ojo Izquierdo
    if (expression == AvatarExpression.WINKING) {
        // Arco de guiño
        val winkPath = Path().apply {
            moveTo(leftEyeCenter.x - eyeRadius, leftEyeCenter.y)
            quadraticTo(leftEyeCenter.x, leftEyeCenter.y - eyeRadius * 0.8f, leftEyeCenter.x + eyeRadius, leftEyeCenter.y)
        }
        drawPath(
            path = winkPath,
            color = NightBlue,
            style = Stroke(width = eyeRadius * 0.45f, cap = StrokeCap.Round)
        )
    } else {
        // Ojo circular brillante
        drawCircle(color = NightBlue, radius = eyeRadius, center = leftEyeCenter)
        // Brillo blanco de reflejo
        drawCircle(
            color = Color.White,
            radius = eyeRadius * 0.36f,
            center = Offset(leftEyeCenter.x - eyeRadius * 0.28f, leftEyeCenter.y - eyeRadius * 0.28f)
        )
    }

    // Ojo Derecho
    drawCircle(color = NightBlue, radius = eyeRadius, center = rightEyeCenter)
    drawCircle(
        color = Color.White,
        radius = eyeRadius * 0.36f,
        center = Offset(rightEyeCenter.x - eyeRadius * 0.28f, rightEyeCenter.y - eyeRadius * 0.28f)
    )

    // Rubor en mejillas
    val blushColor = Color(0x33FF684A)
    drawCircle(
        color = blushColor,
        radius = eyeRadius * 0.6f,
        center = Offset(leftEyeCenter.x - eyeRadius * 1.1f, leftEyeCenter.y + eyeRadius * 0.9f)
    )
    drawCircle(
        color = blushColor,
        radius = eyeRadius * 0.6f,
        center = Offset(rightEyeCenter.x + eyeRadius * 1.1f, rightEyeCenter.y + eyeRadius * 0.9f)
    )

    // Boca sonriente
    val mouthPath = Path().apply {
        moveTo(mouthCenter.x - mouthWidth / 2f, mouthCenter.y)
        quadraticTo(
            mouthCenter.x,
            mouthCenter.y + mouthWidth * 0.65f,
            mouthCenter.x + mouthWidth / 2f,
            mouthCenter.y
        )
    }
    drawPath(
        path = mouthPath,
        color = NightBlue,
        style = Stroke(width = eyeRadius * 0.4f, cap = StrokeCap.Round)
    )
}

/**
 * Fila interactiva para seleccionar o presentar el equipo de personajes
 */
@Composable
fun AvatarTeamRow(
    selectedType: AvatarType? = null,
    onAvatarClick: (AvatarType) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AvatarType.values().forEach { avatar ->
            val isSelected = selectedType == avatar
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onAvatarClick(avatar) }
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .background(
                            color = if (isSelected) avatar.primaryColor.copy(alpha = 0.2f) else Color.Transparent,
                            shape = CircleShape
                        )
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = if (isSelected) avatar.primaryColor else Color.Transparent,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    GeometricAvatar(
                        avatarType = avatar,
                        size = 46.dp,
                        showShadow = false
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = avatar.characterName,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (isSelected) avatar.primaryColor else NightBlue,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

/**
 * Globo de diálogo pedagógico con avatar acompañante
 */
@Composable
fun AvatarDialogueCard(
    avatarType: AvatarType,
    title: String,
    message: String,
    modifier: Modifier = Modifier,
    actionButtonText: String? = null,
    onActionClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(24.dp)),
        shape = RoundedCornerShape(24.dp),
        color = SurfaceWhite
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GeometricAvatar(
                avatarType = avatarType,
                size = 64.dp,
                modifier = Modifier.padding(end = 14.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .background(avatarType.primaryColor.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = avatarType.characterName,
                            style = MaterialTheme.typography.labelSmall,
                            color = avatarType.primaryColor,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleSmall,
                        color = NightBlue
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = NightBlue.copy(alpha = 0.85f),
                    lineHeight = 18.sp
                )
                if (actionButtonText != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .background(avatarType.primaryColor)
                            .clickable { onActionClick() }
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = actionButtonText,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
