package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                content = {
                    Image(
                        painter = painterResource(id = R.drawable.banner),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize()
                    )
                }
            )
        }
    ) { innerPadding ->
        MainContent(Modifier.padding(innerPadding))
    }
}

val imageResourceIds = listOf(
    R.drawable.single,
    R.drawable.doubl,
    R.drawable.triple,
    R.drawable.collage,
    R.drawable.photo_edit,
    R.drawable.add_text,
)
val labels = listOf(
    R.string.single_frames,
    R.string.double_frames,
    R.string.triple_frames,
    R.string.collage,
    R.string.photo_edit,
    R.string.add_text
)
val framesResourceIds = listOf(
    R.drawable.double_square,
    R.drawable.single_heart,
    R.drawable.double_rectangle,
    R.drawable.double_heart_flowers,
    R.drawable.double_heart,
    R.drawable.premium_heart,
    R.drawable.triple_heart,
    R.drawable.triple_rectangle,
)

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp),
        contentPadding = PaddingValues(bottom = 15.dp)
    ) {
        item {
            HeaderSection()
        }
        item {
            FrameTypeItem(iconRestIds = imageResourceIds, labels = labels)
        }
        item {
            Text(text = stringResource(R.string.popular_frames),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(10.dp))
        }
        item {
            PopularFrameItem(imageRestIds = framesResourceIds)
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /* TODO: Add action */ }) {
            Image(
                painter = painterResource(id = R.drawable.ad1),
                contentDescription = "Camera"
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logo_menu),
            contentDescription = ""
        )
        IconButton(onClick = { /* TODO: Add action */ }) {
            Image(
                painter = painterResource(id = R.drawable.settings1),
                contentDescription = "Settings",
            )
        }
    }
}

@Preview
@Composable
private fun HeaderSectionPr() {
    HeaderSection()
}

@Composable
fun FrameTypeItem(iconRestIds: List<Int>, labels: List<Int>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.height(300.dp)
    ) {
        items(iconRestIds.size) { index ->
            IconComponent(
                iconRestId = iconRestIds[index],
                label = stringResource(id = labels[index])
            )
        }
    }
}

@Composable
fun IconComponent(iconRestId: Int, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = iconRestId),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .padding(16.dp)
        )
        Text(text = label, fontSize = 14.sp)
    }
}

@Composable
fun PopularFrameItem(imageRestIds: List<Int>) {
    val firstHalf = imageRestIds.subList(0, imageRestIds.size / 2)
    val secondHalf = imageRestIds.subList(imageRestIds.size / 2, imageRestIds.size)

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(1f)) {
                firstHalf.forEach { imageRestId ->
                    FrameItem(imageRestId = imageRestId)
                }
            }
            Column(modifier = Modifier.weight(1f)) {
                secondHalf.forEach { imageRestId ->
                    FrameItem(imageRestId = imageRestId)
                }
            }
        }
    }
}

@Composable
fun FrameItem(imageRestId: Int) {
    Image(
        painter = painterResource(id = imageRestId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    )
}

