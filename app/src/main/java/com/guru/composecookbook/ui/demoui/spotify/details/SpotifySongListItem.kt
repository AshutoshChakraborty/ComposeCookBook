package com.guru.composecookbook.ui.demoui.spotify.details

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.ui.tooling.preview.Preview
import com.guru.composecookbook.theme.graySurface
import com.guru.composecookbook.theme.typography
import com.guru.composecookbook.ui.demoui.spotify.data.Album
import com.guru.composecookbook.ui.demoui.spotify.data.SpotifyDataProvider

@Composable
fun SpotifySongListItem(album: Album) {
    val album = remember(album.id) { album }
    Row(
        modifier = Modifier.padding(8.dp),
        verticalGravity = Alignment.CenterVertically,
    ) {
       Image(
           asset = imageResource(id = album.imageId),
           contentScale = ContentScale.Crop,
           modifier = Modifier.preferredSize(55.dp).padding(4.dp)
       )
        Column(modifier = Modifier.padding(horizontal = 4.dp).weight(1f)) {
            Text(
                text = album.song, 
                style = typography.h6.copy(fontSize = 16.sp),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = "${album.artist}, ${album.descriptions}",
                style = typography.subtitle2,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (album.id % 3 == 0) {
            Icon(
                asset = Icons.Default.Favorite,
                tint = MaterialTheme.colors.primaryVariant,
                modifier = Modifier.padding(4.dp).preferredSize(20.dp)
            )
        }
        Icon(
            asset = Icons.Default.MoreVert,
            tint = Color.LightGray,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
@Preview
fun PreviewSpotifySongListItem() {
    val album = SpotifyDataProvider.album
    SpotifySongListItem(album = album)
}