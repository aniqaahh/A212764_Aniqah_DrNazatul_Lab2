package com.example.a212764_aniqah_drnazatul_lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a212764_aniqah_drnazatul_lab2.ui.theme.A212764_Aniqah_DrNazatul_Lab2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A212764_Aniqah_DrNazatul_Lab2Theme {
                HomeScreen()

            }
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    var searchText by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.bgimage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Dark overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Text("TrailFinder", fontSize = 28.sp, color = Color.White)

            Spacer(modifier = Modifier.height(12.dp))

            SearchBar(
                text = searchText,
                onTextChange = { searchText = it }
            )

            Spacer(modifier = Modifier.height(12.dp))

            CategoryChips()

            Spacer(modifier = Modifier.height(16.dp))

            if (searchText.isEmpty() || "datuk".contains(searchText.lowercase())) {

                Text("Popular Trails", color = Color.White, fontSize = 20.sp)

                Column {
                    TrailGridCard(imageRes = R.drawable.datuk)

                    Spacer(modifier = Modifier.height(6.dp))

                    TrailHeader(
                        title = "Gunung Datuk",
                        location = "Rembau, Negeri Sembilan",
                        length = "4.7km",
                        elevation = "586m",
                        time = "3–3.5hr",
                        rating = "4.6",
                        level = "Moderate"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (searchText.isEmpty() || "rompin".contains(searchText.lowercase())) {

                Text("Beautiful Scenery", color = Color.White, fontSize = 20.sp)

                Column {
                    TrailGridCard(imageRes = R.drawable.rompin)

                    Spacer(modifier = Modifier.height(6.dp))

                    TrailHeader(
                        title = "Taman Negeri Rompin",
                        location = "Kuala Rompin, Pahang",
                        length = "31.5km",
                        elevation = "1,048m",
                        time = "9.5-10.5hr",
                        rating = "4.6",
                        level = "Hard"
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            BottomNavBar()
        }
    }
}

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = { Text("Find your track") },
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(30.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White.copy(alpha = 0.15f),
                unfocusedContainerColor = Color.White.copy(alpha = 0.15f),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

    }
}

@Composable
fun CategoryChips() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        ChipItem("Biking", R.drawable.biking)
        ChipItem("Running", R.drawable.running)
        ChipItem("Hiking", R.drawable.hiking)
    }
}

@Composable
fun ChipItem(text: String, iconRes: Int) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.2f))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(text, color = Color.White)
    }
}
@Composable
fun TrailGridCard(
    imageRes: Int,
    modifier: Modifier = Modifier
) {
    var isSaved by remember { mutableStateOf(false) } //declare code interaction

    Column(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {

        Box {
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(6.dp)
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            // saved button
            Icon(
                imageVector = if (isSaved) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = if (isSaved) Color.Red else Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(12.dp)
                    .size(28.dp)
                    .clickable {
                        isSaved = !isSaved
                    }
            )

            if (isSaved) {
                Text(
                    text = "Saved ❤️",
                    color = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                        .background(Color.Black.copy(alpha = 0.5f))
                        .padding(4.dp)
                )
            }
        }
    }
}

@Composable
fun TrailHeader(
    title: String,
    location: String,
    length: String,
    elevation: String,
    time: String,
    rating: String,
    level: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {

        // ROW 1 (Title + Labels)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Length", color = Color.White.copy(alpha = 0.6f), fontSize = 11.sp)
                Text("Elev", color = Color.White.copy(alpha = 0.6f), fontSize = 11.sp)
                Text("Est Time", color = Color.White.copy(alpha = 0.6f), fontSize = 11.sp)
            }
        }

        // ROW 2 (Location + Values)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = location,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 13.sp
            )

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Text(length, color = Color.White, fontSize = 13.sp)
                Text(elevation, color = Color.White, fontSize = 13.sp)
                Text(time, color = Color.White, fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        //  ROW 3 (Rating + Level)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text("⭐ $rating", color = Color.White, fontSize = 13.sp)

            Spacer(modifier = Modifier.width(12.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        when (level) {
                            "Easy" -> Color(0xFF00FF00)
                            "Moderate" -> Color(0xFFFFA500)
                            "Hard" -> Color(0xFFFF0000)
                            else -> Color.Gray
                        }
                    )
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(level, color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun BottomNavBar() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp) // adjust height
    ) {

        Image(
            painter = painterResource(id = R.drawable.kayubawah),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            NavItem("Explore", R.drawable.explore)
            NavItem("Saved", R.drawable.saved)
            NavItem("Map", R.drawable.navigate)
            NavItem("Activity", R.drawable.activity)
            NavItem("Profile", R.drawable.profile)
        }
    }
}

@Composable
fun NavItem(label: String, iconRes: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            modifier = Modifier.size(30.dp)
        )

        Text(
            text = label,
            color = Color.White,
            fontSize = 11.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen()
}