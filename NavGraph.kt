package com.example.bookmydoctor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.room.Room
import com.example.bookmydoctor.data.AppDatabase
import com.example.bookmydoctor.repository.AppointmentRepository
import com.example.bookmydoctor.ui.screens.*
import com.example.bookmydoctor.viewmodel.AppointmentViewModel
import com.example.bookmydoctor.viewmodel.AppointmentViewModelFactory

@Composable
fun AppNavGraph(navController: NavHostController) {
    // 1. Get the current context
    val context = LocalContext.current
    val db = remember(context) {
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "appointment_db"
        ).build()
    }

    // 2. Create Repository
    val repository = AppointmentRepository(db.appointmentDao())

    // 3. Create ViewModel with Factory
    val factory = AppointmentViewModelFactory(repository)
    val viewModel: AppointmentViewModel = viewModel(factory = factory)

    // 4. Navigation Graph with Splash Screen as start
    NavHost(navController = navController, startDestination = "splash") {

        // 🌟 Splash Screen
        composable("splash") {
            SplashScreen(navController)
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("doctor_list") {
            DoctorListScreen(navController)
        }

        composable("booking_form") {
            AppointmentBookingScreen(navController, viewModel)
        }

        composable("confirmation") {
            ConfirmationScreen(navController, viewModel)
        }

        composable("history") {
            AppointmentHistoryScreen(navController, viewModel)
        }

        composable("clinic_info") {
            ClinicInfoScreen(navController)
        }
    }
}
