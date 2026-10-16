package com.example.ui.screens

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.automirrored.filled.HelpOutline
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.EmergencyContact
import com.example.data.TcaRepository
import com.example.ui.components.AvatarDialogueCard
import com.example.ui.components.AvatarType
import com.example.ui.components.EduButton
import com.example.ui.components.EduCard
import com.example.ui.components.EduChip
import com.example.ui.components.GeometricAvatar
import com.example.ui.theme.CoralOrange
import com.example.ui.theme.Cream
import com.example.ui.theme.DeepBlue
import com.example.ui.theme.LavenderBlue
import com.example.ui.theme.NightBlue
import com.example.ui.theme.PastelPink
import com.example.ui.theme.SoftBlueBg
import com.example.ui.theme.SoftPinkBg
import com.example.ui.theme.SoftYellowBg
import com.example.ui.theme.SubtleBorder
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.WarningRed

@Composable
fun OrientationScreen(
    onNavigateBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val countries = listOf("Todos", "Argentina", "España", "México", "Chile", "Colombia")
    var selectedCountry by remember { mutableStateOf("Todos") }

    val filteredContacts = remember(selectedCountry) {
        if (selectedCountry == "Todos") {
            TcaRepository.emergencyContacts
        } else {
            TcaRepository.emergencyContacts.filter { it.country == selectedCountry }
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Cream)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
            AvatarDialogueCard(
                avatarType = AvatarType.EMPATICO,
                title = "Buscar orientación y acompañamiento",
                message = "Pedir ayuda a tiempo cambia vidas. No estás solo/a: existen redes públicas de salud y profesionales especializados para acompañar a tu familia o a vos."
            )
        }

        // AVISO ÉTICO Y LEGAL OBLIGATORIO
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SoftYellowBg,
                borderColor = CoralOrange.copy(alpha = 0.5f),
                cornerRadius = 20.dp
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = CoralOrange,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Aviso importante de salud pública",
                            style = MaterialTheme.typography.titleSmall,
                            color = NightBlue,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Esta aplicación es una herramienta educativa y de prevención comunitaria. NO realiza diagnósticos, no evalúa estados de salud individual ni reemplaza la consulta presencial con médicos/as, psicólogos/as o equipos interdisciplinarios de salud.",
                            style = MaterialTheme.typography.bodySmall,
                            color = NightBlue,
                            lineHeight = 18.sp
                        )
                    }
                }
            }
        }

        // Cuándo buscar ayuda inmediata
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SoftPinkBg,
                borderColor = PastelPink,
                cornerRadius = 20.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Situaciones que requieren atención médica urgente",
                        style = MaterialTheme.typography.titleSmall,
                        color = WarningRed,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Si la persona presenta desmayos, mareos intensos constantes, vómitos repetidos, signos graves de deshidratación o ideación de autolesión, acudí inmediatamente a la guardia hospitalaria o centro de emergencias médicas de tu localidad.",
                        style = MaterialTheme.typography.bodySmall,
                        color = NightBlue,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Primeros pasos para la consulta profesional
        item {
            EduCard(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = SurfaceWhite,
                cornerRadius = 20.dp
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Pasos recomendados para solicitar ayuda",
                        style = MaterialTheme.typography.titleMedium,
                        color = DeepBlue
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    HelpStepRow("1", "Médico/a pediatra o clínico de cabecera", "Es el primer contacto para evaluar parámetros físicos (presión, analítica, ritmo cardíaco) de forma integral y derivar con criterio.")
                    HelpStepRow("2", "Salud mental (Psicología y Psiquiatría)", "Profesionales especializados en infancia y adolescencia con experiencia en TCA y terapia basada en la familia.")
                    HelpStepRow("3", "Nutrición no pesocentrista", "Profesionales de nutrición que no trabajen con dietas restrictivas ni obsesión por la balanza, sino con reconciliación alimentaria.")
                }
            }
        }

        // Selector de País para líneas de ayuda
        item {
            Column {
                Text(
                    text = "Líneas de ayuda gratuitas y servicios oficiales",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = NightBlue
                )
                Text(
                    text = "Seleccioná tu país para ver números verificados de asistencia:",
                    style = MaterialTheme.typography.bodySmall,
                    color = NightBlue.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    countries.take(3).forEach { country ->
                        EduChip(
                            text = country,
                            selected = selectedCountry == country,
                            onClick = { selectedCountry = country },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    countries.drop(3).forEach { country ->
                        EduChip(
                            text = country,
                            selected = selectedCountry == country,
                            onClick = { selectedCountry = country },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Directorio de contactos de emergencia oficiales
        items(filteredContacts) { contact ->
            EmergencyContactCard(
                contact = contact,
                onCallClick = {
                    try {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${contact.phoneNumber.filter { it.isDigit() || it == '+' }}")
                        }
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        // Safe fallback
                    }
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun HelpStepRow(number: String, title: String, desc: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
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
                color = NightBlue.copy(alpha = 0.8f),
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
private fun EmergencyContactCard(
    contact: EmergencyContact,
    onCallClick: () -> Unit
) {
    EduCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        cornerRadius = 20.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(LavenderBlue.copy(alpha = 0.25f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = contact.country,
                        style = MaterialTheme.typography.labelSmall,
                        color = DeepBlue,
                        fontWeight = FontWeight.Bold
                    )
                }
                Text(
                    text = contact.schedule,
                    style = MaterialTheme.typography.labelSmall,
                    color = NightBlue.copy(alpha = 0.6f),
                    fontSize = 11.sp
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = contact.serviceName,
                style = MaterialTheme.typography.titleSmall,
                color = NightBlue,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = contact.description,
                style = MaterialTheme.typography.bodySmall,
                color = NightBlue.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        tint = DeepBlue,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = contact.phoneNumber,
                        style = MaterialTheme.typography.titleMedium,
                        color = DeepBlue,
                        fontWeight = FontWeight.Bold
                    )
                }

                EduButton(
                    text = "Llamar",
                    onClick = onCallClick,
                    backgroundColor = DeepBlue,
                    modifier = Modifier.height(40.dp),
                    testTag = "btn_call_${contact.countryCode}"
                )
            }
        }
    }
}
