package alex.boruto.app.presentation.components

import alex.boruto.app.ui.theme.SMALL_PADDING
import alex.boruto.app.ui.theme.titleColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark

@Composable
fun OrderedList(
    title: String,
    items: List<String>,
    textColor: Color,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(bottom = SMALL_PADDING),
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = FontWeight.Bold
        )
        items.forEachIndexed { index, item ->
            Text(
                modifier = Modifier.alpha(0.5f),
                text = "${index + 1}. $item",
                color = textColor,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
        }
    }
}

@Composable
@PreviewLightDark
fun OrderedListPreview() {
    OrderedList(
        title = "Family",
        items = listOf("Minato", "Kushina"),
        textColor = titleColor,
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    )
}