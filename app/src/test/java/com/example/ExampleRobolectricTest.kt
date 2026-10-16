package com.example

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import com.example.ui.screens.FamiliesScreen
import com.example.ui.screens.FaqScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LibraryScreen
import com.example.ui.screens.OrientationScreen
import com.example.ui.screens.StudentsHubScreen
import com.example.ui.screens.TeachersScreen
import com.example.ui.theme.TcaEducationalTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Prevención TCA", appName)
  }

  @Test
  fun `repository contains essential pedagogical content`() {
    assertTrue(com.example.data.TcaRepository.mythsList.isNotEmpty())
    assertTrue(com.example.data.TcaRepository.emergencyContacts.isNotEmpty())
    assertTrue(com.example.data.TcaRepository.classroomActivities.isNotEmpty())
    assertTrue(com.example.data.TcaRepository.libraryItems.isNotEmpty())
  }

  @Test
  fun `launch MainActivity without crash`() {
    val controller = Robolectric.buildActivity(MainActivity::class.java)
    val activity = controller.create().start().resume().visible().get()
    assertTrue(activity != null)
  }
}
