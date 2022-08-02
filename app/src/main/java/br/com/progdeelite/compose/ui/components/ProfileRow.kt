package br.com.progdeelite.compose.ui.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.progdeelite.compose.R
import br.com.progdeelite.compose.ui.theme.ArsenalTheme

// 1) COMO CRIAR A CLASSE DE DADOS DO LAZY ROW
// 2) COMO CRIAR O COMPONENTE DE HISTORY (INSTAGRAM) COM LAZY ROW
// 3) COMO CRIAR O PREVIEW E COMO USAR NA PRATICA


// 1) COMO CRIAR A CLASSE DE DADOS DO LAZY ROW
data class ProfileImageItem(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    val id: Int = 0
)

// 2) COMO CRIAR O COMPONENTE DE HISTORY (INSTAGRAM) COM LAZY ROW
@Composable
fun ProfileRow(
    modifier: Modifier = Modifier,
    rowData: List<ProfileImageItem> = emptyList()
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(rowData) { item ->
            ProfileImage(item.drawable, item.text) { Log.d("TESTANDO", "Navegue para tela: ${item.id}") }
        }
    }
}

// 3) COMO CRIAR O PREVIEW E COMO USAR NA PRATICA
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun ProfileRowPreview() {
    ArsenalTheme {
        ProfileRow(
            rowData = listOf(
                ProfileImageItem( R.drawable.ic_droid, R.string.profile_name, 1),
                ProfileImageItem( R.drawable.ic_droid, R.string.profile_name,2),
                ProfileImageItem( R.drawable.ic_droid, R.string.profile_name,3),
                ProfileImageItem( R.drawable.ic_droid, R.string.profile_name,4),
                ProfileImageItem( R.drawable.ic_droid, R.string.profile_name,5),
                ProfileImageItem( R.drawable.ic_droid, R.string.profile_name,6),
                ProfileImageItem( R.drawable.ic_droid, R.string.profile_name,7),
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}
