package com.example.stopwatch.presentation.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.stopwatch.data.model.TabItem
import com.example.stopwatch.presentation.stopwatch.StopwatchScreen
import com.example.stopwatch.presentation.timer.TimerScreen

@Composable
fun MainScreen() {
    val tabItemList = listOf(
        TabItem.Stopwatch,
        TabItem.Timer
    )
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    val horizontalPagerState = rememberPagerState {
        tabItemList.size
    }

    LaunchedEffect(key1 = selectedTabIndex) {
        horizontalPagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = horizontalPagerState.currentPage, horizontalPagerState.isScrollInProgress) {
        if (!horizontalPagerState.isScrollInProgress) {
            selectedTabIndex = horizontalPagerState.currentPage
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            TopSection(
                selectedTabIndex = selectedTabIndex,
                tabItemList = tabItemList,
                onTabChange = { selectedTabIndex = it }
            )

            BottomSection(
                horizontalPagerState = horizontalPagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }

}

@Composable
fun TopSection(
    modifier: Modifier = Modifier,
    selectedTabIndex: Int,
    tabItemList: List<TabItem>,
    onTabChange: (Int) -> Unit
) {
    TabRow(selectedTabIndex = selectedTabIndex) {
        tabItemList.forEachIndexed { index, item ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onTabChange(index) },
                text = { Text(text = item.title) },
                icon = {
                    Icon(
                        painter = painterResource(id = if (index == selectedTabIndex) item.selectedIcon else item.unSelectedIcon),
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}

@Composable
fun BottomSection(
    horizontalPagerState: PagerState,
    modifier: Modifier = Modifier
) {
    HorizontalPager(
        state = horizontalPagerState,
        modifier = modifier
    ) { index ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (index) {
                0 -> {
                    StopwatchScreen()
                }

                1 -> {
                    TimerScreen()
                }
            }
        }
    }
}