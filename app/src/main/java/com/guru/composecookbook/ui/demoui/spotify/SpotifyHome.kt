package com.guru.composecookbook.ui.demoui.spotify

import androidx.annotation.IntDef
import androidx.compose.animation.animate
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.lazy.LazyRowForIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.R
import com.guru.composecookbook.data.DemoDataProvider
import com.guru.composecookbook.theme.blue
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider
import com.guru.composecookbook.ui.utils.VerticalGrid
import com.guru.composecookbook.ui.utils.diagonalGradientTint
import com.guru.composecookbook.ui.utils.horizontalGradientBackground

@Composable
fun SpotifyHome() {
    val scrollState = rememberScrollState(0f)
    val surfaceGradient = SpotifyDataProvider.spotifySurfaceGradient(isSystemInDarkTheme())
    Stack(modifier = Modifier.fillMaxSize()) {
        ScrollableContent(scrollState = scrollState, surfaceGradient = surfaceGradient)
        Icon(
            asset = Icons.Outlined.Settings,
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .gravity(Alignment.TopEnd)
                .padding(start = 12.dp, end = 12.dp, top = 36.dp, bottom = 12.dp)
                .drawOpacity(animate(1f - scrollState.value/200f))
        )
        PlayerBottomBar(Modifier.gravity(Alignment.BottomCenter))
    }
}

@Composable
fun ScrollableContent(scrollState: ScrollState, surfaceGradient: List<Color>) {
    ScrollableColumn(
        scrollState = scrollState,
        modifier = Modifier.horizontalGradientBackground(surfaceGradient).padding(8.dp)
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        SpotifyTitle("Good Evening")
        HomeGridSection()
        HomeLanesSection()
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun PlayerBottomBar(modifier: Modifier) {
    val bottomBarHeight = 57.dp
    val backgroundColor =
        if (isSystemInDarkTheme()) graySurface else MaterialTheme.colors.background
    Row(
        modifier = modifier.padding(bottom = bottomBarHeight)
            .fillMaxWidth()
            .background(color = backgroundColor),
        verticalGravity = Alignment.CenterVertically,
    ) {
        Image(
            asset = imageResource(id = R.drawable.adele21),
            modifier = Modifier.preferredSize(65.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "Someone Like you by Adele",
            style = typography.h6.copy(fontSize = 14.sp),
            modifier = Modifier.padding(8.dp).weight(1f),
        )
        Icon(asset = Icons.Default.FavoriteBorder, modifier = Modifier.padding(8.dp))
        Icon(asset = Icons.Default.PlayArrow, modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun SpotifyTitle(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = typography.h5.copy(fontWeight = FontWeight.ExtraBold),
        modifier = modifier.padding(start = 8.dp, end = 4.dp, bottom = 8.dp, top = 24.dp)
    )
}

@Composable
fun HomeGridSection() {
    val items = remember { SpotifyDataProvider.albums }
    VerticalGrid {
        items.take(6).forEach { 
            SpotifyHomeGridItem(album = it)
        }
    }
}

@Composable
fun HomeLanesSection() {
    val categories = remember { SpotifyDataProvider.listOfSpotifyHomeLanes }
    categories.forEachIndexed { index, lane ->
        SpotifyTitle(text = lane)
        SpotifyLane(index)
    }
}

@Composable
fun SpotifyLane(index: Int) {
    val itemsEven = remember { SpotifyDataProvider.albums }
    val itemsOdd = remember { SpotifyDataProvider.albums.asReversed() }
    LazyRowFor(if (index%2 == 0) itemsEven else itemsOdd) {
        SpotifyLaneItem(album = it)
    }
}

@Preview
@Composable
fun PreviewSpotifyHome() {
    PreviewSpotifyHome()
}