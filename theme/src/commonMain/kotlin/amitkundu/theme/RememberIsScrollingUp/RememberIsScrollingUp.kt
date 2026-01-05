package amitkundu.theme.RememberIsScrollingUp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow

@Composable
fun rememberIsScrollingUp(
    listState: androidx.compose.foundation.lazy.LazyListState
): Boolean {

    var previousIndex by remember { mutableIntStateOf(0) }
    var previousScrollOffset by remember { mutableIntStateOf(0) }
    var isScrollingUp by remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.firstVisibleItemIndex to
                    listState.firstVisibleItemScrollOffset
        }.collect { (index, offset) ->
            isScrollingUp =
                if (index == previousIndex) {
                    offset < previousScrollOffset
                } else {
                    index < previousIndex
                }

            previousIndex = index
            previousScrollOffset = offset
        }
    }

    return isScrollingUp
}
