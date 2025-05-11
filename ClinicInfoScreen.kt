package com.example.bookmydoctor.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ClinicInfoScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🏥 Our Clinic", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Your trusted healthcare provider.\nVisit or call us anytime.")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            val locationUri = Uri.parse("geo:0,0?q=Your+Clinic+Name,+City")
            val mapIntent = Intent(Intent.ACTION_VIEW, locationUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            context.startActivity(mapIntent)
        }) {
            Text("Open in Google Maps")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val websiteUri = Uri.parse("https://yourclinicwebsite.com")
            val webIntent = Intent(Intent.ACTION_VIEW, websiteUri)
            context.startActivity(webIntent)
        }) {
            Text("Visit Website")
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back")
        }
    }
}
