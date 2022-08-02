package br.com.progdeelite.compose.ui.components

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.progdeelite.compose.R
import br.com.progdeelite.compose.ui.theme.ArsenalTheme

// 1) COMO CRIAR A CLASSE DE DADOS DO CARD VIEW
// 2) COMO CRIAR O COMPONENTE DE GRADE USANDO O LAZY HORIZONTAL GRID
// 3) COMO CRIAR O PREVIEW E COMO USAR NA PRÁTICA

// 1) COMO CRIAR A CLASSE DE DADOS DO CARD VIEW
data class SimpleCardItem(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    val id: Int
)

// 2) COMO CRIAR O COMPONENTE DE GRADE USANDO O LAZY HORIZONTAL GRID
@Composable
fun Gridview(
    modifier: Modifier = Modifier,
    gridViewData: List<SimpleCardItem> = emptyList()
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(120.dp)
    ) {
        items(gridViewData) { gridItem ->
            SimpleCard(
                drawable = gridItem.drawable,
                text = gridItem.text,
                modifier = Modifier.height(56.dp)
            ) { Log.d("TESTANDO", "Navegue para: ${gridItem.id}")}
        }
    }
}

// 3) COMO CRIAR O PREVIEW E COMO USAR NA PRÁTICA
@Preview(showBackground = true, backgroundColor = 0xFFF0EAE2)
@Composable
fun GridviewPreview() {
    ArsenalTheme {
        Gridview(
            gridViewData = listOf(
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name,1),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name,2),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name,3),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name,4),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name,5),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name,6),
                SimpleCardItem(R.drawable.ic_droid, R.string.profile_name,7),
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}
