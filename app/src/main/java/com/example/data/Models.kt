package com.example.data

import com.example.ui.components.AvatarType

enum class EducationalLevel(val label: String, val ageRange: String) {
    INICIAL("Nivel Inicial", "3 a 5 años"),
    PRIMARIA("Nivel Primario", "6 a 11 años"),
    SECUNDARIA("Nivel Secundario", "12 a 18 años"),
    TODOS("Todas las etapas", "Comunidad educativa")
}

enum class AudienceType(val label: String) {
    FAMILIAS("Familias y Cuidadores"),
    DOCENTES("Docentes y Equipos Escolares"),
    ESTUDIANTES("Estudiantes"),
    GENERAL("Comunidad General")
}

enum class ResourceTopic(val label: String) {
    CONCEPTO_TCA("Comprensión de los TCA"),
    PREVENCION_AULA("Prevención en la Escuela"),
    ACOMPANAMIENTO("Acompañamiento y Escucha"),
    AUTOESTIMA("Autoestima y Fortalezas"),
    REDES_SOCIALES("Redes Sociales y Filtros"),
    DIVERSIDAD_CORPORAL("Diversidad Corporal"),
    ALIMENTACION_FLEXIBLE("Alimentación y Emociones")
}

enum class ResourceFormat(val label: String) {
    GUIA_PRACTICA("Guía de Orientación"),
    ACTIVIDAD_AULA("Dinámica de Aula"),
    INFOGRAFIA("Ficha Informativa"),
    TALLER_FAMILIAR("Propuesta para Hogar"),
    CASO_REFLEXION("Caso de Análisis")
}

data class ResourceItem(
    val id: String,
    val title: String,
    val audience: AudienceType,
    val level: EducationalLevel,
    val topic: ResourceTopic,
    val format: ResourceFormat,
    val estimatedTime: String,
    val description: String,
    val instructions: List<String>,
    val materials: List<String>,
    val professionalSource: String,
    val avatar: AvatarType
)

data class MythItem(
    val id: String,
    val myth: String,
    val reality: String,
    val explanation: String,
    val avatar: AvatarType
)

data class WarningSign(
    val category: String, // "Emocionales y Psicológicas", "Conductuales", "Físicas", "En el Ámbito Escolar"
    val title: String,
    val description: String,
    val actionTip: String
)

data class ConversationGuide(
    val context: String,
    val recommendedPhrase: String,
    val avoidPhrase: String,
    val whyExplanation: String,
    val avatar: AvatarType
)

data class ClassroomActivity(
    val id: String,
    val title: String,
    val level: EducationalLevel,
    val durationMinutes: Int,
    val objectives: List<String>,
    val materials: List<String>,
    val stepByStep: List<String>,
    val reflectionQuestions: List<String>,
    val avatar: AvatarType
)

data class InteractiveQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val feedback: String,
    val avatar: AvatarType
)

data class EmergencyContact(
    val country: String,
    val countryCode: String,
    val serviceName: String,
    val phoneNumber: String,
    val description: String,
    val schedule: String,
    val website: String
)

data class DailyEmotionLog(
    val avatarType: AvatarType,
    val emotionLabel: String,
    val reflectionPrompt: String,
    val timestamp: Long = System.currentTimeMillis()
)
