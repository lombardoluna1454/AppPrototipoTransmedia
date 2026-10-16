package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.ui.components.EduBottomNavigation
import com.example.ui.components.EduTopBar
import com.example.ui.screens.FaqScreen
import com.example.ui.screens.FamiliesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LibraryScreen
import com.example.ui.screens.OrientationScreen
import com.example.ui.screens.StudentsHubScreen
import com.example.ui.screens.TeachersScreen
import com.example.ui.theme.Cream
import com.example.ui.theme.TcaEducationalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TcaEducationalTheme {
                TcaApp()
            }
        }
    }
}

@Composable
fun TcaApp() {
    var currentRoute by remember { mutableStateOf("home") }
    val backStack = remember { mutableStateListOf("home") }

    val navigateTo: (String) -> Unit = { route ->
        if (currentRoute != route) {
            backStack.add(route)
            currentRoute = route
        }
    }

    val navigateBack: () -> Unit = {
        if (backStack.size > 1) {
            backStack.removeAt(backStack.lastIndex)
            currentRoute = backStack.last()
        }
    }

    BackHandler(enabled = backStack.size > 1) {
        navigateBack()
    }

    val topBarTitle = when (currentRoute) {
        "home" -> "Prevención y Cuidado"
        "families" -> "Familias y Cuidadores"
        "teachers" -> "Docentes y Equipos Escolares"
        "students_hub" -> "Espacio Estudiantes"
        "students_inicial" -> "Nivel Inicial (3 a 5)"
        "students_primaria" -> "Nivel Primario (6 a 11)"
        "students_secundaria" -> "Nivel Secundario (12 a 18)"
        "library" -> "Biblioteca de Recursos"
        "orientation" -> "Buscar Orientación"
        "faq" -> "Preguntas Frecuentes"
        else -> "Prevención TCA"
    }

    val topBarSubtitle = when (currentRoute) {
        "home" -> "Plataforma Educativa"
        "families" -> "Acompañar con empatía y sin juicios"
        "teachers" -> "Prevención en el aula y protocolos"
        "students_hub" -> "Bienestar, cuerpo y emociones"
        "students_inicial" -> "Emociones, cuidado y diversidad"
        "students_primaria" -> "Autoestima y respeto corporal"
        "students_secundaria" -> "Pensamiento crítico y autonomía"
        "library" -> "Guías, dinámicas e infografías"
        "orientation" -> "Líneas de ayuda y red profesional"
        "faq" -> "Respuestas a dudas habituales"
        else -> null
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Cream),
        containerColor = Cream,
        topBar = {
            EduTopBar(
                title = topBarTitle,
                subtitle = topBarSubtitle,
                canNavigateBack = backStack.size > 1,
                onNavigateBack = navigateBack,
                onHelpClick = { navigateTo("orientation") }
            )
        },
        bottomBar = {
            EduBottomNavigation(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    if (route == "home") {
                        backStack.clear()
                        backStack.add("home")
                        currentRoute = "home"
                    } else {
                        navigateTo(route)
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Cream)
        ) {
            when (currentRoute) {
                "home" -> HomeScreen(onNavigate = navigateTo)
                "families" -> FamiliesScreen(onNavigate = navigateTo)
                "teachers" -> TeachersScreen(onNavigate = navigateTo)
                "students_hub" -> StudentsHubScreen(
                    initialTab = 0,
                    onNavigateToLevel = navigateTo
                )
                "students_inicial" -> StudentsHubScreen(
                    initialTab = 0,
                    onNavigateToLevel = navigateTo
                )
                "students_primaria" -> StudentsHubScreen(
                    initialTab = 1,
                    onNavigateToLevel = navigateTo
                )
                "students_secundaria" -> StudentsHubScreen(
                    initialTab = 2,
                    onNavigateToLevel = navigateTo
                )
                "library" -> LibraryScreen(onNavigateBack = navigateBack)
                "orientation" -> OrientationScreen(onNavigateBack = navigateBack)
                "faq" -> FaqScreen(onNavigateBack = navigateBack)
                else -> HomeScreen(onNavigate = navigateTo)
            }
        }
    }
}
