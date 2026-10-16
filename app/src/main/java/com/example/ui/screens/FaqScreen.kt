package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.TcaRepository
import com.example.ui.components.AvatarDialogueCard
import com.example.ui.components.AvatarType
import com.example.ui.components.EduCard
import com.example.ui.components.GeometricAvatar
import com.example.ui.theme.Cream
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.NightBlue
import com.example.ui.theme.SoftBlueBg
import com.example.ui.theme.SubtleBorder
import com.example.ui.theme.SurfaceWhite

@Composable
fun FaqScreen(
    onNavigateBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val expandedStates = remember { mutableStateMapOf<Int, Boolean>() }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Cream)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
            AvatarDialogueCard(
                avatarType = AvatarType.DIVERTIDO,
                title = "Preguntas Frecuentes",
                message = "Respuestas claras sobre la plataforma, la prevención de TCA y las pautas de cuidado mutuo."
            )
        }

        items(TcaRepository.faqList.indices.toList()) { index ->
            val pair = TcaRepository.faqList[index]
            val isExpanded = expandedStates[index] ?: false

            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = if (isExpanded) SoftBlueBg else SurfaceWhite,
                borderColor = if (isExpanded) DeepBlue else SubtleBorder,
                cornerRadius = 20.dp,
                onClick = { expandedStates[index] = !isExpanded },
                testTag = "faq_item_$index"
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        GeometricAvatar(
                            avatarType = if (index % 2 == 0) AvatarType.CURIOSO else AvatarType.EMPATICO,
                            size = 36.dp,
                            showShadow = false
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = pair.first,
                            style = MaterialTheme.typography.titleSmall,
                            color = NightBlue,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = null,
                            tint = DeepBlue
                        )
                    }

                    AnimatedVisibility(visible = isExpanded) {
                        Column(
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(SurfaceWhite)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = pair.second,
                                style = MaterialTheme.typography.bodyMedium,
                                color = NightBlue,
                                lineHeight = 20.sp
                            )
                        }
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
