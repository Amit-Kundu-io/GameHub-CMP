package amitkundu.theme.RememberIsScrollingUp

import androidx.compose.foundation.lazy.LazyListState
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
    listState: LazyListState
): Boolean {

    var isScrollingUp by remember { mutableStateOf(true) }
    var lastIndex by remember { mutableIntStateOf(0) }
    var lastOffset by remember { mutableIntStateOf(0) }

    //  NEW: track real user scroll
    var hasUserScrolled by remember { mutableStateOf(false) }

    LaunchedEffect(listState) {
        snapshotFlow {
            listState.firstVisibleItemIndex to
                    listState.firstVisibleItemScrollOffset
        }.collect { (index, offset) ->

            // Ignore restore / initial emissions
            if (!hasUserScrolled) {
                if (index != 0 || offset != 0) {
                    hasUserScrolled = true
                }
                lastIndex = index
                lastOffset = offset
                return@collect
            }

            isScrollingUp =
                if (index == lastIndex) {
                    offset < lastOffset
                } else {
                    index < lastIndex
                }

            lastIndex = index
            lastOffset = offset
        }
    }

    return isScrollingUp
}

